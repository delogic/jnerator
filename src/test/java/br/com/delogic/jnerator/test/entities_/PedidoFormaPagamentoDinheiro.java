package br.com.delogic.jnerator.test.entities_;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Dinheiro")
public class PedidoFormaPagamentoDinheiro extends PedidoFormaPagamento {

    @Column(precision = 10, scale = 2)
    private BigDecimal valorApresentado;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorTroco;

    public BigDecimal getValorApresentado() {
        return valorApresentado;
    }

    public void setValorApresentado(BigDecimal valorApresentado) {
        this.valorApresentado = valorApresentado;
    }

    public BigDecimal getValorTroco() {
        return valorTroco;
    }

    public void setValorTroco(BigDecimal valorTroco) {
        this.valorTroco = valorTroco;
    }

    @Override
    public String getDescricao() {
        return "Dinheiro, pago ao entregador";
    }

    @Override
    public String getLembretePagamento() {
        if (valorTroco == null) {
            return "Pagamento em dinheiro. Não necessita de troco.";
        } else {
            return "Enviar R$ ";
        }
    }
}