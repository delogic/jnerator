package br.com.delogic.jnerator.impl.generator;

import java.math.BigInteger;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class BigIntegerAttributeGenerator implements AttributeGenerator<BigInteger> {

    public BigInteger generate(int index, AttributeConfiguration attributeConfiguration) {
        return new BigInteger((attributeConfiguration.getName() + index).getBytes());
    }

}
