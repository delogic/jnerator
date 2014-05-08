package br.com.delogic.jnerator.test.entities;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.delogic.jnerator.test.entities.enums.CreditCards;
import br.com.delogic.jnerator.test.entities.util.TenentProperty;

@Entity
public class TenentPaymentsAccepted implements TenentProperty {

    @Id
    @OneToOne
    private Tenent              tenent;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tenentstorepayments", joinColumns = { @JoinColumn(name = "tenent_id", nullable = false) })
    @Enumerated(EnumType.STRING)
    @Column(name = "paymenttype", length = 50, nullable = false)
    private Set<CreditCards> storePaymentTypes;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tenentdeliverypayments", joinColumns = { @JoinColumn(name = "tenent_id", nullable = false) })
    @Enumerated(EnumType.STRING)
    @Column(name = "paymenttype", length = 50, nullable = false)
    private Set<CreditCards> deliveryPaymentTypes;

    public Tenent getTenent() {
        return tenent;
    }

    public void setTenent(Tenent tenent) {
        this.tenent = tenent;
    }

    public Set<CreditCards> getStorePaymentTypes() {
        return storePaymentTypes;
    }

    public void setStorePaymentTypes(Set<CreditCards> storePaymentTypes) {
        this.storePaymentTypes = storePaymentTypes;
    }

    public Set<CreditCards> getDeliveryPaymentTypes() {
        return deliveryPaymentTypes;
    }

    public void setDeliveryPaymentTypes(Set<CreditCards> deliveryPaymentTypes) {
        this.deliveryPaymentTypes = deliveryPaymentTypes;
    }

}
