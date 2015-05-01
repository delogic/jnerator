package br.com.delogic.jnerator.impl.generator;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class DoubleAttributeGenerator implements AttributeGenerator {

    public <T> Double generate(int index, AttributeConfiguration<T> attributeConfiguration, Object instance) {
        return (double) index;
    }

}
