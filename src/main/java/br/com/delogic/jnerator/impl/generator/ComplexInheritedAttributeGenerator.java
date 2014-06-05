package br.com.delogic.jnerator.impl.generator;

import java.util.List;
import java.util.Random;

import br.com.delogic.jnerator.AttributeConfiguration;
import br.com.delogic.jnerator.AttributeGenerator;
import br.com.delogic.jnerator.InstanceGenerator;

public class ComplexInheritedAttributeGenerator implements AttributeGenerator<Object> {

    private final List<InstanceGenerator<?>> instanceGenerators;
    private Random                           random = new Random();

    public ComplexInheritedAttributeGenerator(Class<?> type, List<InstanceGenerator<?>> assinableGenerators) {
        this.instanceGenerators = assinableGenerators;
    }

    public Object generate(int index, AttributeConfiguration attributeConfiguration, Object instance) {
        InstanceGenerator<?> generator = instanceGenerators.get(random.nextInt(instanceGenerators.size()));
        return generator.getCachedInstances().get(random.nextInt(generator.getCachedInstances().size()));
    }

}
