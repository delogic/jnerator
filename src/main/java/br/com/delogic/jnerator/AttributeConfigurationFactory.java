package br.com.delogic.jnerator;

import java.util.List;

public interface AttributeConfigurationFactory {

    List<AttributeConfiguration> create(Class<?> type);

}
