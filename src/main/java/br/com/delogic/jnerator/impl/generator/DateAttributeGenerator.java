package br.com.delogic.jnerator.impl.generator;

import java.util.Date;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class DateAttributeGenerator implements AttributeGenerator<Date, Object> {

    public Date generate(int index, AttributeConfiguration attributeConfiguration, Object instance) {
        return new Date();
    }

}
