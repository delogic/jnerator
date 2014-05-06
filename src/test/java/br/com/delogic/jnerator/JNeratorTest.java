package br.com.delogic.jnerator;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.delogic.jnerator.test.entities.Category;
import br.com.delogic.jnerator.test.entities.Product;
import br.com.delogic.jnerator.test.entities.Tenent;

public class JNeratorTest extends Assert {

    private JNerator jNerator;

    @Before
    public void init() {
        jNerator = new JNeratorImpl();
    }

    @Test
    public void test() {

        assertTenents(jNerator.prepare(Tenent.class).generate(50));
        assertCategories(jNerator.prepare(Category.class).generate(50));
        assertProducts(jNerator.prepare(Product.class).generate(100));

    }

    private void assertTenents(List<Tenent> tenents) {
        assertNotNull(tenents);
        for (Tenent t: tenents){
            assertNotNull(t.getCompanyName());
            assertNotNull(t.getDomain());
            assertNotNull(t.getEmail());
            assertNotNull(t.getActive());
            assertNotNull(t.getId());
            assertNotNull(t.getLastAccess());
            toString(t);
        }
    }

    private void assertCategories(List<Category> categories) {
        assertNotNull(categories);
        assertEquals(50, categories.size());
        for (Category cat : categories) {
            assertNotNull(cat);
            assertNotNull(cat.getName());
            assertNotNull(cat.getId());
            assertNotNull(cat.getCompositions());
            assertNotNull(cat.getOrder());
            assertNotNull(cat.getTenent());
            toString(cat);
        }
    }

    private void toString(Object object) {
        System.out.println(ToStringBuilder.reflectionToString(object, ToStringStyle.DEFAULT_STYLE));
    }

    private void assertProducts(List<Product> products) {
        assertNotNull(products);
        assertFalse(products.isEmpty());
        for (Product p : products) {
            assertNotNull(p.getDescription());
            assertNotNull(p.getName());
            assertNotNull(p.getCategory());
            assertNotNull(p.getId());
            assertNotNull(p.getValue());
            assertNotNull(p.getCategory().getName());
            assertNotNull(p.getCategory().getId());
            assertNotNull(p.getCategory().getOrder());
            toString(p);
        }
    }
}
