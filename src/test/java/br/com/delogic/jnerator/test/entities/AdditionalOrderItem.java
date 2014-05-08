package br.com.delogic.jnerator.test.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
public class AdditionalOrderItem extends LongEntityId {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAdditionalOrderItem")
    @SequenceGenerator(name = "seqAdditionalOrderItem", sequenceName = "seq_AdditionalOrderItem", allocationSize = 1, initialValue = 1)
    @Column(precision = 8)
    private Long        id;

    @JoinColumn(nullable = false)
    private ItemProduct itemProduct;

    @Column(length = 50, nullable = false)
    private String      name;

    @Column(precision = 10, nullable = false)
    private BigDecimal  value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemProduct getItemProduct() {
        return itemProduct;
    }

    public void setItemProduct(ItemProduct itemProduct) {
        this.itemProduct = itemProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

}
