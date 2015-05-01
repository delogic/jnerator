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
import br.com.delogic.jnerator.AttributeConfigurationImpl;
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
    private final Map<String, AttributeConfigurationImpl<T>> attributesConfiguration;

    // <AttributeName, AttributeGenerator>
    private final Map<String, AttributeGenerator<?>>         attributesGenerator;
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
        this.attributesConfiguration = asMap(attributeConfigurationFactory.create(type, this));
        this.attributesGenerator = new HashMap<String, AttributeGenerator<?>>();
        this.ignoredAttributes = new HashSet<String>();

        for (Entry<String, AttributeConfigurationImpl<T>> attributeConfiguration : attributesConfiguration.entrySet()) {
            AttributeGenerator<?> generator = attributeGeneratorFactory.create(attributeConfiguration.getValue().getField(), this);
            attributesGenerator.put(attributeConfiguration.getValue().getName(), generator);
        }

    }

    private Map<String, AttributeConfigurationImpl<T>> asMap(List<AttributeConfigurationImpl<T>> create) {
        Map<String, AttributeConfigurationImpl<T>> attrs = new HashMap<String, AttributeConfigurationImpl<T>>();
        for (AttributeConfigurationImpl<T> ac : create) {
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

        for (Entry<String, AttributeConfigurationImpl<T>> config : attributesConfiguration.entrySet()) {

            AttributeGenerator<?> generator = attributesGenerator.get(config.getKey());

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

    public <E> InstanceGenerator<T> setAttributeGenerator(String attributeName, AttributeGenerator<E> attributeGenerator) {
        attributesGenerator.put(attributeName, (AttributeGenerator<?>) attributeGenerator);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <R> InstanceGenerator<R> forRelationship(String attributeName, Class<? extends R> relationshipType) {
        AttributeConfiguration<T> config = attributesConfiguration.get(attributeName);

        RelationshipConfiguration relationshipConfiguration = relationshipConfigurationFactory.create(config.getField());
        InstanceGenerator<R> instanceGenerator = (InstanceGenerator<R>) jNerator.prepare(relationshipType);

        AttributeGenerator<?> attributeGenerator = attributeGeneratorFactory.create(config.getField(), instanceGenerator,
            relationshipConfiguration);

        this.attributesGenerator.put(attributeName, attributeGenerator);

        return instanceGenerator;
    }

    @SuppressWarnings("unchecked")
    public <R> InstanceGenerator<R> forRelationship(String attributeName, Class<? extends R>... relationshipTypes) {
        AttributeConfiguration<T> config = attributesConfiguration.get(attributeName);

        RelationshipConfiguration relationshipConfiguration = relationshipConfigurationFactory.create(config.getField());

        final List<InstanceGenerator<R>> allTypesGenerators = new ArrayList<InstanceGenerator<R>>();
        for (Class<? extends R> relType : relationshipTypes) {
            allTypesGenerators.add((InstanceGenerator<R>) jNerator.prepare(relType));
        }

        InstanceGenerator<R> proxyMultiGenerators = new InstanceGenerator<R>() {

            public List<R> generate(int amount) {
                List<R> ts = new ArrayList<R>();
                for (InstanceGenerator<R> ig : allTypesGenerators) {
                    ts.addAll(ig.generate(amount));
                }
                // let's shufle to avoid same results
                Collections.shuffle(ts);
                return ts;
            }

            public List<R> getCachedInstances() {
                List<R> ts = new ArrayList<R>();
                for (InstanceGenerator<R> ig : allTypesGenerators) {
                    ts.addAll(ig.getCachedInstances());
                }
                return ts;
            }

            public <E> InstanceGenerator<R> setAttributeGenerator(String attributeName, AttributeGenerator<E> attributeGenerator) {
                for (InstanceGenerator<R> ig : allTypesGenerators) {
                    ig.setAttributeGenerator(attributeName, attributeGenerator);
                }
                return this;
            }

            public <E> InstanceGenerator<E> forRelationship(String attributeName, Class<? extends E> type) {
                throw new UnsupportedOperationException(
                    "Multi types relationship attribute generators cannot set other attribute generators");
            }

            public <E> InstanceGenerator<E> forRelationship(String attributeName, Class<? extends E>... types) {
                throw new UnsupportedOperationException(
                    "Multi types relationship attribute generators cannot set other attribute generators");
            }

            public InstanceGenerator<R> doNotGenerateAttribute(String... attributeNames) {
                throw new UnsupportedOperationException(
                    "Multi types relationship attribute generators cannot set this parameter");
            }

            public AttributeConfiguration<R> forAttr(String attributeName) {
                throw new UnsupportedOperationException(
                    "Multi types relationship attribute generators cannot set this parameter");
            }

            public <E> InstanceGenerator<E> forRelationship(String attributeName, List<Class<? extends E>> types) {
                throw new UnsupportedOperationException(
                    "Multi types relationship attribute generators cannot set this parameter");
            }

            public AttributeConfiguration<R> getAttributeConfigurationFor(String attributeName) {
                throw new UnsupportedOperationException(
                    "Multi types relationship attribute generators cannot set this parameter");
            }

        };

        AttributeGenerator<?> attributeGenerator = attributeGeneratorFactory.create(config.getField(), proxyMultiGenerators,
            relationshipConfiguration);

        this.attributesGenerator.put(attributeName, attributeGenerator);

        return proxyMultiGenerators;
    }

    public InstanceGenerator<T> doNotGenerateAttribute(String... attributeNames) {
        for (String atr : attributeNames) {
            ignoredAttributes.add(atr);
        }
        return this;
    }

    public AttributeConfiguration<T> forAttr(String attributeName) {
        return (AttributeConfiguration<T>) attributesConfiguration.get(attributeName);
    }

    @SuppressWarnings("unchecked")
    public <E> InstanceGenerator<E> forRelationship(String attributeName, List<Class<? extends E>> types) {
        AttributeConfiguration<T> config = attributesConfiguration.get(attributeName);

        RelationshipConfiguration relationshipConfiguration = relationshipConfigurationFactory.create(config.getField());

        final List<InstanceGenerator<E>> allTypesGenerators = new ArrayList<InstanceGenerator<E>>();
        for (Class<? extends E> relType : types) {
            allTypesGenerators.add((InstanceGenerator<E>) jNerator.prepare(relType));
        }

        InstanceGenerator<E> proxyMultiGenerators = new InstanceGenerator<E>() {

            public List<E> generate(int amount) {
                List<E> ts = new ArrayList<E>();
                for (InstanceGenerator<E> ig : allTypesGenerators) {
                    ts.addAll(ig.generate(amount));
                }
                // let's shufle to avoid same results
                Collections.shuffle(ts);
                return ts;
            }

            public List<E> getCachedInstances() {
                List<E> ts = new ArrayList<E>();
                for (InstanceGenerator<E> ig : allTypesGenerators) {
                    ts.addAll(ig.getCachedInstances());
                }
                return ts;
            }

            public <I> InstanceGenerator<E> setAttributeGenerator(String attributeName, AttributeGenerator<I> attributeGenerator) {
                for (InstanceGenerator<E> ig : allTypesGenerators) {
                    ig.setAttributeGenerator(attributeName, attributeGenerator);
                }
                return this;
            }

            public <E> InstanceGenerator<E> forRelationship(String attributeName, Class<? extends E> type) {
                throw new UnsupportedOperationException(
                    "Multi types relationship attribute generators cannot set other attribute generators");
            }

            public <R> InstanceGenerator<R> forRelationship(String attributeName, Class<? extends R>... types) {
                throw new UnsupportedOperationException(
                    "Multi types relationship attribute generators cannot set other attribute generators");
            }

            public InstanceGenerator<E> doNotGenerateAttribute(String... attributeNames) {
                throw new UnsupportedOperationException(
                    "Multi types relationship attribute generators cannot set this parameter");
            }

            public AttributeConfiguration<E> forAttr(String attributeName) {
                throw new UnsupportedOperationException(
                    "Multi types relationship attribute generators cannot set this parameter");
            }

            public <R> InstanceGenerator<R> forRelationship(String attributeName, List<Class<? extends R>> types) {
                throw new UnsupportedOperationException(
                    "Multi types relationship attribute generators cannot set this parameter");
            }

            public AttributeConfiguration<E> getAttributeConfigurationFor(String attributeName) {
                throw new UnsupportedOperationException(
                    "Multi types relationship attribute generators cannot set this parameter");
            }

        };

        AttributeGenerator<?> attributeGenerator = attributeGeneratorFactory.create(config.getField(), proxyMultiGenerators,
            relationshipConfiguration);

        this.attributesGenerator.put(attributeName, attributeGenerator);

        return proxyMultiGenerators;
    }

}
