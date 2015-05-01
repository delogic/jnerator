package br.com.delogic.jnerator.impl.generator;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class LongAttributeGenerator implements AttributeGenerator {

    public <T> Long generate(int index, AttributeConfiguration<T> attributeConfiguration, Object instance) {
        return (long) index;
    }

}
