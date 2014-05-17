package br.com.delogic.jnerator.util;

import org.junit.Assert;
import org.junit.Test;

public class UtilTest extends Assert{

    @Test
    public void testValidIndex(){
        assertEquals(0, Util.validIndex(0, 5));
        assertEquals(1, Util.validIndex(1, 5));
        assertEquals(2, Util.validIndex(2, 5));
        assertEquals(3, Util.validIndex(3, 5));
        assertEquals(4, Util.validIndex(4, 5));
        assertEquals(0, Util.validIndex(5, 5));
        assertEquals(1, Util.validIndex(6, 5));
        assertEquals(2, Util.validIndex(7, 5));
        assertEquals(3, Util.validIndex(8, 5));

    }

}
