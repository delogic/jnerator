package br.com.delogic.jnerator;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import br.com.delogic.jnerator.util.ReflectionUtils;

public class SimpleRelationshipConfigurationFactory implements RelationshipConfigurationFactory {

    private enum Types {
        ONE,
        MANY;
    }

    public RelationshipConfiguration create(Field field) {
        Class<?> ownerType = field.getDeclaringClass();
        Class<?> ownedType = field.getType();
        if (Collection.class.isAssignableFrom(field.getType())){
            ownedType = (Class<?>) ReflectionUtils.getFirstGenericType(field);
        } else if (field.getType().isArray()){
            ownedType = field.getType().getComponentType();
        }

        String ownedAttributeName = field.getName();
        String ownedOwnerAttributeName = findOwnedOwnerAttributeName(field);

        Types owner = findOwnerType(field);
        Types owned = findOwnedType(field);

        RelationshipType relationshipType = findRelationshipType(owner, owned);

        return new RelationshipConfiguration(relationshipType, ownerType, ownedType, ownedAttributeName, ownedOwnerAttributeName);
    }

    private String findOwnedOwnerAttributeName(Field field) {
        Class<?> owner = field.getDeclaringClass();

        List<Field> ownedAttributes = getAllDeclaredFields(field);
        for (Field f : ownedAttributes) {
            if (f.getType().isAssignableFrom(owner)
                || (Collection.class.isAssignableFrom(f.getType()) && ReflectionUtils.getFirstGenericType(f).equals(owner))
                || (f.getType().isArray() && f.getType().getComponentType().isAssignableFrom(owner))) {
                return f.getName();
            }
        }

        throw new IllegalStateException(String.format(
            "Could not find relationship between %s and %s. Are you sure this is a relationship ?", field.getType(),
            field.getDeclaringClass()));

    }

    private List<Field> getAllDeclaredFields(Field field) {
        if (Collection.class.isAssignableFrom(field.getType())){
            return ReflectionUtils.getAllDeclaredFields((Class<?>) ReflectionUtils.getFirstGenericType(field));
        } else if (field.getType().isArray()) {
            return ReflectionUtils.getAllDeclaredFields(field.getType().getComponentType());
        }
        return ReflectionUtils.getAllDeclaredFields(field.getType());
    }

    private RelationshipType findRelationshipType(Types owner, Types owned) {
        if (owner == Types.ONE && owned == Types.ONE) {
            return RelationshipType.ONE_TO_ONE;
        } else if (owner == Types.ONE && owned == Types.MANY) {
            return RelationshipType.ONE_TO_MANY;
        } else if (owned == Types.ONE) {
            return RelationshipType.MANY_TO_ONE;
        } else {
            return RelationshipType.MANY_TO_MANY;
        }
    }

    private Types findOwnedType(Field field) {
        return field.getType().isArray() || Collection.class.isAssignableFrom(field.getType()) ? Types.MANY : Types.ONE;
    }

    private Types findOwnerType(Field field) {
        Class<?> owner = field.getDeclaringClass();
        List<Field> ownedAttributes = getAllDeclaredFields(field);
        for (Field f : ownedAttributes) {
            if (f.getType().isAssignableFrom(owner)) {
                return Types.ONE;
            } else if ((Collection.class.isAssignableFrom(f.getType()) && ReflectionUtils.getFirstGenericType(f).equals(owner))
                || (f.getType().isArray() && f.getType().getComponentType().isAssignableFrom(owner))) {
                return Types.MANY;
            }
        }

        throw new IllegalStateException(String.format(
            "Could not find relationship between %s and %s. Are you sure this is a relationship ?", field.getType(),
            field.getDeclaringClass()));

    }
}
