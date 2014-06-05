package br.com.delogic.jnerator.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;
import br.com.delogic.jnerator.AttributeGeneratorFactory;
import br.com.delogic.jnerator.InstanceGenerator;
import br.com.delogic.jnerator.RelationshipConfiguration;
import br.com.delogic.jnerator.RelationshipType;
import br.com.delogic.jnerator.impl.generator.ArrayAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.BigDecimalAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.BigIntegerAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.BooleanAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.ByteAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.CharacterAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.ComplexInheritedAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.ComplexTypeAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.ComplexTypeCollectionAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.DateAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.DoubleAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.EnumAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.FloatAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.IntegerAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.LongAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.ProvidedAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.ShortAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.SimpleTypeCollectionAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.StringAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.UndefinedAttributeExceptionGenerator;
import br.com.delogic.jnerator.util.ReflectionUtils;

public class SimpleAttributeGeneratorFactory implements AttributeGeneratorFactory {

    Map<String, AttributeGenerator<?>>  attributeGenerators = new HashMap<String, AttributeGenerator<?>>();
    Map<Class<?>, InstanceGenerator<?>> instanceGenerators  = new HashMap<Class<?>, InstanceGenerator<?>>();

    public SimpleAttributeGeneratorFactory() {
        attributeGenerators.put("boolean", new BooleanAttributeGenerator());
        attributeGenerators.put("byte", new ByteAttributeGenerator());
        attributeGenerators.put("short", new ShortAttributeGenerator());
        attributeGenerators.put("int", new IntegerAttributeGenerator());
        attributeGenerators.put("long", new LongAttributeGenerator());
        attributeGenerators.put("float", new FloatAttributeGenerator());
        attributeGenerators.put("double", new DoubleAttributeGenerator());
        attributeGenerators.put("char", new CharacterAttributeGenerator());
        attributeGenerators.put("java.lang.Boolean", new BooleanAttributeGenerator());
        attributeGenerators.put("java.lang.Byte", new ByteAttributeGenerator());
        attributeGenerators.put("java.lang.Short", new ShortAttributeGenerator());
        attributeGenerators.put("java.lang.Integer", new IntegerAttributeGenerator());
        attributeGenerators.put("java.lang.Long", new LongAttributeGenerator());
        attributeGenerators.put("java.lang.Float", new FloatAttributeGenerator());
        attributeGenerators.put("java.lang.Double", new DoubleAttributeGenerator());
        attributeGenerators.put("java.lang.Character", new CharacterAttributeGenerator());
        attributeGenerators.put("java.lang.String", new StringAttributeGenerator());
        attributeGenerators.put("java.math.BigInteger", new BigIntegerAttributeGenerator());
        attributeGenerators.put("java.math.BigDecimal", new BigDecimalAttributeGenerator());
        attributeGenerators.put("java.util.Date", new DateAttributeGenerator());
    }

    public AttributeGenerator<?> create(final Field field, final InstanceGenerator<?> generator) {

        final Class<?> type = field.getType();

        if (attributeGenerators.containsKey(type.getName())) {
            // simple types
            return attributeGenerators.get(type.getName());
        }

        // when the attribute is a collection
        if (Collection.class.isAssignableFrom(type)) {

            // getting the generic type
            Class<?> genericType = (Class<?>) ReflectionUtils.getFirstGenericType(field);

            if (attributeGenerators.containsKey(genericType.getName())) {
                // if this is a simple attribute generator type
                return new SimpleTypeCollectionAttributeGenerator(field, attributeGenerators.get(genericType.getName()));
            }

            // when generic type is an enum
            if (Enum.class.isAssignableFrom(genericType)) {
                // if this is a simple attribute generator type
                return new SimpleTypeCollectionAttributeGenerator(field, registerAndReturnEnumAttributeGenerator(genericType));
            }

            // if is a collection of objects
            if (instanceGenerators.containsKey(genericType)) {
                return new ComplexTypeCollectionAttributeGenerator(field, instanceGenerators.get(genericType));
            }

        }

        // when is an enum
        if (Enum.class.isAssignableFrom(type)) {
            return registerAndReturnEnumAttributeGenerator(type);
        }

        // when is an array
        if (type.isArray()) {

            if (attributeGenerators.containsKey(type.getComponentType().getName())) {
                return new ArrayAttributeGenerator(field, attributeGenerators.get(type.getComponentType().getName()));
            }

        }

        // when the attribute is another instance
        if (instanceGenerators.containsKey(type)) {
            return new ComplexTypeAttributeGenerator(field, instanceGenerators.get(type));
        }

        AttributeGenerator<?> attributeGenerator = tryAssinableGenerators(type);
        if (attributeGenerator != null) {
            return attributeGenerator;
        }

        // return a attribute generation to throw an exception in case it's not
        // replaced after prepare phase
        return new UndefinedAttributeExceptionGenerator() {
            public Object generate(int index, AttributeConfiguration attributeConfiguration, Object instance) {
                throw new IllegalArgumentException(String.format("Unfortunatelly we don't know how to create %s.%s of type %s."
                    + " Configure this type to be created before %s or register an attribute generator for %s attribute.",
                    field.getDeclaringClass(), field.getName(), firstValidValue(field.getGenericType(), field.getType()),
                    field.getDeclaringClass(), field.getName()));
            }
        };

    }

