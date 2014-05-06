package br.com.delogic.jnerator.test.entities_;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.delogic.jnerator.test.entities.Tenent;
import br.com.delogic.jnerator.test.entities.util.TenentProperty;

@Entity
public class ArrendatarioEndereco implements TenentProperty {

    @Id
    @OneToOne
    @JoinColumn(nullable = false)
    private Tenent arrendatario;

    private Endereco     endereco;

    public Tenent getTenent() {
        return arrendatario;
    }

    public void setTenent(Tenent arrendatario) {
        this.arrendatario = arrendatario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
