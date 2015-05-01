package br.com.delogic.jnerator;

public interface AttributeGenerator {

    <T> Object generate(int index, AttributeConfiguration<T> attributeConfiguration, Object instance);

}
