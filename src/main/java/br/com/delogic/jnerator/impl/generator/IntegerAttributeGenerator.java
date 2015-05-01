package br.com.delogic.jnerator.impl.generator;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;
import br.com.delogic.jnerator.util.Util;

public class IntegerAttributeGenerator implements AttributeGenerator<Integer> {

    public Integer generate(int index, AttributeConfiguration attributeConfiguration, Object instance) {
        if (attributeConfiguration.getFrom() != null && attributeConfiguration.getTo() != null){
            int from = attributeConfiguration.getFrom().intValue(), to = attributeConfiguration.getTo().intValue();
            if (from >= to){
                throw new IllegalArgumentException("Number From must be less than number To");
            }
            int number = Util.nextInt(to);
            if (number < from){
                number += from;
            }
            return number;
        }
        return index;
    }

}
