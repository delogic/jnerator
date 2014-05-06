package br.com.delogic.jnerator.test.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.delogic.jnerator.test.entities.util.TenentProperty;

@Entity
public class TenentAddress implements TenentProperty {

    @Id
    @OneToOne
    @JoinColumn(nullable = false)
    private Tenent  tenent;

    private Address address;

    public Tenent getTenent() {
        return tenent;
    }

    public void setTenent(Tenent tenent) {
        this.tenent = tenent;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
