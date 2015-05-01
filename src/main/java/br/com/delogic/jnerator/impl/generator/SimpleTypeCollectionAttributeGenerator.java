package br.com.delogic.jnerator.impl.generator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;
import br.com.delogic.jnerator.util.ReflectionUtils;

public class SimpleTypeCollectionAttributeGenerator implements AttributeGenerator {

    private final Field              field;
    private final AttributeGenerator simpleAttributeGenerator;

    public SimpleTypeCollectionAttributeGenerator(Field field, AttributeGenerator attributeGenerator) {
        this.field = field;
        this.simpleAttributeGenerator = attributeGenerator;
    }

    public <T> Collection<?> generate(int index, AttributeConfiguration<T> attributeConfiguration, Object instance) {

        Collection<Object> collection = createCollection(field);
        for (int i = 0; i < 5; i++) {
            collection.add(simpleAttributeGenerator.generate(i, attributeConfiguration, instance));
        }
        return collection;
    }

    @SuppressWarnings("unchecked")
    <E> Collection<E> createCollection(Field field) {

        Class<Collection<E>> type = (Class<Collection<E>>) field.getType();

        if (type.isInterface()) {
            if (List.class.isAssignableFrom(type)) {
                return new ArrayList<E>();
            }
            if (Set.class.isAssignableFrom(type)) {
                return new HashSet<E>();
            }
            throw new IllegalArgumentException(
                "Currently only List and Set are supported as Collection types. The following field is not supported:" + field);
        }

        return (Collection<E>) ReflectionUtils.instantiate(type);

    }

}
