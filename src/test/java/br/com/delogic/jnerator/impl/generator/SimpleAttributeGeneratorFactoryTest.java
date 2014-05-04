package br.com.delogic.jnerator.impl.generator;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.delogic.jnerator.AttributeGenerator;
import br.com.delogic.jnerator.impl.SimpleAttributeGeneratorFactory;
import br.com.delogic.jnerator.util.ReflectionUtils;

@SuppressWarnings("unused")
public class SimpleAttributeGeneratorFactoryTest extends Assert {

    private SimpleAttributeGeneratorFactory factory;

    // fields used by the test
    private byte                            _byte;
    private short                           _short;
    private int                             _int;
    private long                            _long;
    private float                           _float;
    private double                          _double;
    private char                            _char;
    private boolean                         _boolean;

    private Byte                            _Byte;
    private Short                           _Short;
    private Integer                         _Integer;
    private Long                            _Long;
    private Float                           _Float;
    private Double                          _Double;
    private Character                       _Character;
    private Boolean                         _Boolean;

    private String                          _String;
    private BigInteger                      _BigInteger;
    private BigDecimal                      _BigDecimal;
    private Date                            _Date;

    private List<String>                    _ListOfStrings;
    private ArrayList<String>               _ArrayListOfStrings;
    private Set<Integer>                    _SetOfInteger;
    private HashSet<Character>              _SetOfCharacter;

    @Before
    public void init() {
        factory = new SimpleAttributeGeneratorFactory();
    }

    @Test
    public void testCreate() {

        assertFactoryField(BooleanAttributeGenerator.class, "_boolean");
        assertFactoryField(ByteAttributeGenerator.class, "_byte");
        assertFactoryField(ShortAttributeGenerator.class, "_short");
        assertFactoryField(IntegerAttributeGenerator.class, "_int");
        assertFactoryField(LongAttributeGenerator.class, "_long");
        assertFactoryField(FloatAttributeGenerator.class, "_float");
        assertFactoryField(DoubleAttributeGenerator.class, "_double");
        assertFactoryField(CharacterAttributeGenerator.class, "_char");

        assertFactoryField(BooleanAttributeGenerator.class, "_Boolean");
        assertFactoryField(ByteAttributeGenerator.class, "_Byte");
        assertFactoryField(ShortAttributeGenerator.class, "_Short");
        assertFactoryField(IntegerAttributeGenerator.class, "_Integer");
        assertFactoryField(LongAttributeGenerator.class, "_Long");
        assertFactoryField(FloatAttributeGenerator.class, "_Float");
        assertFactoryField(DoubleAttributeGenerator.class, "_Double");
        assertFactoryField(CharacterAttributeGenerator.class, "_Character");

        assertFactoryField(StringAttributeGenerator.class, "_String");
        assertFactoryField(BigIntegerAttributeGenerator.class, "_BigInteger");
        assertFactoryField(BigDecimalAttributeGenerator.class, "_BigDecimal");
        assertFactoryField(DateAttributeGenerator.class, "_Date");

        assertFactoryField(SimpleTypeCollectionAttributeGenerator.class, "_ListOfStrings");
        assertFactoryField(SimpleTypeCollectionAttributeGenerator.class, "_ArrayListOfStrings");
        assertFactoryField(SimpleTypeCollectionAttributeGenerator.class, "_SetOfInteger");
        assertFactoryField(SimpleTypeCollectionAttributeGenerator.class, "_SetOfCharacter");

    }

    @SuppressWarnings("unchecked")
    private <E extends AttributeGenerator<?>> void assertFactoryField(Class<E> attributeGeneratorType, String fieldName) {
        Field field = getLocalField(fieldName);
        assertNotNull("There's no field " + field, field);

        E generator = (E) factory.create(field, null);
        assertNotNull("There's no generator for " + field.getType(), generator);

        assertEquals(attributeGeneratorType, generator.getClass());

    }

    private Field getLocalField(String name) {
        try {
            Field field = ReflectionUtils.getField(getClass(), name);
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
