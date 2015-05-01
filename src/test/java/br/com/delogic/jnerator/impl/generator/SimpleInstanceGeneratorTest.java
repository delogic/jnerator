package br.com.delogic.jnerator.impl.generator;

import org.junit.Assert;
import org.junit.Test;

import br.com.delogic.jnerator.JNerator;
import br.com.delogic.jnerator.JNeratorImpl;
import br.com.delogic.jnerator.test.entities.Category;
import br.com.delogic.jnerator.test.entities.Tenent;

public class SimpleInstanceGeneratorTest extends Assert {

    @Test
    public void testDoNotGenerateOnInstances() {

        JNerator jNerator = new JNeratorImpl();

        for (Tenent p : jNerator.prepare(Tenent.class).generate(10)) {
            assertNotNull(p.getId());
            assertNotNull(p.getDomain());
        }

        for (Category c : jNerator.prepare(Category.class).generate(10)) {
            assertNotNull(c.getId());
            assertNotNull(c.getName());
            assertNotNull(c.getCompositions());
            assertNotNull(c.getOrder());
            assertNotNull(c.getTenent());
        }

        for (Category c : jNerator.prepare(Category.class).doNotGenerateAttribute("id", "name", "tenent").generate(10)) {
            assertNull(c.getId());
            assertNull(c.getName());
            assertNotNull(c.getCompositions());
            assertNotNull(c.getOrder());
            assertNull(c.getTenent());
        }

    }

    @Test
    public void testDoNotGenerateOnJNerator() {

        JNerator jNerator = new JNeratorImpl();
        jNerator.doNotGenerateForAttributes("id", "name", "tenent");

        for (Tenent p : jNerator.prepare(Tenent.class).generate(10)) {
            assertNull(p.getId());
            assertNotNull(p.getDomain());
        }

        for (Category c : jNerator.prepare(Category.class).generate(10)) {
            assertNull(c.getId());
            assertNull(c.getName());
            assertNotNull(c.getCompositions());
            assertNotNull(c.getOrder());
            assertNull(c.getTenent());
        }

    }

}
