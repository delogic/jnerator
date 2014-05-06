package br.com.delogic.jnerator.test.entities_;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class LongEntityId implements Serializable, IsEntityId<Long> {

    @Override
    public int hashCode() {
        return getId() != null ? Integer.parseInt(String.valueOf(getId())) : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof LongEntityId)) return false;

        // many objects inherit from this class so we'd better check if they
        // have the same class
        if (!obj.getClass().equals(this.getClass())) return false;

        LongEntityId that = (LongEntityId) obj;
        return this.getId().equals(that.getId());
    }

    @Override
    public String toString() {
        return "Type:" + this.getClass().getName() + " id:" + getId();
    }

    public abstract void setId(Long id);

    public abstract Long getId();

}
