package br.com.delogic.jnerator.test.entities_;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import br.com.delogic.jnerator.test.entities.Tenent;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("Local")
public class ClienteLocal extends Cliente {

    @JoinColumn(nullable = false)
    private Tenent arrendatario;

    public Tenent getArrendatario() {
        return arrendatario;
    }

    public void setArrendatario(Tenent arrendatario) {
        this.arrendatario = arrendatario;
    }

    @Override
    public String getInformacoesCompletas() {
        return getNome();
    }

}
