package br.com.delogic.jnerator.impl.generator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.delogic.jnerator.AttributeConfigurationImpl;

public class BrazilianNameAttributeGeneratorTest extends Assert {

    private BrazilianNameAttributeGenerator    generator;
    private AttributeConfigurationImpl<Object> config;
    private String                             name;

    @Before
    public void init() {
        generator = new BrazilianNameAttributeGenerator();
    }

    @Test
    public void shouldGenerateName() {
        givenAttributeConfiguration();
        whenGenerating();
        thenValueHasNameAndLastName();
    }

    private void givenAttributeConfiguration() {
        config = new AttributeConfigurationImpl<Object>("name", null, null);
    }

    private void whenGenerating() {
        name = generator.generate(0, config, null);
    }

    private void thenValueHasNameAndLastName() {
        assertNotNull(name);
        assertTrue(name.length() >= 3);
        System.out.println(name);
    }

}
