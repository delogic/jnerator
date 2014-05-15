package br.com.delogic.jnerator.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import br.com.delogic.jfunk.Each;
import br.com.delogic.jfunk.ForEach;
import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeConfigurationFactory;
import br.com.delogic.jnerator.AttributeGenerator;
import br.com.delogic.jnerator.AttributeGeneratorFactory;
import br.com.delogic.jnerator.InstanceGenerator;
import br.com.delogic.jnerator.JNerator;
import br.com.delogic.jnerator.RelationshipConfiguration;
import br.com.delogic.jnerator.RelationshipConfigurationFactory;
import br.com.delogic.jnerator.exception.JNeratorException;
import br.com.delogic.jnerator.util.ReflectionUtils;

public class SimpleInstanceGenerator<T> implements InstanceGenerator<T> {

    // <AttributeName, AttributeConfiguration>
    private final Map<String, AttributeConfiguration>        attributesConfiguration;

    // <AttributeName, AttributeGenerator>
    private final Map<String, AttributeGenerator<?, Object>> attributesGenerator;
    private final Class<T>                                   type;
    private List<T>                                          cachedInstances;
    private final JNerator                                   jNerator;
    private final RelationshipConfigurationFactory           relationshipConfigurationFactory;
    private final AttributeGeneratorFactory                  attributeGeneratorFactory;
    private final Set<String>                                ignoredAttributes;

    public SimpleInstanceGenerator(Class<T> type, AttributeConfigurationFactory attributeConfigurationFactory,
        AttributeGeneratorFactory attributeGeneratorFactory, RelationshipConfigurationFactory relationshipConfigurationFactory,
        JNerator jNerator) {
        this.type = type;
        this.jNerator = jNerator;
        this.relationshipConfigurationFactory = relationshipConfigurationFactory;
        this.attributeGeneratorFactory = attributeGeneratorFactory;
        this.attributesConfiguration = asMap(attributeConfigurationFactory.create(type));
        this.attributesGenerator = new HashMap<String, AttributeGenerator<?, Object>>();
        this.ignoredAttributes = new HashSet<String>();

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

        removeIgnoredAttributes();

        int displayedExecution = 0;
        int currentAmount = 0;

        if (amount > 1000) System.out.println(type);

        for (int index = 0; index < amount; index++) {

            T instance = (T) ReflectionUtils.instantiate(type);

            populateInstance(instance, index);

            instances.add(instance);

            if (amount > 1000 && (currentAmount = (int) (((float) index / amount) * 100)) > displayedExecution) {
                String line = "" + currentAmount + "%\r";
                System.out.print(line);
                displayedExecution = currentAmount;
            }

        }

        cachedInstances = Collections.unmodifiableList(instances);

        return instances;

    }

    private void removeIgnoredAttributes() {
        ForEach.element(ignoredAttributes, new Each<String>() {
            public void each(String element, int arg1) {
                attributesConfiguration.remove(element);
                attributesGenerator.remove(element);
            }
        });
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

        RelationshipConfiguration relationshipConfiguration = relationshipConfigurationFactory.create(config.getField());
        InstanceGenerator<E> instanceGenerator = (InstanceGenerator<E>) jNerator.prepare(relationshipType);

        AttributeGenerator<?, Object> attributeGenerator = attributeGeneratorFactory.create(config.getField(), instanceGenerator,
            relationshipConfiguration);

        this.attributesGenerator.put(attributeName, attributeGenerator);

        return instanceGenerator;
    }

    public <T> InstanceGenerator<T> setRelationshipAttributeGenerator(String attributeName, Class<? extends T>... relationshipTypes) {
        AttributeConfiguration config = attributesConfiguration.get(attributeName);

        RelationshipConfiguration relationshipConfiguration = relationshipConfigurationFactory.create(config.getField());

        final List<InstanceGenerator<T>> allTypesGenerators = new ArrayList<InstanceGenerator<T>>();
        for (Class<? extends T> relType : relationshipTypes) {
            allTypesGenerators.add((InstanceGenerator<T>) jNerator.prepare(relType));
        }

        InstanceGenerator<T> proxyMultiGenerators = new InstanceGenerator<T>() {

            public List<T> generate(int amount) {
                List<T> ts = new ArrayList<T>();
                for (InstanceGenerator<T> ig : allTypesGenerators) {
                    ts.addAll(ig.generate(amount));
                }
                // let's shufle to avoid same results
                Collections.shuffle(ts);
                return ts;
            }

            public List<T> getCachedInstances() {
                List<T> ts = new ArrayList<T>();
                for (InstanceGenerator<T> ig : allTypesGenerators) {
                    ts.addAll(ig.getCachedInstances());
                }
                return ts;
            }

            public <E> InstanceGenerator<T> setAttributeGenerator(String attributeName, AttributeGenerator<E, T> attributeGenerator) {
                for (InstanceGenerator<T> ig : allTypesGenerators) {
                    ig.setAttributeGenerator(attributeName, attributeGenerator);
                }
                return this;
            }

            public <E> InstanceGenerator<E> setRelationshipAttributeGenerator(String attributeName, Class<? extends E> type) {
                throw new UnsupportedOperationException(
                    "Multi types relationship attribute generators cannot set other attribute generators");
            }

            public <E> InstanceGenerator<E> setRelationshipAttributeGenerator(String attributeName, Class<? extends E>... types) {
                throw new UnsupportedOperationException(
                    "Multi types relationship attribute generators cannot set other attribute generators");
            }

            public InstanceGenerator<T> doNotGenerateFor(String... attributeNames) {
                throw new UnsupportedOperationException(
                    "Multi types relationship attribute generators cannot set this parameter");
            }
        };

        AttributeGenerator<?, Object> attributeGenerator = attributeGeneratorFactory.create(config.getField(), proxyMultiGenerators,
            relationshipConfiguration);

        this.attributesGenerator.put(attributeName, attributeGenerator);

        return proxyMultiGenerators;
    }

    public InstanceGenerator<T> doNotGenerateFor(String... attributeNames) {
        for (String atr : attributeNames) {
            ignoredAttributes.add(atr);
        }
        return this;
    }

}
