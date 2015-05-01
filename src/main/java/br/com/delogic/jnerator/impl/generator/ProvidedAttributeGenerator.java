package br.com.delogic.jnerator.impl.generator;

import java.util.Arrays;
import java.util.List;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;
import br.com.delogic.jnerator.util.Util;

public class ProvidedAttributeGenerator<E> implements AttributeGenerator {

    private E       element;
    private List<E> elements;

    public ProvidedAttributeGenerator(E element) {
        this.element = element;
    }

    public ProvidedAttributeGenerator(E... elements) {
        this.elements = Arrays.asList(elements);
    }

    public ProvidedAttributeGenerator(List<E> elements) {
        this.elements = elements;
    }

    public <T> Object generate(int index, AttributeConfiguration<T> attributeConfiguration, Object instance) {
        if (element != null) {
            return element;
        }
        return elements.get(Util.validIndex(index, elements.size()));
    }

}
