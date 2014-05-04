package br.com.delogic.jnerator;

import java.lang.reflect.Field;

public class AttributeConfiguration {

    private final String name;
    private final Field  field;

    public AttributeConfiguration(String name, Field field) {
        this.name = name;
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public Field getField() {
        return field;
    }

}
