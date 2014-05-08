package br.com.delogic.jnerator.impl.generator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class ProvidedAttributeGenerator<E, T> implements AttributeGenerator<E, T> {

    private List<E> elements;
    private Random  random = new Random();

    public ProvidedAttributeGenerator(E... elements) {
        this.elements = Arrays.asList(elements);
    }

    public E generate(int index, AttributeConfiguration attributeConfiguration, T instance) {
        return elements.get(random.nextInt(elements.size()));
    }

}
