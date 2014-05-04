package br.com.delogic.jnerator.impl;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.delogic.jnerator.AttributeGenerator;
import br.com.delogic.jnerator.AttributeGeneratorFactory;
import br.com.delogic.jnerator.Generator;
import br.com.delogic.jnerator.impl.generator.BigDecimalAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.BigIntegerAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.BooleanAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.ByteAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.CharacterAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.DateAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.DoubleAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.FloatAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.IntegerAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.LongAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.ShortAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.SimpleTypeCollectionAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.StringAttributeGenerator;
import br.com.delogic.jnerator.util.ReflectionUtils;

public class SimpleAttributeGeneratorFactory implements AttributeGeneratorFactory {

    Map<String, AttributeGenerator<?>> generatorsByType = new HashMap<String, AttributeGenerator<?>>();

    public SimpleAttributeGeneratorFactory() {
        generatorsByType.put("boolean", new BooleanAttributeGenerator());
        generatorsByType.put("byte", new ByteAttributeGenerator());
        generatorsByType.put("short", new ShortAttributeGenerator());
        generatorsByType.put("int", new IntegerAttributeGenerator());
        generatorsByType.put("long", new LongAttributeGenerator());
        generatorsByType.put("float", new FloatAttributeGenerator());
        generatorsByType.put("double", new DoubleAttributeGenerator());
        generatorsByType.put("char", new CharacterAttributeGenerator());
        generatorsByType.put("Boolean", new BooleanAttributeGenerator());
        generatorsByType.put("Byte", new ByteAttributeGenerator());
        generatorsByType.put("Short", new ShortAttributeGenerator());
        generatorsByType.put("Integer", new IntegerAttributeGenerator());
        generatorsByType.put("Long", new LongAttributeGenerator());
        generatorsByType.put("Float", new FloatAttributeGenerator());
        generatorsByType.put("Double", new DoubleAttributeGenerator());
        generatorsByType.put("Character", new CharacterAttributeGenerator());
        generatorsByType.put("String", new StringAttributeGenerator());
        generatorsByType.put("BigInteger", new BigIntegerAttributeGenerator());
        generatorsByType.put("BigDecimal", new BigDecimalAttributeGenerator());
        generatorsByType.put("Date", new DateAttributeGenerator());
    }

    public AttributeGenerator<?> create(Field field, final Generator<?> generator) {

        Class<?> type = field.getType();

        if (generatorsByType.containsKey(type.getSimpleName())) {
            // simple types
            return generatorsByType.get(type.getSimpleName());
        }

        //when the attribute is a collection
        if (Collection.class.isAssignableFrom(type)) {

            //getting the generic type
            Class<?> genericType = (Class<?>) ReflectionUtils.getFirstGenericType(field);

            if (generatorsByType.containsKey(genericType.getSimpleName())) {
                //if this is a simple attribute generator type
                return new SimpleTypeCollectionAttributeGenerator(field, generatorsByType.get(genericType.getClass().getSimpleName()));
            }
        }

        throw new IllegalArgumentException(String.format("Unfortunatelly we don't know how to create %s. "
            + "Configure this type to be created before the current object or register an attribute generator for this attribute.", type));

    }

}
