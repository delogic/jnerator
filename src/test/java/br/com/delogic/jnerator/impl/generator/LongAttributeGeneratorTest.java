package br.com.delogic.jnerator.impl.generator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.delogic.jnerator.AttributeConfigurationImpl;

public class LongAttributeGeneratorTest extends Assert {

    private LongAttributeGenerator             generator;
    private Long                               long_;
    private AttributeConfigurationImpl<Object> attributeConfig;

    @Before
    public void init() {
        generator = new LongAttributeGenerator();
    }

    @Test
    public void shouldGenerateLongUsingIndex() {
        givenAttributeConfigurationWithoutLimit();
        whenGenerating(1);
        thenNumberGeneratedIs(1L);

        whenGenerating(15);
        thenNumberGeneratedIs(15L);

        whenGenerating(1667);
        thenNumberGeneratedIs(1667L);
    }

    private void givenAttributeConfigurationWithoutLimit() {
        attributeConfig = new AttributeConfigurationImpl<Object>("name", null, null);
    }

    private void whenGenerating(int i) {
        long_ = generator.generate(i, attributeConfig, null);
    }

    private void thenNumberGeneratedIs(Long expected) {
        assertEquals(expected, long_);
    }

    @Test
    public void shouldGenerateRandomLongUsingBoundaries() {
        givenAttributeConfigurationWithLimit(10L, 50L);
        whenGenerating(5);
        thenNumberGeneratedAreBetween(10, 50);
    }

    private void givenAttributeConfigurationWithLimit(Long from, Long to) {
        attributeConfig = new AttributeConfigurationImpl<Object>("name", null, null);
        attributeConfig.setFrom(from);
        attributeConfig.setTo(to);
    }

    private void thenNumberGeneratedAreBetween(long i, long j) {
        assertTrue(long_ >= i && long_ <= j);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenToIsLessThanFrom() {
        givenAttributeConfigurationWithLimit(50L, 49L);
        whenGenerating(5);
        fail("Should had thrown illegalargumentexception");
    }

    @Test
    public void shouldGenerateUsingIndexIfOneAttributeIsNull() {
        givenAttributeConfigurationWithLimit(null, 50L);
        whenGenerating(0);
        thenNumberGeneratedIs(0L);

        givenAttributeConfigurationWithLimit(50L, null);
        whenGenerating(1);
        thenNumberGeneratedIs(1L);

        givenAttributeConfigurationWithLimit(null, null);
        whenGenerating(2);
        thenNumberGeneratedIs(2L);
    }

}
