package br.com.delogic.jnerator;

import java.util.List;

public interface InstanceGenerator<T> {

    List<T> generate(int amount);

    List<T> getCachedInstances();

    AttributeConfiguration<T> forAttr(String attributeName);

    <E> InstanceGenerator<T> setAttributeGenerator(String attributeName,
        AttributeGenerator attributeGenerator);

    <R> InstanceGenerator<R> forRelationship(String
        attributeName, Class<? extends R> type);

    <R> InstanceGenerator<R> forRelationship(String
        attributeName, List<Class<? extends R>> types);

    InstanceGenerator<T> doNotGenerateAttribute(String... attributeNames);

}
