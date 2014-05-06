package br.com.delogic.jnerator.test.entities_;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.delogic.jnerator.test.entities.enums.FormaPagamento;

@Entity
@DiscriminatorValue("Máquina Cartão")
public class PedidoFormaPagamentoMaquinaCartao extends PedidoFormaPagamento {

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private FormaPagamento cartaoUtilizado;

    public FormaPagamento getCartaoUtilizado() {
        return cartaoUtilizado;
    }

    public void setCartaoUtilizado(FormaPagamento cartaoUtilizado) {
        this.cartaoUtilizado = cartaoUtilizado;
    }

    @Override
    public String getDescricao() {
        return cartaoUtilizado == null ?
                                      "Enviar Máquina de Cartão Crédito/Débito" :
                                      cartaoUtilizado.getDescricao() + " - Enviar a Máquina de Cartão";
    }

    @Override
    public String getLembretePagamento() {
        return "Enviar a máquina de cartão";
    }

}
