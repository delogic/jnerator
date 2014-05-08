package br.com.delogic.jnerator.impl.generator;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;
import br.com.delogic.jnerator.InstanceGenerator;

public class ComplexTypeAttributeGenerator implements AttributeGenerator<Object, Object> {

    private final InstanceGenerator<?> instanceGenerator;
    private final Random               random = new Random();

    public ComplexTypeAttributeGenerator(Field field, InstanceGenerator<?> instanceGenerator) {
        this.instanceGenerator = instanceGenerator;
    }

    public Object generate(int index, AttributeConfiguration attributeConfiguration, Object instance) {
        List<?> entitiesCached = instanceGenerator.getCachedInstances();
        return entitiesCached.get(random.nextInt(entitiesCached.size()));
    }

}
