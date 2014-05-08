package br.com.delogic.jnerator;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.delogic.jnerator.impl.generator.ProvidedAttributeGenerator;
import br.com.delogic.jnerator.test.entities.Additional;
import br.com.delogic.jnerator.test.entities.AdditionalOrderItem;
import br.com.delogic.jnerator.test.entities.Address;
import br.com.delogic.jnerator.test.entities.CashPaymentMode;
import br.com.delogic.jnerator.test.entities.Category;
import br.com.delogic.jnerator.test.entities.City;
import br.com.delogic.jnerator.test.entities.Client;
import br.com.delogic.jnerator.test.entities.CreditCardPaymentMode;
import br.com.delogic.jnerator.test.entities.DeliveryMode;
import br.com.delogic.jnerator.test.entities.DeliveryRegionByCity;
import br.com.delogic.jnerator.test.entities.DeliveryRegionByZipCode;
import br.com.delogic.jnerator.test.entities.ItemProduct;
import br.com.delogic.jnerator.test.entities.LocalClient;
import br.com.delogic.jnerator.test.entities.OnlineClient;
import br.com.delogic.jnerator.test.entities.OnlineClientAddress;
import br.com.delogic.jnerator.test.entities.Order;
import br.com.delogic.jnerator.test.entities.PaymenteMode;
import br.com.delogic.jnerator.test.entities.PhoneClient;
import br.com.delogic.jnerator.test.entities.Product;
import br.com.delogic.jnerator.test.entities.ProductOrderItem;
import br.com.delogic.jnerator.test.entities.StoreDelivered;
import br.com.delogic.jnerator.test.entities.Tenent;
import br.com.delogic.jnerator.test.entities.TenentAddress;
import br.com.delogic.jnerator.test.entities.TenentPaymentsAccepted;
import br.com.delogic.jnerator.test.entities.TenentStorePersonalization;
import br.com.delogic.jnerator.test.entities.TenentWorkingHours;
import br.com.delogic.jnerator.test.entities.enums.CreditCards;
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
        assertHasData(jNerator.prepare(LocalClient.class).generate(amount));
        assertHasData(jNerator.prepare(OnlineClient.class).generate(amount));
        assertHasData(jNerator.prepare(OnlineClientAddress.class).generate(amount));
        assertHasData(jNerator.prepare(PhoneClient.class).generate(amount));
        assertHasData(jNerator.prepare(Additional.class).generate(amount));
        assertHasData(jNerator.prepare(DeliveryRegionByCity.class).generate(amount));
        assertHasData(jNerator.prepare(DeliveryRegionByZipCode.class).generate(amount));

        InstanceGenerator<Order> orderGenerator = jNerator.prepare(Order.class);

        orderGenerator.setAttributeGenerator("paymentMode", new AttributeGenerator<PaymenteMode, Order>() {
            private Random random = new Random();

            public PaymenteMode generate(int index, AttributeConfiguration attributeConfiguration, Order instance) {

                if (random.nextBoolean()) {
                    CashPaymentMode mode = new CashPaymentMode();
                    mode.setOrder(instance);
                    mode.setChange(new BigDecimal(123));
                    mode.setPresented(new BigDecimal(1234));
                    return mode;
                } else {
                    CreditCardPaymentMode mode = new CreditCardPaymentMode();
                    mode.setCreditCard(CreditCards.AMERICAN_EXPRESS);
                    mode.setOrder(instance);
                    return mode;
                }

            }
        });

        orderGenerator.setAttributeGenerator("orderItens", new AttributeGenerator<Set<ItemProduct>, Order>() {
            public Set<ItemProduct> generate(int index, AttributeConfiguration attributeConfiguration, final Order order) {
                return new HashSet<ItemProduct>(
                    jNerator.prepare(ItemProduct.class)
                        .setAttributeGenerator("order", new ProvidedAttributeGenerator<Order, ItemProduct>(order))
                        .setAttributeGenerator("products", new AttributeGenerator<List<ProductOrderItem>, ItemProduct>() {
                            public List<ProductOrderItem> generate(int index, AttributeConfiguration attributeConfiguration,
                                ItemProduct instance) {
                                return jNerator
                                    .prepare(ProductOrderItem.class)
                                    .setAttributeGenerator("itemProduct",
                                        new ProvidedAttributeGenerator<ItemProduct, ProductOrderItem>(instance)).generate(10);
                            }
                        })
                        .setAttributeGenerator("additionals", new AttributeGenerator<List<AdditionalOrderItem>, ItemProduct>() {
                            public List<AdditionalOrderItem> generate(int index, AttributeConfiguration attributeConfiguration,
                                final ItemProduct itemProduct) {
                                return jNerator
                                    .prepare(AdditionalOrderItem.class)
                                    .setAttributeGenerator("itemProduct",
                                        new ProvidedAttributeGenerator<ItemProduct, AdditionalOrderItem>(itemProduct))
                                    .generate(10);
                            }
                        })
                        .generate(10));
            }
        });

        //
        orderGenerator.setAttributeGenerator("deliveryMode", new AttributeGenerator<DeliveryMode, Order>() {
            public DeliveryMode generate(int index, AttributeConfiguration attributeConfiguration, Order instance) {
                return new StoreDelivered();
            }
        });

        orderGenerator.setAttributeGenerator("additionals", new AttributeGenerator<List<AdditionalOrderItem>, Order>() {
            public List<AdditionalOrderItem> generate(int index, AttributeConfiguration attributeConfiguration, Order instance) {
                return jNerator.prepare(AdditionalOrderItem.class).generate(10);
            }
        });

        List<Order> orders = orderGenerator.generate(amount);
        assertHasData(orders);

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
