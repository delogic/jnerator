package br.com.delogic.jnerator.impl.generator;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class StringAttributeGenerator implements AttributeGenerator<String> {

    public String generate(int index, AttributeConfiguration attributeConfiguration, Object instance) {
        return attributeConfiguration.getName() + " " + index;
    }

}
