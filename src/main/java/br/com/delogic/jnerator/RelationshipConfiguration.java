package br.com.delogic.jnerator;

public class RelationshipConfiguration {

    private final RelationshipType relationshipType;
    private final Class<?>         ownerType;
    private final Class<?>         ownedType;
    private final String           ownedAttributeName;
    private final String           ownedOwnerAttributeName;

    public RelationshipConfiguration(RelationshipType relationshipType,
        Class<?> ownerType,
        Class<?> ownedType,
        String ownedAttributeName,
        String ownedOwnerAttributeName) {
        this.ownedAttributeName = ownedAttributeName;
        this.ownedOwnerAttributeName = ownedOwnerAttributeName;
        this.ownedType = ownedType;
        this.ownerType = ownerType;
        this.relationshipType = relationshipType;
    }

    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    public Class<?> getOwnerType() {
        return ownerType;
    }

    public Class<?> getOwnedType() {
        return ownedType;
    }

    public String getOwnedAttributeName() {
        return ownedAttributeName;
    }

    public String getOwnedOwnerAttributeName() {
        return ownedOwnerAttributeName;
    }

}
