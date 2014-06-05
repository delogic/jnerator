package br.com.delogic.jnerator.impl.generator;

import java.util.Random;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class BooleanAttributeGenerator implements AttributeGenerator<Boolean> {

    private Random random = new Random();

    public Boolean generate(int index, AttributeConfiguration attributeConfiguration, Object instance) {
        return random.nextBoolean();
    }

}
