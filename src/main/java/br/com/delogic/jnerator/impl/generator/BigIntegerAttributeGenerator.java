package br.com.delogic.jnerator.impl.generator;

import java.math.BigInteger;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class BigIntegerAttributeGenerator implements AttributeGenerator {

    public <T> BigInteger generate(int index, AttributeConfiguration<T> attributeConfiguration, Object instance) {
        return new BigInteger((attributeConfiguration.getName() + index).getBytes());
    }

}
