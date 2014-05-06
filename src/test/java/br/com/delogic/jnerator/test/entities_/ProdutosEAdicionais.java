package br.com.delogic.jnerator.test.entities_;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import br.com.delogic.jnerator.test.entities.Product;
import br.com.delogic.jnerator.test.entities.TenentEntity;

@SuppressWarnings("serial")
@Entity
public class ProdutosEAdicionais extends TenentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=  "seqProdutoseAdicionais")
    @SequenceGenerator(name= "seqProdutoseAdicionais", sequenceName= "seq_produtoseadicionais", allocationSize = 1, initialValue = 1)
    private Long             id;

    @JoinColumn(name = "PRODUTO_ID", nullable = false)
    private Product          produto;

    @JoinColumn(name = "ADICIONAL_ID", nullable = false)
    private ProdutoAdicional adicional;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduto() {
        return produto;
    }

    public void setProduto(Product produto) {
        this.produto = produto;
    }

    public ProdutoAdicional getAdicional() {
        return adicional;
    }

    public void setAdicional(ProdutoAdicional adicional) {
        this.adicional = adicional;
    }

}
