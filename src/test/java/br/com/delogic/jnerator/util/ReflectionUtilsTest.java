package br.com.delogic.jnerator.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("unused")
public class ReflectionUtilsTest extends Assert {

    /*
     * Fields used locally on test
     */
    private List<String>             _ListOfString;
    private List<Integer>            _ListOfInteger;
    private Set<ReflectionUtilsTest> _SetOfReflectionUtilsTest;

    @Test
    public void testGetField() {
        assertNotNull(ReflectionUtils.getField(ReflectionUtilsTest.class, "_ListOfString"));
    }

    @Test
    public void testGetFirstGenericType() {

        Field field = ReflectionUtils.getField(ReflectionUtilsTest.class, "_ListOfString");
        assertEquals(String.class, ReflectionUtils.getFirstGenericType(field));

        field = ReflectionUtils.getField(ReflectionUtilsTest.class, "_ListOfInteger");
        assertEquals(Integer.class, ReflectionUtils.getFirstGenericType(field));

        field = ReflectionUtils.getField(ReflectionUtilsTest.class, "_SetOfReflectionUtilsTest");
        assertEquals(ReflectionUtilsTest.class, ReflectionUtils.getFirstGenericType(field));

    }

    @Test
    public void testInstantiate() {
        ReflectionUtils.instantiate(String.class);
        ReflectionUtils.instantiate(ArrayList.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateError() {
        ReflectionUtils.instantiate(List.class);
    }

}
