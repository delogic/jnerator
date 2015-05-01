package br.com.delogic.jnerator;

import java.lang.reflect.Field;
import java.util.Collection;

public interface AttributeConfiguration<T> {

    String getName();

    Field getField();

    Number getFrom();

    Number getTo();

    InstanceGenerator<T> use(AttributeGenerator attributeGenerator);

    <E> InstanceGenerator<T> use(E entity);

    <E> InstanceGenerator<T> use(Collection<E> entities);

    <E> InstanceGenerator<T> use(E... entities);

    <E> InstanceGenerator<T> useSequentially(Collection<E> entities);

    <E> InstanceGenerator<T> useSequentially(E... entities);

    <E> InstanceGenerator<T> useLoremIpsum(int lenght);

    <N extends Number> InstanceGenerator<T> use(N from, N to);

}