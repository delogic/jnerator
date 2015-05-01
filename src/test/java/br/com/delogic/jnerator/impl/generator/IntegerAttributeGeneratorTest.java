package br.com.delogic.jnerator.impl.generator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.delogic.jnerator.AttributeConfigurationImpl;

public class IntegerAttributeGeneratorTest extends Assert {

    private IntegerAttributeGenerator generator;
    private Integer                   integer;
    private AttributeConfigurationImpl    attributeConfig;

    @Before
    public void init() {
        generator = new IntegerAttributeGenerator();
    }

    @Test
    public void shouldGenerateIntegerUsingIndex() {
        givenAttributeConfigurationWithoutLimit();
        whenGenerating(1);
        thenNumberGeneratedIs(1);

        whenGenerating(15);
        thenNumberGeneratedIs(15);

        whenGenerating(1667);
        thenNumberGeneratedIs(1667);
    }

    private void givenAttributeConfigurationWithoutLimit() {
        attributeConfig = new AttributeConfigurationImpl("name", null, null);
    }

    private void whenGenerating(int i) {
        integer = generator.generate(i, attributeConfig, null);
    }

    private void thenNumberGeneratedIs(Integer expected) {
        assertEquals(expected, integer);
    }

    @Test
    public void shouldGenerateRandomIntegerUsingBoundaries() {
        givenAttributeConfigurationWithLimit(10, 50);
        whenGenerating(5);
        thenNumberGeneratedAreBetween(10, 50);
    }

    private void givenAttributeConfigurationWithLimit(Integer from, Integer to) {
        attributeConfig = new AttributeConfigurationImpl("name", null, null);
        attributeConfig.setFrom(from);
        attributeConfig.setTo(to);
    }

    private void thenNumberGeneratedAreBetween(int i, int j) {
        assertTrue(integer >= i && integer <= j);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenToIsLessThanFrom() {
        givenAttributeConfigurationWithLimit(50, 49);
        whenGenerating(5);
        fail("Should had thrown illegalargumentexception");
    }

    @Test
    public void shouldGenerateUsingIndexIfOneAttributeIsNull() {
        givenAttributeConfigurationWithLimit(null, 50);
        whenGenerating(0);
        thenNumberGeneratedIs(0);

        givenAttributeConfigurationWithLimit(50, null);
        whenGenerating(1);
        thenNumberGeneratedIs(1);

        givenAttributeConfigurationWithLimit(null, null);
        whenGenerating(2);
        thenNumberGeneratedIs(2);
    }

}
