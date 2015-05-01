package br.com.delogic.jnerator.impl.generator;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class ByteAttributeGenerator implements AttributeGenerator {

    public <T> Byte generate(int index, AttributeConfiguration<T> attributeConfiguration, Object instance) {
        return (byte) index;
    }

}
