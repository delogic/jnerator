package br.com.delogic.jnerator.impl.generator;

import java.util.Random;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class BooleanAttributeGenerator implements AttributeGenerator {

    private Random random = new Random();

    public <T> Boolean generate(int index, AttributeConfiguration<T> attributeConfiguration, Object instance) {
        return random.nextBoolean();
    }

}
