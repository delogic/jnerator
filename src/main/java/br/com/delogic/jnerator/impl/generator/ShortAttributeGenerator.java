package br.com.delogic.jnerator.impl.generator;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class ShortAttributeGenerator implements AttributeGenerator<Short, Object> {

    public Short generate(int index, AttributeConfiguration attributeConfiguration, Object instance) {
        return (short) index;
    }

}
