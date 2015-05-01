package br.com.delogic.jnerator.impl.generator;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class ArrayAttributeGenerator implements AttributeGenerator {

    private Class<?>           componentType;
    private AttributeGenerator attributeGenerator;

    public ArrayAttributeGenerator(Field field, AttributeGenerator attributeGenerator) {
        this.componentType = field.getType().getComponentType();
        this.attributeGenerator = attributeGenerator;
    }

    public <T> Object generate(int index, AttributeConfiguration<T> attributeConfiguration, Object object) {

        int amount = 10;
        Object array = Array.newInstance(componentType, amount);
        for (int i = 0; i < amount; i++) {
            Array.set(array, i, attributeGenerator.generate(i, attributeConfiguration, object));
        }
        return array;
    }

}
