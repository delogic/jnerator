package br.com.delogic.jnerator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import br.com.delogic.jnerator.impl.SimpleAttributeConfigurationFactory;
import br.com.delogic.jnerator.impl.SimpleAttributeGeneratorFactory;
import br.com.delogic.jnerator.impl.SimpleInstanceGenerator;

public class JNeratorImpl implements JNerator {

    private AttributeGeneratorFactory attributeGeneratorFactory = new SimpleAttributeGeneratorFactory();
    private Set<String>               ignoredAttributes         = new HashSet<String>();

    public <E> InstanceGenerator<E> prepare(Class<E> type) {
        SimpleInstanceGenerator<E> instanceGenerator =
            new SimpleInstanceGenerator<E>(
                type,
                new SimpleAttributeConfigurationFactory(),
                attributeGeneratorFactory,
                new SimpleRelationshipConfigurationFactory(),
                this);

        attributeGeneratorFactory.addInstanceGenerator(instanceGenerator, type);

        instanceGenerator.doNotGenerateFor(ignoredAttributes.toArray(new String[ignoredAttributes.size()]));

        return instanceGenerator;
    }

    public JNerator doNotGenerateFor(String... attributeNames) {
        ignoredAttributes.addAll(Arrays.asList(attributeNames));
        return this;
    }

}
