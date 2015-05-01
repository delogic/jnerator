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
import org.junit.Test;

import br.com.delogic.jnerator.AttributeConfigurationImpl;
import br.com.delogic.jnerator.AttributeGenerator;
import br.com.delogic.jnerator.impl.SimpleAttributeGeneratorFactory;
import br.com.delogic.jnerator.util.ReflectionUtils;

@SuppressWarnings("unused")
public class AttributeGeneratorTest extends Assert {

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

    @Test
    public void testBigDecimalAttributeGenerator(){
        testAttributeGeneration(new BigDecimalAttributeGenerator(), "_BigDecimal");
    }

    @Test
    public void testBigIntegerAttributeGenerator(){
        testAttributeGeneration(new BigIntegerAttributeGenerator(), "_BigInteger");
    }

    @Test
    public void testBooleanAttributeGenerator(){
        testAttributeGeneration(new BooleanAttributeGenerator(), "_Boolean");
    }

    @Test
    public void testByteAttributeGenerator(){
        testAttributeGeneration(new ByteAttributeGenerator(), "_Byte");
    }

    @Test
    public void testCharacterAttributeGenerator(){
        testAttributeGeneration(new CharacterAttributeGenerator(), "_Character");
    }

    @Test
    public void testDateAttributeGenerator(){
        testAttributeGeneration(new DateAttributeGenerator(), "_Date");
    }

    @Test
    public void testDoubleAttributeGenerator(){
        testAttributeGeneration(new DoubleAttributeGenerator(), "_Double");
    }

    @Test
    public void testFloatAttributeGenerator(){
        testAttributeGeneration(new FloatAttributeGenerator(), "_Float");
    }

    @Test
    public void testIntegerAttributeGenerator(){
        testAttributeGeneration(new IntegerAttributeGenerator(), "_Integer");
    }

    @Test
    public void testIntAttributeGenerator(){
        testAttributeGeneration(new IntegerAttributeGenerator(), "_int");
    }

    @Test
    public void testLongAttributeGenerator(){
        testAttributeGeneration(new LongAttributeGenerator(), "_Long");
    }

    @Test
    public void testShortAttributeGenerator(){
        testAttributeGeneration(new ShortAttributeGenerator(), "_Short");
    }

    @Test
    public void testStringAttributeGenerator(){
        testAttributeGeneration(new StringAttributeGenerator(), "_String");
    }

    @Test
    public void testSimpleTypeCollectionAttributeGenerator(){
        testAttributeGeneration(new SimpleTypeCollectionAttributeGenerator(getLocalField("_ListOfStrings"), new StringAttributeGenerator()), "_ListOfStrings");
        testAttributeGeneration(new SimpleTypeCollectionAttributeGenerator(getLocalField("_ArrayListOfStrings"), new StringAttributeGenerator()), "_ArrayListOfStrings");
        testAttributeGeneration(new SimpleTypeCollectionAttributeGenerator(getLocalField("_SetOfInteger"), new StringAttributeGenerator()), "_SetOfInteger");
        testAttributeGeneration(new SimpleTypeCollectionAttributeGenerator(getLocalField("_SetOfCharacter"), new CharacterAttributeGenerator()), "_SetOfCharacter");
    }


    private Field getLocalField(String name) {
        Field field = ReflectionUtils.getField(getClass(), name);
        field.setAccessible(true);
        return field;
    }

    private <E> void testAttributeGeneration(AttributeGenerator attributeGenerator, String fieldName){
        for (int i = 0; i < 1000; i++){
            Object val = attributeGenerator.generate(i, new AttributeConfigurationImpl<E>(fieldName, getLocalField(fieldName), null), new Object());
            assertNotNull(val);
        }
    }



}
