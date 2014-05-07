package br.com.delogic.jnerator.test.entities_;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.delogic.jnerator.test.entities.Client;
import br.com.delogic.jnerator.test.entities.LongEntityId;
import br.com.delogic.jnerator.test.entities.Tenent;
import br.com.delogic.jnerator.test.entities.enums.PedidoSituacao;
import br.com.delogic.jnerator.test.entities.util.TenentProperty;

@SuppressWarnings("serial")
@Entity
public class Pedido extends LongEntityId implements TenentProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPedido")
    @SequenceGenerator(name = "seqPedido", sequenceName = "seq_pedido", allocationSize = 1, initialValue = 1)
    @Column(precision = 10)
    private Long                 id;

    @Column(length = 50)
    private String               numero;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private PedidoSituacao       situacao;

    @JoinColumn(nullable = false)
    private Tenent         arrendatario;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date                 dataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date                 dataAlteracaoSituacao;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pedido")
    private Set<PedidoItem>      itensPedido;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal           valorProdutos;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal           valorTotal;

    @JoinColumn(nullable = false)
    private Client              cliente;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pedido")
    private PedidoFormaEntrega   formaEntrega;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pedido")
    private PedidoFormaPagamento formaPagamento;

    @Column(length = 100)
    private String               observacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public PedidoSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(PedidoSituacao situacao) {
        this.situacao = situacao;
    }

    public Tenent getTenent() {
        return arrendatario;
    }

    public void setTenent(Tenent arrendatario) {
        this.arrendatario = arrendatario;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAlteracaoSituacao() {
        return dataAlteracaoSituacao;
    }

    public void setDataAlteracaoSituacao(Date dataAlteracaoSituacao) {
        this.dataAlteracaoSituacao = dataAlteracaoSituacao;
    }

    public Set<PedidoItem> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(Set<PedidoItem> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public BigDecimal getValorProdutos() {
        return valorProdutos;
    }

    public void setValorProdutos(BigDecimal valorProdutos) {
        this.valorProdutos = valorProdutos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public PedidoFormaEntrega getFormaEntrega() {
        return formaEntrega;
    }

    public void setFormaEntrega(PedidoFormaEntrega formaEntrega) {
        this.formaEntrega = formaEntrega;
    }

    public PedidoFormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(PedidoFormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

}
