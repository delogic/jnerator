package br.com.delogic.jnerator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JNeratorTest extends Assert {

    private JNerator jNerator;

    @Before
    public void init() {
        jNerator = new JNeratorImpl();
    }

    @Test
    public void test() {

        List<Category> categories = jNerator.prepare(Category.class).generate(50);
        assertNotNull(categories);
        assertEquals(50, categories.size());
        for (Category cat : categories) {
            assertNotNull(cat);
            assertNotNull(cat.getName());
            assertNotNull(cat.getId());
            System.out.println(cat.getId() + "|" + cat.getName() + "|" + cat.getOrder());
        }

    }
}
