package br.com.delogic.jnerator;

public interface AttributeGenerator<E, T> {

    E generate(int index, AttributeConfiguration attributeConfiguration, T instance);

}
