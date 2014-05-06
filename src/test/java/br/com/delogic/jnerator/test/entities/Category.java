package br.com.delogic.jnerator.test.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
public class Category extends TenentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCategory")
    @SequenceGenerator(name = "seqCategory", sequenceName = "seq_category", allocationSize = 1, initialValue = 1)
    @Column(precision = 10)
    private Long    id;

    @NotEmpty
    @Size(min = 1, max = 50)
    @Column(length = 50, nullable = false)
    private String  name;

    @NotNull
    @Column(nullable = false)
    private Integer compositions;

    @NotNull
    @Column(nullable = false)
    private Integer order;

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

    public Integer getCompositions() {
        return compositions;
    }

    public void setCompositions(Integer compositions) {
        this.compositions = compositions;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

}
