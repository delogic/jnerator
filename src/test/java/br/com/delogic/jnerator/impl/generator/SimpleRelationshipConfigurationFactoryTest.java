package br.com.delogic.jnerator.impl.generator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import br.com.delogic.jnerator.RelationshipConfiguration;
import br.com.delogic.jnerator.RelationshipConfigurationFactory;
import br.com.delogic.jnerator.RelationshipType;
import br.com.delogic.jnerator.SimpleRelationshipConfigurationFactory;
import br.com.delogic.jnerator.util.ReflectionUtils;

@SuppressWarnings("unused")
public class SimpleRelationshipConfigurationFactoryTest extends Assert {

    private OneToOne              oneToOne;
    private List<OneToMany>       oneToMany;
    private OneToMany[]           oneToManyArray;
    private ManyToOne             manyToOne;
    private ManyToOneArray        manyToOneArray;
    private ArrayList<ManyToMany> manyToMany;
    private ManyToManyArray[]     manyToManyArrays;

    @Test
    public void testRelationships() {

        RelationshipConfigurationFactory configurationFactory = new SimpleRelationshipConfigurationFactory();
        assertOneToOne(configurationFactory.create(getLocalField("oneToOne")));
        assertOneToMany(configurationFactory.create(getLocalField("oneToMany")));
        assertOneToManyArray(configurationFactory.create(getLocalField("oneToManyArray")));
        assertManyToOne(configurationFactory.create(getLocalField("manyToOne")));
        assertManyToOneArray(configurationFactory.create(getLocalField("manyToOneArray")));
        assertManyToMany(configurationFactory.create(getLocalField("manyToMany")));
        assertManyToManyArray(configurationFactory.create(getLocalField("manyToManyArrays")));

    }

    private void assertManyToManyArray(RelationshipConfiguration create) {
        assertNotNull(create);
        assertEquals("manyToManyArrays", create.getOwnedAttributeName());
        assertEquals("array", create.getOwnedOwnerAttributeName());
        assertEquals(ManyToManyArray.class, create.getOwnedType());
        assertEquals(SimpleRelationshipConfigurationFactoryTest.class, create.getOwnerType());
        assertEquals(RelationshipType.MANY_TO_MANY, create.getRelationshipType());
    }

    private void assertManyToMany(RelationshipConfiguration create) {
        assertNotNull(create);
        assertEquals("manyToMany", create.getOwnedAttributeName());
        assertEquals("collection", create.getOwnedOwnerAttributeName());
        assertEquals(ManyToMany.class, create.getOwnedType());
        assertEquals(SimpleRelationshipConfigurationFactoryTest.class, create.getOwnerType());
        assertEquals(RelationshipType.MANY_TO_MANY, create.getRelationshipType());
    }

    private void assertManyToOneArray(RelationshipConfiguration create) {
        assertNotNull(create);
        assertEquals("manyToOneArray", create.getOwnedAttributeName());
        assertEquals("configurationFactoryTests", create.getOwnedOwnerAttributeName());
        assertEquals(ManyToOneArray.class, create.getOwnedType());
        assertEquals(SimpleRelationshipConfigurationFactoryTest.class, create.getOwnerType());
        assertEquals(RelationshipType.MANY_TO_ONE, create.getRelationshipType());
    }

    private void assertManyToOne(RelationshipConfiguration create) {
        assertNotNull(create);
        assertEquals("manyToOne", create.getOwnedAttributeName());
        assertEquals("configurationFactoryTests", create.getOwnedOwnerAttributeName());
        assertEquals(ManyToOne.class, create.getOwnedType());
        assertEquals(SimpleRelationshipConfigurationFactoryTest.class, create.getOwnerType());
        assertEquals(RelationshipType.MANY_TO_ONE, create.getRelationshipType());
    }

    private void assertOneToManyArray(RelationshipConfiguration create) {
        assertNotNull(create);
        assertEquals("oneToManyArray", create.getOwnedAttributeName());
        assertEquals("configurationFactoryTest", create.getOwnedOwnerAttributeName());
        assertEquals(OneToMany.class, create.getOwnedType());
        assertEquals(SimpleRelationshipConfigurationFactoryTest.class, create.getOwnerType());
        assertEquals(RelationshipType.ONE_TO_MANY, create.getRelationshipType());
    }

    private void assertOneToMany(RelationshipConfiguration create) {
        assertNotNull(create);
        assertEquals("oneToMany", create.getOwnedAttributeName());
        assertEquals("configurationFactoryTest", create.getOwnedOwnerAttributeName());
        assertEquals(OneToMany.class, create.getOwnedType());
        assertEquals(SimpleRelationshipConfigurationFactoryTest.class, create.getOwnerType());
        assertEquals(RelationshipType.ONE_TO_MANY, create.getRelationshipType());
    }

    private void assertOneToOne(RelationshipConfiguration create) {
        assertNotNull(create);
        assertEquals("oneToOne", create.getOwnedAttributeName());
        assertEquals("relationshipConfigurationFactoryTest", create.getOwnedOwnerAttributeName());
        assertEquals(OneToOne.class, create.getOwnedType());
        assertEquals(SimpleRelationshipConfigurationFactoryTest.class, create.getOwnerType());
        assertEquals(RelationshipType.ONE_TO_ONE, create.getRelationshipType());
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

    public static class OneToOne {

        private SimpleRelationshipConfigurationFactoryTest relationshipConfigurationFactoryTest;

    }

    public static class OneToMany {

        private SimpleRelationshipConfigurationFactoryTest configurationFactoryTest;

    }

    public static class ManyToOne {

        private Set<SimpleRelationshipConfigurationFactoryTest> configurationFactoryTests;

    }

    public static class ManyToOneArray {

        private SimpleRelationshipConfigurationFactoryTest[] configurationFactoryTests;

    }

    public static class ManyToMany {

        private Collection<SimpleRelationshipConfigurationFactoryTest> collection;

    }

    public static class ManyToManyArray {

        private SimpleRelationshipConfigurationFactoryTest[] array;

    }

}
