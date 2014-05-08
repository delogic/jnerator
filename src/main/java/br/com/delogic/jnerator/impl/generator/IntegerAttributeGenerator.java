package br.com.delogic.jnerator.impl.generator;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class IntegerAttributeGenerator implements AttributeGenerator<Integer, Object> {

    public Integer generate(int index, AttributeConfiguration attributeConfiguration, Object instance) {
        return index;
    }

}
