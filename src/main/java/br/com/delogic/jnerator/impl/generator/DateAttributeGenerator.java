package br.com.delogic.jnerator.impl.generator;

import java.util.Date;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;
import br.com.delogic.jnerator.util.RandomUtil;

public class DateAttributeGenerator implements AttributeGenerator {

    public <T> Date generate(int index, AttributeConfiguration<T> attributeConfiguration, Object instance) {
        return RandomUtil.getDateBetween(1);
    }

}
