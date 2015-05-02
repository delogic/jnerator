package br.com.delogic.jnerator.impl.generator;

import java.lang.reflect.Field;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;
import br.com.delogic.jnerator.util.ReflectionUtils;

public class EmailAttributeGenerator implements AttributeGenerator {

    private final String fromAttributeName;

    public EmailAttributeGenerator(String fromAttributeName) {
        this.fromAttributeName = fromAttributeName;
    }

    public <T> String generate(int index, AttributeConfiguration<T> attributeConfiguration, Object instance) {
        try {
            Field field  = ReflectionUtils.getField(instance.getClass(), fromAttributeName);
            if (!field.isAccessible()){
                field.setAccessible(true);
            }
            return String.valueOf(field.get(instance)).replace(" ", ".").toLowerCase() + "@email" + index + ".com";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
