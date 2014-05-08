package br.com.delogic.jnerator;

import java.lang.reflect.Field;

public interface RelationshipConfigurationFactory {

    RelationshipConfiguration create(Field field);

}
