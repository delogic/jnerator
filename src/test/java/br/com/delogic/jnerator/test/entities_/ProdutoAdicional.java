package br.com.delogic.jnerator.test.entities_;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.delogic.jnerator.test.entities.TenentEntity;

@SuppressWarnings("serial")
@Entity
public class ProdutoAdicional extends TenentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "seqProdutoAdicional")
    @SequenceGenerator(name= "seqProdutoAdicional", sequenceName= "seq_produtoadicional", allocationSize = 1, initialValue = 1)
    @Column(precision = 8)
    private Long       id;


    @NotEmpty
    @Size(min = 1, max = 100)
    @Column(length = 50, nullable = false)
    private String     nome;

    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("999999.99")
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}
