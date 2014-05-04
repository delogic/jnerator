package br.com.delogic.jnerator.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeConfigurationFactory;
import br.com.delogic.jnerator.AttributeGenerator;
import br.com.delogic.jnerator.AttributeGeneratorFactory;
import br.com.delogic.jnerator.Generator;
import br.com.delogic.jnerator.exception.JNeratorException;
import br.com.delogic.jnerator.util.ReflectionUtils;

public class SimpleInstanceGenerator<E> implements Generator<E> {

    private final Map<String, AttributeConfiguration> attributesConfiguration;
    private final Map<String, AttributeGenerator<?>>  attributesGenerator;
    private final Class<E>                            type;

    public SimpleInstanceGenerator(Class<E> type, AttributeConfigurationFactory attributeConfigurationFactory,
        AttributeGeneratorFactory attributeGeneratorFactory) {
        this.type = type;
        this.attributesConfiguration = asMap(attributeConfigurationFactory.create(type));
        this.attributesGenerator = new HashMap<String, AttributeGenerator<?>>();

        for (Entry<String, AttributeConfiguration> config : attributesConfiguration.entrySet()) {
            AttributeGenerator<?> generator = attributeGeneratorFactory.create(config.getValue().getField(), this);
            attributesGenerator.put(config.getKey(), generator);
        }

    }

    private Map<String, AttributeConfiguration> asMap(List<AttributeConfiguration> create) {
        Map<String, AttributeConfiguration> attrs = new HashMap<String, AttributeConfiguration>();
        for (AttributeConfiguration ac : create) {
            attrs.put(ac.getName(), ac);
        }
        return attrs;
    }

    public List<E> generate(int amount) {

        List<E> instances = new ArrayList<E>();

        for (int index = 0; index < amount; index++) {

            E instance = (E) ReflectionUtils.instantiate(type);

            populateInstance(instance, index);

            instances.add(instance);

        }

        return instances;

    }

    void populateInstance(E instance, int index) {

        for (Entry<String, AttributeConfiguration> config : attributesConfiguration.entrySet()) {

            AttributeGenerator<?> generator = attributesGenerator.get(config.getKey());

            Object value = generator.generate(index, config.getValue());

            Field field = config.getValue().getField();

            setFieldValue(field, instance, value);

        }
    }

    void setFieldValue(Field field, E instance, Object value) {
        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            field.set(instance, value);
        } catch (Exception e) {
            throw new JNeratorException("Error trying to set field value for field:" + field + " and value: " + value, e);
        }
    }

}
