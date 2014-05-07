package br.com.delogic.jnerator.test.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
public class Additional extends TenentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAdditional")
    @SequenceGenerator(name = "seqAdditional", sequenceName = "seq_Additional", allocationSize = 1, initialValue = 1)
    @Column(precision = 8)
    private Long          id;

    @NotEmpty
    @Size(min = 1, max = 100)
    @Column(length = 50, nullable = false)
    private String        name;

    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("999999.99")
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal    value;

    private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
