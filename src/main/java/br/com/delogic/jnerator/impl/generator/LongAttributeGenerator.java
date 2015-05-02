package br.com.delogic.jnerator.impl.generator;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;
import br.com.delogic.jnerator.util.Util;

public class LongAttributeGenerator implements AttributeGenerator {

    public <T> Long generate(int index, AttributeConfiguration<T> attributeConfiguration, Object instance) {
        if (attributeConfiguration.getFrom() != null && attributeConfiguration.getTo() != null) {
            long from = attributeConfiguration.getFrom().longValue(), to = attributeConfiguration.getTo().longValue();
            if (from >= to) {
                throw new IllegalArgumentException("Number From must be less than number To");
            }
            //TODO Must use long generator
            long number = Util.nextInt((int) to);
            if (number < from) {
                number += from;
            }
            return number;
        }
        return (long) index;
    }

}
