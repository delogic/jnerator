package br.com.delogic.jnerator;

import java.util.List;

public interface Generator<E> {

    List<E> generate(int amount);

}
