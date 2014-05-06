package br.com.delogic.jnerator.test.entities_;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import br.com.delogic.jnerator.test.entities.Tenent;

@SuppressWarnings("serial")
@Entity
public class VisitanteMensagemFaleConosco extends LongEntityId {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqVisitanteMensagemFaleConosco")
    @SequenceGenerator(name = "seqVisitanteMensagemFaleConosco", sequenceName = "seq_visitantemensagemfaleconosco", allocationSize = 1, initialValue = 1)
    @Column(precision = 10)
    private Long         id;

    @Column(length = 100, nullable = false)
    private String       nome;

    @Column(length = 100, nullable = false)
    private String       email;

    @Column(length = 100, nullable = false)
    private String       telefone;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Tenent arrendatario;

    @Column(length = 5000)
    private String       comentarios;

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Tenent getArrendatario() {
        return arrendatario;
    }

    public void setArrendatario(Tenent arrendatario) {
        this.arrendatario = arrendatario;
    }

}
