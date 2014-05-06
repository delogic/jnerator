package br.com.delogic.jnerator;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.delogic.jnerator.test.entities.Address;
import br.com.delogic.jnerator.test.entities.Category;
import br.com.delogic.jnerator.test.entities.City;
import br.com.delogic.jnerator.test.entities.Product;
import br.com.delogic.jnerator.test.entities.Tenent;
import br.com.delogic.jnerator.test.entities.TenentAddress;
import br.com.delogic.jnerator.test.entities.TenentPaymentsAccepted;
import br.com.delogic.jnerator.test.entities.TenentStorePersonalization;
import br.com.delogic.jnerator.test.entities.TenentWorkingHours;
import br.com.delogic.jnerator.util.ReflectionUtils;

public class JNeratorTest extends Assert {

    private JNerator jNerator;
    private int      amount = 100;

    @Before
    public void init() {
        jNerator = new JNeratorImpl();
    }

    @Test
    public void test() {

        assertHasData(jNerator.prepare(Tenent.class).generate(amount));
        assertHasData(jNerator.prepare(Category.class).generate(amount));
        assertHasData(jNerator.prepare(Product.class).generate(amount));
        assertHasData(jNerator.prepare(City.class).generate(amount));
        assertHasData(jNerator.prepare(Address.class).generate(amount));
        assertHasData(jNerator.prepare(TenentAddress.class).generate(amount));
        assertHasData(jNerator.prepare(TenentPaymentsAccepted.class).generate(amount));
        assertHasData(jNerator.prepare(TenentStorePersonalization.class).generate(amount));
        assertHasData(jNerator.prepare(TenentWorkingHours.class).generate(amount));

    }

    private void assertHasData(List<?> objects) {
        assertNotNull(objects);
        assertTrue(objects.size() >= amount);

        List<Field> allFields = ReflectionUtils.getAllDeclaredFields(objects.get(0).getClass());

        for (Object obj : objects) {
            for (Field field : allFields) {
                try {
                    field.setAccessible(true);
                    assertNotNull(field.get(obj));
                } catch (Exception e) {
                    throw new RuntimeException("Error when trying to get field value for assert", e);
                }
            }
            toString(obj);
        }
    }

    private void toString(Object object) {
        System.out.println(ToStringBuilder.reflectionToString(object, ToStringStyle.DEFAULT_STYLE));
    }

}
