package br.com.delogic.jnerator.impl.generator;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class DoubleAttributeGenerator implements AttributeGenerator<Double, Object> {

    public Double generate(int index, AttributeConfiguration attributeConfiguration, Object instance) {
        return (double) index;
    }

}
