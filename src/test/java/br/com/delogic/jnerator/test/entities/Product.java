package br.com.delogic.jnerator.test.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
public class Product extends TenentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProduct")
    @SequenceGenerator(name = "seqProduct", sequenceName = "seq_Product", allocationSize = 1, initialValue = 1)
    @Column(precision = 8)
    private Long          id;

    @NotEmpty
    @Size(min = 1, max = 100)
    @Column(length = 100, nullable = false)
    private String        name;

    @NotEmpty
    @Size(min = 1, max = 150)
    @Column(length = 150, nullable = false)
    private String        description;

    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("999999.99")
    @Column(precision = 10, scale = 2)
    private BigDecimal    value;

    @NotNull
    @JoinColumn(name = "CATEGORIA_ID", nullable = false)
    private Category      category;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private UnitMeasure unit;

    @Column(nullable = false)
    private Boolean       active;

    @Column(length = 50)
    private String        code;

    @Column(length = 50)
    private String        externalCode;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public UnitMeasure getUnit() {
        return unit;
    }

    public void setUnit(UnitMeasure unit) {
        this.unit = unit;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExternalCode() {
        return externalCode;
    }

    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode;
    }

}
