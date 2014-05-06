package br.com.delogic.jnerator.test.entities_;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
public class PedidoItemAdicional extends LongEntityId {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "seqPedidoItemAdicional")
    @SequenceGenerator(name= "seqPedidoItemAdicional", sequenceName= "seq_pedidoitemadicional", allocationSize = 1, initialValue = 1)
    @Column(precision = 8)
    private Long       id;

    @JoinColumn(nullable = false)
    private PedidoItem pedidoItem;

    @Column(length = 50, nullable = false)
    private String     nome;

    @Column(precision = 10, nullable = false)
    private BigDecimal valor;

    @Column(precision = 10, nullable = false)
    private Long       idAdicionalOriginal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidoItem getPedidoItem() {
        return pedidoItem;
    }

    public void setPedidoItem(PedidoItem pedidoItem) {
        this.pedidoItem = pedidoItem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getIdAdicionalOriginal() {
        return idAdicionalOriginal;
    }

    public void setIdAdicionalOriginal(Long idAdicionalOriginal) {
        this.idAdicionalOriginal = idAdicionalOriginal;
    }

}
