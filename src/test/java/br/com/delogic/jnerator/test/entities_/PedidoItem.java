package br.com.delogic.jnerator.test.entities_;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
public class PedidoItem extends LongEntityId {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "seqPedidoItem")
    @SequenceGenerator(name= "seqPedidoItem", sequenceName="seq_pedidoitem", allocationSize = 1, initialValue = 1)
    private Long                      id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pedidoItem")
    private List<PedidoItemProduto>   produtos;

    @JoinColumn(nullable = false)
    private Pedido                    pedido;

    @Column(precision = 10, scale = 2)
    private BigDecimal                valorUnitario;

    @Column(precision = 10, scale = 2)
    private BigDecimal                valorTotal;

    @Column(precision = 10, scale = 3)
    private BigDecimal                quantidade;

    @Column(length = 200)
    private String                    solicitacaoEspecial;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pedidoItem")
    private List<PedidoItemAdicional> adicionais;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitarioVenda) {
        this.valorUnitario = valorUnitarioVenda;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<PedidoItemProduto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<PedidoItemProduto> produtos) {
        this.produtos = produtos;
    }

    public String getSolicitacaoEspecial() {
        return solicitacaoEspecial;
    }

    public void setSolicitacaoEspecial(String solicitacaoEspecial) {
        this.solicitacaoEspecial = solicitacaoEspecial;
    }

    public List<PedidoItemAdicional> getAdicionais() {
        return adicionais;
    }

    public void setAdicionais(List<PedidoItemAdicional> adicionais) {
        this.adicionais = adicionais;
    }

}
