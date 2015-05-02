package br.com.delogic.jnerator.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class SomeListTest extends Assert {

    private ArrayList<String> list;
    private List<String>      some;
    private HashSet<Integer>  possibilities;

    @Test
    public void shouldGenerateSomeSublistWithZeroOrFour() {
        givenListOfStrings(10);
        whenGettingSome(0, 4);
        thenListIsBetween(0, 4);
    }

    @Test
    public void shouldGenerateSomeSublistWithTwoOrFive() {
        givenListOfStrings(10000);
        whenGettingSome(2, 5);
        thenListIsBetween(2, 5);
        System.out.println(some);
    }

    @Test
    public void shouldGenerateForAllPossibilities() {
        givenListOfStrings(10000);
        whenGettingForAllPossibilitiesBetweenZeroAndTen();
        thenAllPossibilitiesAreMet();
    }

    @Test
    public void shouldNotChangeOriginalListValues() {
        givenListOfStrings(10000);
        whenGettingSome(2, 100);
        thenListValuesAreTheSame(10000);
    }

    private void thenListValuesAreTheSame(int max) {
        for (int i = 0; i < max; i++) {
            assertTrue(list.contains("String" + i));
        }
    }

    private void whenGettingForAllPossibilitiesBetweenZeroAndTen() {
        possibilities = new LinkedHashSet<Integer>();
        while (possibilities.size() < 10) {
            possibilities.add(Some.sublistOf(list, 0, 10).size());
        }
    }

    private void thenAllPossibilitiesAreMet() {
        assertEquals(10, possibilities.size());
        System.out.println(possibilities);
    }

    private void givenListOfStrings(int max) {
        list = new ArrayList<String>();
        for (int i = 0; i < max; i++) {
            list.add("String" + i);
        }
    }

    private void whenGettingSome(int min, int max) {
        some = Some.sublistOf(list, min, max);
    }

    private void thenListIsBetween(int min, int max) {
        assertNotNull(some);
        assertTrue(some.size() >= min && some.size() <= max);
    }

}
