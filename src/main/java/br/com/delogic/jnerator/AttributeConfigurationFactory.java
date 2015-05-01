package br.com.delogic.jnerator;

import java.util.List;

public interface AttributeConfigurationFactory {

    <T> List<AttributeConfigurationImpl<T>> create(Class<?> type, InstanceGenerator<T> instanceGenerator);

}
