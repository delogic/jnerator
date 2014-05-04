package br.com.delogic.jnerator.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeConfigurationFactory;
import br.com.delogic.jnerator.util.ReflectionUtils;

public class SimpleAttributeConfigurationFactory implements AttributeConfigurationFactory {

    public List<AttributeConfiguration> create(Class<?> type) {

        List<Field> fields = ReflectionUtils.getAllDeclaredFields(type);

        List<AttributeConfiguration> attrs = new ArrayList<AttributeConfiguration>();

        for (Field field : fields) {
            attrs.add(new AttributeConfiguration(field.getName(), field));
        }

        return attrs;
    }

}
