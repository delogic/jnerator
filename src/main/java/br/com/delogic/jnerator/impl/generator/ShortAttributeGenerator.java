package br.com.delogic.jnerator.impl.generator;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;
import br.com.delogic.jnerator.util.Util;

public class ShortAttributeGenerator implements AttributeGenerator {

    public <T> Short generate(int index, AttributeConfiguration<T> attributeConfiguration, Object instance) {
        if (attributeConfiguration.getFrom() != null && attributeConfiguration.getTo() != null){
            int from = attributeConfiguration.getFrom().intValue(), to = attributeConfiguration.getTo().intValue();
            if (from >= to){
                throw new IllegalArgumentException("Number From must be less than number To");
            }
            int number = Util.nextInt(to);
            if (number < from){
                number += from;
            }
            return (short) number;
        }
        return (short) index;
    }

}
