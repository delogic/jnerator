package br.com.delogic.jnerator.test.entities_;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Cheque")
public class PedidoFormaPagamentoCheque extends PedidoFormaPagamento {

    @Override
    public String getDescricao() {
        return "Cheque, diretamente ao entregador";
    }

    @Override
    public String getLembretePagamento() {
        return "Pagamento via cheque";
    }

}
