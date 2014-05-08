package br.com.delogic.jnerator.util;

import java.util.Collection;

public class find {

    public static <E> E first(Collection<E> col, When<E> when) {
        for (E e : col) {
            if (when.found(e)) {
                return e;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <E, C extends Collection<E>> C all(C col, When<E> when) {
        C newCol = (C) instantiate(col.getClass());
        for (E e : col) {
            if (when.found(e)) {
                newCol.add(e);
            }
        }
        return newCol;
    }

    private static <T> T instantiate(Class<T> type) {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Impossible to create this object:" + type, e);
        }
    }

    public static interface When<E> {

        boolean found(E e);

    }

}
