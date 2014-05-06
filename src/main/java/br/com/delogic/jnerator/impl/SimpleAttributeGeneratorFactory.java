package br.com.delogic.jnerator.impl;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.delogic.jnerator.AttributeGenerator;
import br.com.delogic.jnerator.AttributeGeneratorFactory;
import br.com.delogic.jnerator.InstanceGenerator;
import br.com.delogic.jnerator.impl.generator.BigDecimalAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.BigIntegerAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.BooleanAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.ByteAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.CharacterAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.ComplexTypeAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.DateAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.DoubleAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.EnumAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.FloatAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.IntegerAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.LongAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.ShortAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.SimpleTypeCollectionAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.StringAttributeGenerator;
import br.com.delogic.jnerator.util.ReflectionUtils;

public class SimpleAttributeGeneratorFactory implements AttributeGeneratorFactory {

    Map<String, AttributeGenerator<?>> attributeGenerators = new HashMap<String, AttributeGenerator<?>>();
    Map<Class<?>, InstanceGenerator<?>> instanceGenerators = new HashMap<Class<?>, InstanceGenerator<?>>();

    public SimpleAttributeGeneratorFactory() {
        attributeGenerators.put("boolean", new BooleanAttributeGenerator());
        attributeGenerators.put("byte", new ByteAttributeGenerator());
        attributeGenerators.put("short", new ShortAttributeGenerator());
        attributeGenerators.put("int", new IntegerAttributeGenerator());
        attributeGenerators.put("long", new LongAttributeGenerator());
        attributeGenerators.put("float", new FloatAttributeGenerator());
        attributeGenerators.put("double", new DoubleAttributeGenerator());
        attributeGenerators.put("char", new CharacterAttributeGenerator());
        attributeGenerators.put("Boolean", new BooleanAttributeGenerator());
        attributeGenerators.put("Byte", new ByteAttributeGenerator());
        attributeGenerators.put("Short", new ShortAttributeGenerator());
        attributeGenerators.put("Integer", new IntegerAttributeGenerator());
        attributeGenerators.put("Long", new LongAttributeGenerator());
        attributeGenerators.put("Float", new FloatAttributeGenerator());
        attributeGenerators.put("Double", new DoubleAttributeGenerator());
        attributeGenerators.put("Character", new CharacterAttributeGenerator());
        attributeGenerators.put("String", new StringAttributeGenerator());
        attributeGenerators.put("BigInteger", new BigIntegerAttributeGenerator());
        attributeGenerators.put("BigDecimal", new BigDecimalAttributeGenerator());
        attributeGenerators.put("Date", new DateAttributeGenerator());
    }

    public AttributeGenerator<?> create(Field field, final InstanceGenerator<?> generator) {

        Class<?> type = field.getType();

        if (attributeGenerators.containsKey(type.getSimpleName())) {
            // simple types
            return attributeGenerators.get(type.getSimpleName());
        }

        //when the attribute is a collection
        if (Collection.class.isAssignableFrom(type)) {

            //getting the generic type
            Class<?> genericType = (Class<?>) ReflectionUtils.getFirstGenericType(field);

            if (attributeGenerators.containsKey(genericType.getSimpleName())) {
                //if this is a simple attribute generator type
                return new SimpleTypeCollectionAttributeGenerator(field, attributeGenerators.get(genericType.getClass().getSimpleName()));
            }
        }

        //when is an enum
        if (Enum.class.isAssignableFrom(type)){

            return new EnumAttributeGenerator(field);

        }

        if (instanceGenerators.containsKey(type)){
            return new ComplexTypeAttributeGenerator(field, instanceGenerators.get(type));
        }

        throw new IllegalArgumentException(String.format("Unfortunatelly we don't know how to create %s. "
            + "Configure this type to be created before the current object or register an attribute generator for this attribute.", type));

    }

    public <E> void addInstanceGenerator(InstanceGenerator<E> instanceGenerator, Class<?> type) {
        instanceGenerators.put(type, instanceGenerator);
    }

}
