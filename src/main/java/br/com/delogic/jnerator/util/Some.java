package br.com.delogic.jnerator.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.delogic.jfunk.Has;

public class Some {

    public static <E> List<E> sublistOf(Collection<E> entities, int minimum, int maximum) {
        if (!Has.content(entities) || entities.size() < minimum) {
            throw new IllegalArgumentException("Minimum cannot be bigger than list size");
        }
        List<E> elements = new ArrayList<E>(entities);
        while (elements.size() > maximum) {
            elements.remove(Util.nextInt(elements.size()));
        }
        int iterations = Util.nextInt(maximum - minimum + 1);
        while (iterations-- > 0) {
            elements.remove(Util.nextInt(elements.size()));
        }
        return elements;
    }

    public static <E> Set<E> subsetOf(Collection<E> entities, int minimum, int maximum) {
        if (!Has.content(entities) || entities.size() < minimum) {
            throw new IllegalArgumentException("Minimum cannot be bigger than list size");
        }
        // just remove duplicates and still use list for easier manipulation
        List<E> elements = new ArrayList<E>(new HashSet<E>(entities));
        while (elements.size() > maximum) {
            elements.remove(Util.nextInt(elements.size()));
        }
        int iterations = Util.nextInt(maximum - minimum + 1);
        while (iterations-- > 0) {
            elements.remove(Util.nextInt(elements.size()));
        }
        return new HashSet<E>(elements);
    }

}
