package br.com.delogic.jnerator.test.entities.enums;

import java.util.LinkedList;
import java.util.List;

import br.com.delogic.jnerator.test.entities_.PedidoFormaPagamento;
import br.com.delogic.jnerator.test.entities_.PedidoFormaPagamentoCheque;
import br.com.delogic.jnerator.test.entities_.PedidoFormaPagamentoDinheiro;
import br.com.delogic.jnerator.test.entities_.PedidoFormaPagamentoMaquinaCartao;

public enum FormaPagamento {

    DINHEIRO("Dinheiro", false) {
        @Override
        public PedidoFormaPagamento criarPedidoFormaPagamento() {
            return new PedidoFormaPagamentoDinheiro();
        }
    },
    CHEQUE("Cheque", false) {
        @Override
        public PedidoFormaPagamento criarPedidoFormaPagamento() {
            return new PedidoFormaPagamentoCheque();
        }
    },
    CARTAO_DEBITO("Cartão Débito", true),
    CARTAO_CREDITO_VISA("Cartão Crédito Visa", true),
    CARTAO_CREDITO_MASTER("Cartão Crédito Master", true),
    CARTAO_CREDITO_AMERICAN_EXPRESS("Cartão Crédito American Express", true),
    CARTAO_CREDITO_AURA("Cartão Crédito Aura", true),
    CARTAO_CREDITO_HIPERCARD("Cartão Crédito Hipercard", true),
    CARTAO_CREDITO_SOROCRED("Cartão Crédito Sorocred", true),
    CARTAO_CREDITO_DINNERSCLUB("Cartão Crédito Dinners Club", true),
    CARTAO_REFEICAO_VISAVALE("Cartão Refeição VisaVale", true),
    CARTAO_REFEICAO_SODEXHO("Cartão Refeição Sodexho", true),
    CARTAO_REFEICAO_VR("Cartão Refeição VR", true);

    private FormaPagamento(String desc, boolean cartao) {
        this.descricao = desc;
        this.cartao = cartao;
    }

    private final String                      descricao;
    private final boolean                     cartao;
    private static final List<FormaPagamento> formasPagamentoCartao = new LinkedList<FormaPagamento>();

    public PedidoFormaPagamento criarPedidoFormaPagamento() {
        return new PedidoFormaPagamentoMaquinaCartao();
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isCartao() {
        return cartao;
    }

    static {
        for (FormaPagamento fp : values()) {
            if (fp.cartao) {
                formasPagamentoCartao.add(fp);
            }
        }
    }

    public static List<FormaPagamento> getFormasPagamentoCartao() {
        return new LinkedList<FormaPagamento>(formasPagamentoCartao);
    }

}
