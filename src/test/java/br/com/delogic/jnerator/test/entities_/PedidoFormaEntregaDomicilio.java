package br.com.delogic.jnerator.test.entities_;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.delogic.jnerator.test.entities.Address;

@Entity
@DiscriminatorValue("Domicílio")
public class PedidoFormaEntregaDomicilio extends PedidoFormaEntrega {

    private Address endereco;

    public Address getEndereco() {
        return endereco;
    }

    public void setEndereco(Address endereco) {
        this.endereco = endereco;
    }

}
