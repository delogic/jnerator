package br.com.delogic.jnerator;


public interface JNerator {

    <E> Generator<E> prepare(Class<E> type);

}
