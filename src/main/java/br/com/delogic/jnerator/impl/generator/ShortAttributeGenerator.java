package br.com.delogic.jnerator.impl.generator;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class ShortAttributeGenerator implements AttributeGenerator {

    public <T> Short generate(int index, AttributeConfiguration<T> attributeConfiguration, Object instance) {
        return (short) index;
    }

}
