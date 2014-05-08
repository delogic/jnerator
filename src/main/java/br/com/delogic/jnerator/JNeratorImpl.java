package br.com.delogic.jnerator;

import br.com.delogic.jnerator.impl.SimpleAttributeConfigurationFactory;
import br.com.delogic.jnerator.impl.SimpleAttributeGeneratorFactory;
import br.com.delogic.jnerator.impl.SimpleInstanceGenerator;

public class JNeratorImpl implements JNerator {

    private AttributeGeneratorFactory attributeGeneratorFactory = new SimpleAttributeGeneratorFactory();

    public <E> InstanceGenerator<E> prepare(Class<E> type) {
        SimpleInstanceGenerator<E> instanceGenerator =
            new SimpleInstanceGenerator<E>(
                type,
                new SimpleAttributeConfigurationFactory(),
                attributeGeneratorFactory,
                new SimpleRelationshipConfigurationFactory(),
                this);

        attributeGeneratorFactory.addInstanceGenerator(instanceGenerator, type);

        return instanceGenerator;
    }

}
