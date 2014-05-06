package br.com.delogic.jnerator.test.entities_;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.delogic.jnerator.test.entities.Tenent;
import br.com.delogic.jnerator.test.entities.util.TenentProperty;

@Entity
public class ArrendatarioHorarioAtendimento implements TenentProperty {

    @Id
    @OneToOne
    private Tenent arrendatario;


    @NotEmpty
    @Size(min=1, max=5000)
    @Column(length = 5000, nullable = false)
    private String       horarioAtendimento;

    public Tenent getTenent() {
        return arrendatario;
    }

    public void setTenent(Tenent arrendatario) {
        this.arrendatario = arrendatario;
    }

    public String getHorarioAtendimento() {
        return horarioAtendimento;
    }

    public void setHorarioAtendimento(String horarioAtendimento) {
        this.horarioAtendimento = horarioAtendimento;
    }

}
