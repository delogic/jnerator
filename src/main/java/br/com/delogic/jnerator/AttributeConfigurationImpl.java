package br.com.delogic.jnerator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import br.com.delogic.jfunk.Has;
import br.com.delogic.jnerator.impl.generator.LoremIpsumAttributeGenerator;
import br.com.delogic.jnerator.impl.generator.ProvidedAttributeGenerator;
import br.com.delogic.jnerator.util.Util;

public class AttributeConfigurationImpl<T> implements AttributeConfiguration<T> {

    private final InstanceGenerator<T> instanceGenerator;
    private final String               name;
    private final Field                field;
    private Number                     from;
    private Number                     to;

    public AttributeConfigurationImpl(String name, Field field, InstanceGenerator<T> instanceGenerator) {
        this.name = name;
        this.field = field;
        this.instanceGenerator = instanceGenerator;
    }

    public String getName() {
        return name;
    }

    public Field getField() {
        return field;
    }

    public Number getFrom() {
        return from;
    }

    public void setFrom(Number from) {
        this.from = from;
    }

    public Number getTo() {
        return to;
    }

    public void setTo(Number to) {
        this.to = to;
    }

    public <E> InstanceGenerator<T> use(AttributeGenerator<E> attributeGenerator) {
        instanceGenerator.setAttributeGenerator(name, attributeGenerator);
        return instanceGenerator;
    }

    public <E> InstanceGenerator<T> use(final E entity) {
        instanceGenerator.setAttributeGenerator(name, new ProvidedAttributeGenerator<E>(entity));
        return instanceGenerator;
    }

    public <E> InstanceGenerator<T> use(Collection<E> entities) {
        instanceGenerator.setAttributeGenerator(name, new ProvidedAttributeGenerator<E>(new ArrayList<E>(entities)));
        return instanceGenerator;
    }

    public <E> InstanceGenerator<T> use(E... entities) {
        instanceGenerator.setAttributeGenerator(name, new ProvidedAttributeGenerator<E>(entities));
        return instanceGenerator;
    }

    public <E> InstanceGenerator<T> useSequentially(Collection<E> entities) {
        return useSequentially(entities.toArray());
    }

    public <E> InstanceGenerator<T> useSequentially(final E... entities) {
        if (!Has.content(entities)) {
            throw new IllegalArgumentException("Collection of entities is empty");
        }
        return instanceGenerator.setAttributeGenerator(name, new AttributeGenerator<E>() {
            public E generate(int index, AttributeConfiguration attributeConfiguration, Object instance) {
                return entities[Util.validIndex(index, entities.length)];
            }
        });
    }

    @SuppressWarnings("unchecked")
    public <E> InstanceGenerator<T> useLoremIpsum(int length) {
        AttributeGenerator<E> gen = (AttributeGenerator<E>) new LoremIpsumAttributeGenerator(length);
        return instanceGenerator.setAttributeGenerator(name, gen);
    }

    public <N extends Number> InstanceGenerator<T> use(N from, N to) {
        this.from = from;
        this.to = to;
        return instanceGenerator;
    }

}
