package br.com.delogic.jnerator.impl.generator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.delogic.jnerator.AttributeConfigurationImpl;

public class EmailAttributeGeneratorTest extends Assert {

    private EmailAttributeGenerator            generator;
    private AttributeConfigurationImpl<Object> config;
    private Person                             person;
    private String                             emailGenerated;

    @Before
    public void init() {
        generator = new EmailAttributeGenerator("name");
    }

    @Test
    public void shouldGenerateName() {
        givenAttributeConfiguration();
        givenAPersonNamed("Jose da Silva");
        whenGenerating();
        thenEmailIs("jose.da.silva@email0.com");
    }

    private void givenAPersonNamed(String personName) {
        person = new Person();
        person.name = personName;
    }

    private void givenAttributeConfiguration() {
        config = new AttributeConfigurationImpl<Object>("name", null, null);
    }

    private void whenGenerating() {
        emailGenerated = generator.generate(0, config, person);
    }

    private void thenEmailIs(String emailExpected) {
        assertEquals(emailExpected, emailGenerated);
    }

    public static class Person {

        private String name;

    }

}
