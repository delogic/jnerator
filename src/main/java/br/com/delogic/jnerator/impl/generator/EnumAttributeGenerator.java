package br.com.delogic.jnerator.impl.generator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class EnumAttributeGenerator implements AttributeGenerator {

    private List<?> enums;
    private Random  random;

    public EnumAttributeGenerator(Class<?> type) {
        enums = Arrays.asList(type.getEnumConstants());
        random = new Random();
    }

    public <T> Enum<?> generate(int index, AttributeConfiguration<T> attributeConfiguration, Object instance) {
        return (Enum<?>) enums.get(random.nextInt(enums.size()));
    }

}
