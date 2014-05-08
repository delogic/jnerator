package br.com.delogic.jnerator.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeConfigurationFactory;
import br.com.delogic.jnerator.AttributeGenerator;
import br.com.delogic.jnerator.AttributeGeneratorFactory;
import br.com.delogic.jnerator.InstanceGenerator;
import br.com.delogic.jnerator.JNerator;
import br.com.delogic.jnerator.exception.JNeratorException;
import br.com.delogic.jnerator.impl.generator.ProvidedAttributeGenerator;
import br.com.delogic.jnerator.util.ReflectionUtils;

public class SimpleInstanceGenerator<T> implements InstanceGenerator<T> {

    // <AttributeName, AttributeConfiguration>
    private final Map<String, AttributeConfiguration>        attributesConfiguration;

    // <AttributeName, AttributeGenerator>
    private final Map<String, AttributeGenerator<?, Object>> attributesGenerator;
    private final Class<T>                                   type;
    private List<T>                                          cachedInstances;
    private final JNerator                                   jNerator;

    public SimpleInstanceGenerator(Class<T> type, AttributeConfigurationFactory attributeConfigurationFactory,
        AttributeGeneratorFactory attributeGeneratorFactory, JNerator jNerator) {
        this.type = type;
        this.jNerator = jNerator;
        this.attributesConfiguration = asMap(attributeConfigurationFactory.create(type));
        this.attributesGenerator = new HashMap<String, AttributeGenerator<?, Object>>();

        for (Entry<String, AttributeConfiguration> attributeConfiguration : attributesConfiguration.entrySet()) {
            AttributeGenerator<?, Object> generator = attributeGeneratorFactory.create(attributeConfiguration.getValue().getField(), this);
            attributesGenerator.put(attributeConfiguration.getValue().getName(), generator);
        }

    }

    private Map<String, AttributeConfiguration> asMap(List<AttributeConfiguration> create) {
        Map<String, AttributeConfiguration> attrs = new HashMap<String, AttributeConfiguration>();
        for (AttributeConfiguration ac : create) {
            attrs.put(ac.getName(), ac);
        }
        return attrs;
    }

    public List<T> generate(int amount) {

        List<T> instances = new ArrayList<T>();

        for (int index = 1; index <= amount; index++) {

            T instance = (T) ReflectionUtils.instantiate(type);

            populateInstance(instance, index);

            instances.add(instance);

        }

        cachedInstances = Collections.unmodifiableList(instances);

        return instances;

    }

    void populateInstance(T instance, int index) {

        for (Entry<String, AttributeConfiguration> config : attributesConfiguration.entrySet()) {

            AttributeGenerator<?, Object> generator = attributesGenerator.get(config.getKey());

            Object value = generator.generate(index, config.getValue(), instance);

            Field field = config.getValue().getField();

            setFieldValue(field, instance, value);

        }
    }

    void setFieldValue(Field field, T instance, Object value) {
        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            field.set(instance, value);
        } catch (Exception e) {
            throw new JNeratorException("Error trying to set field value for field:" + field + " and value: " + value, e);
        }
    }

    public List<T> getCachedInstances() {
        return cachedInstances;
    }

    public <E> InstanceGenerator<T> setAttributeGenerator(String attributeName, AttributeGenerator<E, T> attributeGenerator) {
        attributesGenerator.put(attributeName, (AttributeGenerator<?, Object>) attributeGenerator);
        return this;
    }

    public <E> InstanceGenerator<E> setRelationshipAttributeGenerator(String attributeName, Class<? extends E> relationshipType) {
        AttributeConfiguration config = attributesConfiguration.get(attributeName);

        final InstanceGenerator<E> instanceGenerator = (InstanceGenerator<E>) jNerator.prepare(relationshipType);
        AttributeGenerator<E, T> attributeGenerator = new AttributeGenerator<E, T>() {
            public E generate(int index, AttributeConfiguration attributeConfiguration, T instance) {
                instanceGenerator.setAttributeGenerator("order", new ProvidedAttributeGenerator(instance));
                return instanceGenerator.generate(1).get(0);
            }
        };

        this.attributesGenerator.put(attributeName, (AttributeGenerator<?, Object>) attributeGenerator);

        return instanceGenerator;
    }

}
