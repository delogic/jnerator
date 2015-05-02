package br.com.delogic.jnerator.impl.generator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.delogic.jnerator.AttributeConfigurationImpl;

public class ShortAttributeGeneratorTest extends Assert {

    private ShortAttributeGenerator            generator;
    private Short                              short_;
    private AttributeConfigurationImpl<Object> attributeConfig;

    @Before
    public void init() {
        generator = new ShortAttributeGenerator();
    }

    @Test
    public void shouldGenerateShortUsingIndex() {
        givenAttributeConfigurationWithoutLimit();
        whenGenerating((short) 1);
        thenNumberGeneratedIs((short) 1);

        whenGenerating((short) 15);
        thenNumberGeneratedIs((short) 15);

        whenGenerating((short) 1667);
        thenNumberGeneratedIs((short) 1667);
    }

    private void givenAttributeConfigurationWithoutLimit() {
        attributeConfig = new AttributeConfigurationImpl("name", null, null);
    }

    private void whenGenerating(short i) {
        short_ = generator.generate(i, attributeConfig, null);
    }

    private void thenNumberGeneratedIs(Short expected) {
        assertEquals(expected, short_);
    }

    @Test
    public void shouldGenerateRandomIntegerUsingBoundaries() {
        givenAttributeConfigurationWithLimit((short) 10, (short) 50);
        whenGenerating((short) 5);
        thenNumberGeneratedAreBetween((short) 10, (short) 50);
    }

    private void givenAttributeConfigurationWithLimit(Short from, Short to) {
        attributeConfig = new AttributeConfigurationImpl<Object>("name", null, null);
        attributeConfig.setFrom(from);
        attributeConfig.setTo(to);
    }

    private void thenNumberGeneratedAreBetween(short i, short j) {
        assertTrue(short_ >= i && short_ <= j);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenToIsLessThanFrom() {
        givenAttributeConfigurationWithLimit((short) 50, (short) 49);
        whenGenerating((short) 5);
        fail("Should had thrown illegalargumentexception");
    }

    @Test
    public void shouldGenerateUsingIndexIfOneAttributeIsNull() {
        givenAttributeConfigurationWithLimit((Short) null, (short) 50);
        whenGenerating((short) 0);
        thenNumberGeneratedIs((short) 0);

        givenAttributeConfigurationWithLimit((short) 50, (Short) null);
        whenGenerating((short) 1);
        thenNumberGeneratedIs((short) 1);

        givenAttributeConfigurationWithLimit((Short) null, (Short) null);
        whenGenerating((short) 2);
        thenNumberGeneratedIs((short) 2);
    }

}
