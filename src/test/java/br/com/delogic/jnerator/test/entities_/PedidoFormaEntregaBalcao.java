package br.com.delogic.jnerator.test.entities_;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Balcão")
public class PedidoFormaEntregaBalcao extends PedidoFormaEntrega {

    @Override
    public String getDescricao() {
        return "Balcão - O cliente irá retirar o pedido diretamente no balcão";
    }

}
