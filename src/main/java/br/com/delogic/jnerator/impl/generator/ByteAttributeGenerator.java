package br.com.delogic.jnerator.impl.generator;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class ByteAttributeGenerator implements AttributeGenerator<Byte> {

    public Byte generate(int index, AttributeConfiguration attributeConfiguration, Object instance) {
        return (byte) index;
    }

}
