package br.com.delogic.jnerator.test.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.delogic.jnerator.test.entities.enums.FederalState;

@SuppressWarnings("serial")
@Entity
public class City extends LongEntityId {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCity")
    @SequenceGenerator(name = "seqCity", sequenceName = "seq_city", allocationSize = 1, initialValue = 1)
    @Column(precision = 10)
    @NotNull
    private Long         id;

    @NotEmpty
    @Column(length = 100, nullable = false)
    private String       name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 3, nullable = false)
    private FederalState state;

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

    public FederalState getState() {
        return state;
    }

    public void setState(FederalState state) {
        this.state = state;
    }

}
