package br.com.delogic.jnerator.test.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
public class ItemProduct extends LongEntityId {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqOrderItem")
    @SequenceGenerator(name = "seqOrderItem", sequenceName = "seq_OrderItem", allocationSize = 1, initialValue = 1)
    private Long                      id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "OrderItem")
    private List<ProductOrderItem>    products;

    @JoinColumn(nullable = false)
    private Order                     order;

    @Column(precision = 10, scale = 2)
    private BigDecimal                unitValue;

    @Column(precision = 10, scale = 2)
    private BigDecimal                value;

    @Column(precision = 10, scale = 3)
    private BigDecimal                amount;

    @Column(length = 200)
    private String                    comment;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "orderItem")
    private List<AdditionalOrderItem> additionals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductOrderItem> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOrderItem> products) {
        this.products = products;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<AdditionalOrderItem> getAdditionals() {
        return additionals;
    }

    public void setAdditionals(List<AdditionalOrderItem> additionals) {
        this.additionals = additionals;
    }

}
