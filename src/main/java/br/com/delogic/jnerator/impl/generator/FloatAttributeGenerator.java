package br.com.delogic.jnerator.impl.generator;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class FloatAttributeGenerator implements AttributeGenerator<Float> {

    public Float generate(int index, AttributeConfiguration attributeConfiguration) {
        return (float) index;
    }

}
