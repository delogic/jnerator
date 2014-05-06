package br.com.delogic.jnerator.test.entities_;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import br.com.delogic.jnerator.test.entities.Tenent;

@SuppressWarnings("serial")
@Entity
public class Usuario extends LongEntityId {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUsuario")
    @SequenceGenerator(name = "seqUsuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
    @Column(precision = 8)
    private Long         id;

    @Column(length = 100, nullable = false)
    private String       nome;

    @Column(length = 100, nullable = false, unique = true)
    private String       email;

    @Column(columnDefinition = "BYTEA", length = 255, nullable = false)
    private byte[]       senha;

    @Column(columnDefinition = "BYTEA", length = 255, nullable = false)
    private byte[]       salt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Tenent arrendatario;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getSenha() {
        return senha;
    }

    public void setSenha(byte[] senha) {
        this.senha = senha;
    }

    public Tenent getArrendatario() {
        return arrendatario;
    }

    public void setArrendatario(Tenent arrendatario) {
        this.arrendatario = arrendatario;
    }

}
