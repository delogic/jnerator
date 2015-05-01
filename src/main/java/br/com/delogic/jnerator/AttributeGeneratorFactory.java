package br.com.delogic.jnerator;

import java.lang.reflect.Field;

public interface AttributeGeneratorFactory {

    AttributeGenerator create(Field field, InstanceGenerator<?> generator);

    <E> AttributeGenerator create(Field field, InstanceGenerator<E> generator,
        RelationshipConfiguration relationshipConfiguration);

    <E> void addInstanceGenerator(InstanceGenerator<E> instanceGenerator, Class<?> type);

}
