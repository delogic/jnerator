package br.com.delogic.jnerator.test.entities;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.delogic.jnerator.test.entities.util.TenentProperty;
import br.com.delogic.jnerator.test.entities_.LongEntityId;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class TenentEntity extends LongEntityId implements TenentProperty {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Tenent tenent;

    public Tenent getTenent() {
        return tenent;
    }

    public void setTenent(Tenent tenent) {
        this.tenent = tenent;
    }

}
