package br.com.delogic.jnerator;

public interface AttributeGenerator<E> {

    E generate(int index, AttributeConfiguration attributeConfiguration);

}
