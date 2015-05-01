package br.com.delogic.jnerator.impl.generator;

import org.junit.Assert;
import org.junit.Test;

public class LoremIpsumAttributeGeneratorTest extends Assert {

    private LoremIpsumAttributeGenerator generator;
    private String                       loremIpsum;

    @Test
    public void shouldGenerateExactLoremIpsumLenght() {
        givenLoremIpsumGenerator(500);
        whenGeneratingLoremIpsum();
        thenExactLoremIpsumLenghtIsGenerated(500);
    }

    private void givenLoremIpsumGenerator(int lenght) {
        generator = new LoremIpsumAttributeGenerator(lenght);
    }

    private void whenGeneratingLoremIpsum() {
        loremIpsum = generator.generate(1, null, null);
    }

    private void thenExactLoremIpsumLenghtIsGenerated(int lenght) {
        assertEquals(lenght, loremIpsum.length());
    }

    @Test
    public void shouldGenerateSmallLoremIpsumLenght() {
        givenLoremIpsumGenerator(39);
        whenGeneratingLoremIpsum();
        thenExactLoremIpsumLenghtIsGenerated(39);
    }

    @Test
    public void shouldGenerateHugeLoremIpsumLenght() {
        givenLoremIpsumGenerator(100001);
        whenGeneratingLoremIpsum();
        thenExactLoremIpsumLenghtIsGenerated(100001);
    }

    @Test
    public void shouldGenerateLoremIpsumLenght() {
        givenLoremIpsumGenerator(LoremIpsumAttributeGenerator.LOREM_IPSUM.length());
        whenGeneratingLoremIpsum();
        thenExactLoremIpsumLenghtIsGenerated(LoremIpsumAttributeGenerator.LOREM_IPSUM.length());
    }

    @Test
    public void shouldGenerateMinimunIpsumLenght() {
        givenLoremIpsumGenerator(1);
        whenGeneratingLoremIpsum();
        thenExactLoremIpsumLenghtIsGenerated(1);
    }

}
