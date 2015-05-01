package br.com.delogic.jnerator.impl.generator;

import java.math.BigDecimal;
import java.math.BigInteger;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class BigDecimalAttributeGenerator implements AttributeGenerator {

    public <T> BigDecimal generate(int index, AttributeConfiguration<T> attributeConfiguration, Object object) {
        return new BigDecimal(new BigInteger((attributeConfiguration.getName() + index).getBytes()));
    }

}
