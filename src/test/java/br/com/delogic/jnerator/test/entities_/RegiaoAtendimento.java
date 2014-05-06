package br.com.delogic.jnerator.test.entities_;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import br.com.delogic.jnerator.test.entities.TenentEntity;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO", length = 50)
public abstract class RegiaoAtendimento extends TenentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "seqRegiaoAtendimento")
    @SequenceGenerator(name= "seqRegiaoAtendimento", sequenceName= "seq_regiaoatendimento", allocationSize = 1, initialValue = 1)
    private Long       id;

    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("999999.99")
    @Column(nullable = false, scale = 2, precision = 7)
    private BigDecimal valorEntrega;

    public abstract String getDescricao();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorEntrega() {
        return valorEntrega;
    }

    public void setValorEntrega(BigDecimal valorEntrega) {
        this.valorEntrega = valorEntrega;
    }

}
