package br.com.delogic.jnerator.test.entities_;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import br.com.delogic.jnerator.test.entities.Tenent;
import br.com.delogic.jnerator.test.entities.util.TenentProperty;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("Telefone")
public class ClienteTelefonico extends Cliente implements TenentProperty {

    @Column(length = 15)
    private String       telefone;

    @JoinColumn(nullable = false)
    private Tenent arrendatario;

    private Endereco     endereco;

    @Override
    public String getInformacoesCompletas() {
        return "";
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

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
