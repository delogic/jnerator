package br.com.delogic.jnerator;

import java.lang.reflect.Field;

public interface AttributeGeneratorFactory {

    AttributeGenerator<?> create(Field field, Generator<?> generator);

}
