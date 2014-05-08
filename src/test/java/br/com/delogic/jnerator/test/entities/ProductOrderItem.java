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
public class ProductOrderItem extends LongEntityId {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqOrderItemProduct")
    @SequenceGenerator(name = "seqOrderItemProduct", sequenceName = "seq_OrderItemProduct", allocationSize = 1, initialValue = 1)
    private Long        id;

    @JoinColumn(nullable = false)
    private ItemProduct itemProduct;

    @Column(length = 100, nullable = false)
    private String      name;

    @Column(length = 100, nullable = false)
    private String      description;

    @Column(length = 50, nullable = false)
    private String      category;

    @Column(length = 50, nullable = false)
    private String      unit;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal  value;

    @Column(length = 50)
    private String      externalCode;

    @Column(nullable = false, precision = 8)
    private Long        originalProductId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getExternalCode() {
        return externalCode;
    }

    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode;
    }

    public Long getOriginalProductId() {
        return originalProductId;
    }

    public void setOriginalProductId(Long originalProductId) {
        this.originalProductId = originalProductId;
    }

}