    /**
     * Checks for attributes which may have inheritance and those subclasses may
     * have already been created as attribute generators.
     *
     * @param type
     * @return
     */
    private AttributeGenerator<?> tryAssinableGenerators(Class<?> type) {

        List<InstanceGenerator<?>> assinableGenerators = new ArrayList<InstanceGenerator<?>>();
        for (Entry<Class<?>, InstanceGenerator<?>> entry : instanceGenerators.entrySet()) {
            if (type.isAssignableFrom(entry.getKey())) {
                assinableGenerators.add(entry.getValue());
            }
        }

        if (!assinableGenerators.isEmpty()) {
            return new ComplexInheritedAttributeGenerator(type, assinableGenerators);
        }

        return null;
    }

    protected Object firstValidValue(Type genericType, Class<?> type) {
        if (genericType != null) {
            return genericType;
        } else {
            return type;
        }
    }

    @SuppressWarnings("unchecked")
    private AttributeGenerator<Enum<?>> registerAndReturnEnumAttributeGenerator(Class<?> type) {
        if (!attributeGenerators.containsKey(type.getName())) {
            // if is a new type we create and add
            attributeGenerators.put(type.getName(), new EnumAttributeGenerator(type));
        }

        return (AttributeGenerator<Enum<?>>) attributeGenerators.get(type.getName());
    }

    public <E> void addInstanceGenerator(InstanceGenerator<E> instanceGenerator, Class<?> type) {
        instanceGenerators.put(type, instanceGenerator);
    }

    public <O> AttributeGenerator<?> create(final Field field, final InstanceGenerator<O> generator,
        final RelationshipConfiguration relationshipConfiguration) {

        if (relationshipConfiguration.getRelationshipType() == RelationshipType.ONE_TO_ONE) {
            // create a new attribute generator to generate the owned attribute
            return new AttributeGenerator<Object>() {

                public Object generate(int index, AttributeConfiguration attributeConfiguration, Object instance) {
                    // when generating the owned attribute we provide the owner
                    // to complete the relationship
                    generator.setAttributeGenerator(relationshipConfiguration.getOwnedOwnerAttributeName(),
                        new ProvidedAttributeGenerator<Object>(instance));
                    return generator.generate(1).get(0);

                }
            };

        }

        if (relationshipConfiguration.getRelationshipType() == RelationshipType.ONE_TO_MANY) {
            // when the attribute is a collection
            if (Collection.class.isAssignableFrom(field.getType())) {
                // if is a collection of objects
                return new ComplexTypeCollectionAttributeGenerator(field, generator) {
                    @Override
                    public Collection<?> generate(int index, AttributeConfiguration attributeConfiguration, Object instance) {
                        generator.setAttributeGenerator(relationshipConfiguration.getOwnedOwnerAttributeName(),
                            new ProvidedAttributeGenerator<Object>(instance));
                        Collection<Object> collection = createCollection(field);
                        collection.addAll(generator.generate(10));
                        return collection;

                    }
                };
            }
        }

        throw new IllegalArgumentException(String.format("Could not create Generator for field %s", field));

    }
}
