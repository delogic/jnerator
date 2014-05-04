package br.com.delogic.jnerator.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtils {

    public static List<Field> getAllDeclaredFields(Class<?> type) {

        if (type == null) {
            // if this is Object or Void or null or primitive
            return new ArrayList<Field>();
        }

        List<Field> fields = getAllDeclaredFields(type.getSuperclass());

        for (Field field : type.getDeclaredFields()) {
            fields.add(field);
        }

        return fields;
    }

    public static final Field getField(Class<?> type, String name) {
        try {
            Field field = type.getDeclaredField(name);
            return field;
        } catch (SecurityException e) {
            throw new IllegalStateException(e);
        } catch (NoSuchFieldException e) {
            for (Field field : getAllDeclaredFields(type)) {
                if (field.getName().equals(name)) {
                    return field;
                }
            }
            throw new IllegalArgumentException("There's no field " + name + " on type " + type);
        }

    }

    public static final Type getFirstGenericType(Field field) {
        Type genericFieldType = field.getGenericType();

        if (genericFieldType instanceof ParameterizedType) {
            ParameterizedType aType = (ParameterizedType) genericFieldType;
            Type[] fieldArgTypes = aType.getActualTypeArguments();
            if (fieldArgTypes.length > 0) {
                return fieldArgTypes[0];
            }
        }

        throw new IllegalStateException("Can't find the generic type for field " + field);

    }

    public static <E> E instantiate(Class<E> type) {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not instantiate" + type
                + ". Make sure this type follows the Javabeans conventions to be instantiated.", e);
        }
    }

}
