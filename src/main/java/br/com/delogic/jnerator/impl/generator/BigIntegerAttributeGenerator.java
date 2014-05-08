package br.com.delogic.jnerator.impl.generator;

import java.math.BigInteger;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class BigIntegerAttributeGenerator implements AttributeGenerator<BigInteger, Object> {

    public BigInteger generate(int index, AttributeConfiguration attributeConfiguration, Object instance) {
        return new BigInteger((attributeConfiguration.getName() + index).getBytes());
    }

}
