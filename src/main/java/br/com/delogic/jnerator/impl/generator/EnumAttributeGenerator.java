package br.com.delogic.jnerator.impl.generator;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class EnumAttributeGenerator implements AttributeGenerator<Object> {

    private List<?> enums;
    private Random random;

    public EnumAttributeGenerator(Field field) {
        enums = Arrays.asList(field.getType().getEnumConstants());
        random = new Random();
    }

    public Enum<?> generate(int index, AttributeConfiguration attributeConfiguration) {
        return (Enum<?>) enums.get(random.nextInt(enums.size()));
    }

}
