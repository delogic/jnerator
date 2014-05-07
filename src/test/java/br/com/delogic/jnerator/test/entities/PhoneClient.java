package br.com.delogic.jnerator.test.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import br.com.delogic.jnerator.test.entities.util.TenentProperty;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("Telefone")
public class PhoneClient extends Client implements TenentProperty {

    @Column(length = 15)
    private String  phone;

    @JoinColumn(nullable = false)
    private Tenent  tenent;

    private Address address;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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
