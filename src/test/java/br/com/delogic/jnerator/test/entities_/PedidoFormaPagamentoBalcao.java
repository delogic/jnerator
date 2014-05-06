package br.com.delogic.jnerator.test.entities_;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Balcão")
public class PedidoFormaPagamentoBalcao extends PedidoFormaPagamento {

    @Override
    public String getDescricao() {
        return "Será pago diretamente no balcão ao retirar o produto";
    }

    @Override
    public String getLembretePagamento() {
        return "Deverá pagar no balcão";
    }

}
