package br.com.delogic.jnerator;

import java.util.List;

public interface InstanceGenerator<E> {

    List<E> generate(int amount);

    List<E> getCachedInstances();

}
