package br.com.delogic.jnerator.test.entities_;

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

import br.com.delogic.jnerator.test.entities.enums.UnidadeFederativa;

@SuppressWarnings("serial")
@Entity
public class Cidade extends LongEntityId {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCidade")
    @SequenceGenerator(name = "seqCidade", sequenceName = "seq_cidade", allocationSize = 1, initialValue = 1)
    @Column(precision = 10)
    @NotNull
    private Long              id;

    @NotEmpty
    @Column(length = 100, nullable = false)
    private String            nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 3, nullable = false)
    private UnidadeFederativa uf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UnidadeFederativa getUf() {
        return uf;
    }

    public void setUf(UnidadeFederativa uf) {
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
