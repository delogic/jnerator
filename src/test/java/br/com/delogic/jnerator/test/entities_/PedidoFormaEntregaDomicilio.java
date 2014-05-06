package br.com.delogic.jnerator.test.entities_;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Domicílio")
public class PedidoFormaEntregaDomicilio extends PedidoFormaEntrega {

    private Endereco endereco;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String getDescricao() {
        return "Domicílio - Entregar em: " + endereco.getDescricao();
    }

}
