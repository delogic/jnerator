package br.com.delogic.jnerator.test.entities_;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;

import br.com.delogic.jnerator.test.entities.LongEntityId;

@SuppressWarnings("serial")
@Entity
public class PedidoItemProduto extends LongEntityId {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "seqPedidoItemProduto")
    @SequenceGenerator(name= "seqPedidoItemProduto", sequenceName= "seq_pedidoitemproduto", allocationSize = 1, initialValue = 1)
    private Long       id;

    @JoinColumn(nullable = false)
    private PedidoItem pedidoItem;

    @Column(length = 100, nullable = false)
    private String     nome;

    @Column(length = 100, nullable = false)
    private String     descricao;

    @Column(length = 50, nullable = false)
    private String     categoria;

    @Column(length = 50, nullable = false)
    private String     unidadeMedida;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorUnitario;

    @Column(length = 50)
    private String     codigoExterno;

    @Column(nullable = false, precision = 8)
    private Long       idProdutoOriginal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Long getIdProdutoOriginal() {
        return idProdutoOriginal;
    }

    public void setIdProdutoOriginal(Long idProdutoOriginal) {
        this.idProdutoOriginal = idProdutoOriginal;
    }

    public String getCodigoExterno() {
        return codigoExterno;
    }

    public void setCodigoExterno(String codigoExterno) {
        this.codigoExterno = codigoExterno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PedidoItem getPedidoItem() {
        return pedidoItem;
    }

    public void setPedidoItem(PedidoItem pedidoItem) {
        this.pedidoItem = pedidoItem;
    }

}
