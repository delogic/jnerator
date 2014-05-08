package br.com.delogic.jnerator.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class convert {

    public static final <E, In extends Collection<E>, Out> List<Out> toList(In in, Into<E, Out> when) {

        List<Out> outs = new ArrayList<Out>();
        for (E obj : in) {
            Out out = when.convert(obj);
            if (out != null) {
                outs.add(out);
            }
        }

        return outs;
    }

    public static interface Into<E, Out> {

        Out convert(E e);

    }

}
