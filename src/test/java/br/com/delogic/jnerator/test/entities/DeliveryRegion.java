package br.com.delogic.jnerator.test.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 50)
public abstract class DeliveryRegion extends TenentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqDeliveryRegion")
    @SequenceGenerator(name = "seqDeliveryRegion", sequenceName = "seq_DeliveryRegion", allocationSize = 1, initialValue = 1)
    private Long       id;

    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("999999.99")
    @Column(nullable = false, scale = 2, precision = 7)
    private BigDecimal deliveryValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDeliveryValue() {
        return deliveryValue;
    }

    public void setDeliveryValue(BigDecimal deliveryValue) {
        this.deliveryValue = deliveryValue;
    }

}
