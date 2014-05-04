package br.com.delogic.jnerator;


public interface JNerator {

    <E> InstanceGenerator<E> prepare(Class<E> type);

}
