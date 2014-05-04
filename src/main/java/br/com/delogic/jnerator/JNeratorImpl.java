package br.com.delogic.jnerator;

import br.com.delogic.jnerator.impl.SimpleAttributeConfigurationFactory;
import br.com.delogic.jnerator.impl.SimpleAttributeGeneratorFactory;
import br.com.delogic.jnerator.impl.SimpleInstanceGenerator;

public class JNeratorImpl implements JNerator {

    public <E> Generator<E> prepare(Class<E> type) {
        return new SimpleInstanceGenerator<E>(type, new SimpleAttributeConfigurationFactory(), new SimpleAttributeGeneratorFactory());
    }

}
