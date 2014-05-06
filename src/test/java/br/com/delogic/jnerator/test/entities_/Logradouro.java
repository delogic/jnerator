package br.com.delogic.jnerator.test.entities_;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import br.com.delogic.jnerator.test.entities.enums.TipoLogradouro;

@SuppressWarnings("serial")
@Entity
public class Logradouro extends LongEntityId {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqLogradouro")
    @SequenceGenerator(name = "seqLogradouro", sequenceName = "seq_logradouro", allocationSize = 1, initialValue = 1)
    @Column(precision = 10)
    private Long           id;

    @Column(length = 150, nullable = false)
    private String         nome;

    @ManyToOne
    @JoinColumn(name = "idbairro", nullable = false)
    private Bairro         bairro;

    @Column(length = 8, nullable = false)
    private String         cep;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private TipoLogradouro tipoLogradouro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public TipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
