package br.com.delogic.jnerator.impl.generator;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class FloatAttributeGenerator implements AttributeGenerator {

    public <T> Float generate(int index, AttributeConfiguration<T> attributeConfiguration, Object instance) {
        return (float) index;
    }

}
