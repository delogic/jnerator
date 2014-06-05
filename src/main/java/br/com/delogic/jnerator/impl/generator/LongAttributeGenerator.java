package br.com.delogic.jnerator.impl.generator;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class LongAttributeGenerator implements AttributeGenerator<Long> {

    public Long generate(int index, AttributeConfiguration attributeConfiguration, Object instance) {
        return (long) index;
    }

}
