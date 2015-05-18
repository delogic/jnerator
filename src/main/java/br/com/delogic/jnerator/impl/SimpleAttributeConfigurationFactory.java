package br.com.delogic.jnerator.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import br.com.delogic.jnerator.AttributeConfigurationFactory;
import br.com.delogic.jnerator.AttributeConfigurationImpl;
import br.com.delogic.jnerator.InstanceGenerator;
import br.com.delogic.jnerator.util.ReflectionUtils;

public class SimpleAttributeConfigurationFactory implements AttributeConfigurationFactory {

    public <T> List<AttributeConfigurationImpl<T>> create(Class<?> type, InstanceGenerator<T> instanceGenerator) {

        List<Field> fields = ReflectionUtils.getAllDeclaredFields(type);

        List<AttributeConfigurationImpl<T>> attrs = new ArrayList<AttributeConfigurationImpl<T>>();

        for (Field field : fields) {
            if (!Modifier.isTransient(field.getModifiers()) &&
                !Modifier.isFinal(field.getModifiers()) &&
                !field.getName().startsWith("_persistence_")) {
                // fields1
                attrs.add(new AttributeConfigurationImpl<T>(field.getName(), field, instanceGenerator));
            }
        }

        return attrs;
    }

}
