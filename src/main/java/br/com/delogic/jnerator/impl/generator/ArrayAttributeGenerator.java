package br.com.delogic.jnerator.impl.generator;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;

public class ArrayAttributeGenerator implements AttributeGenerator<Object, Object> {

    private Class<?>                           componentType;
    private AttributeGenerator<?, Object> attributeGenerator;

    public ArrayAttributeGenerator(Field field, AttributeGenerator<?, Object> attributeGenerator) {
        this.componentType = field.getType().getComponentType();
        this.attributeGenerator = attributeGenerator;
    }

    public Object generate(int index, AttributeConfiguration attributeConfiguration, Object object) {

        int amount = 10;

        Object array = Array.newInstance(componentType, amount);

        for (int i = 0; i < amount; i++) {
            Array.set(array, i, attributeGenerator.generate(i, attributeConfiguration, object));
        }

        return array;
    }

}
