package br.com.delogic.jnerator.test.entities_;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.delogic.jnerator.test.entities.Tenent;
import br.com.delogic.jnerator.test.entities.enums.FormaPagamento;
import br.com.delogic.jnerator.test.entities.util.TenentProperty;

@Entity
public class ArrendatarioFormasPagamento implements TenentProperty {

    @Id
    @OneToOne
    private Tenent        arrendatario;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "arrendatarioformaspagbalcao", joinColumns = { @JoinColumn(name = "ARRENDATARIO_ID", nullable = false) })
    @Enumerated(EnumType.STRING)
    @Column(name = "FORMAPAGAMENTO", length = 50, nullable = false)
    private Set<FormaPagamento> formasPagamentoBalcao;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "arrendatarioformaspagentrega", joinColumns = { @JoinColumn(name = "ARRENDATARIO_ID", nullable = false) })
    @Enumerated(EnumType.STRING)
    @Column(name = "FORMAPAGAMENTO", length = 50, nullable = false)
    private Set<FormaPagamento> formasPagamentoEntrega;

    public Tenent getTenent() {
        return arrendatario;
    }

    public void setTenent(Tenent arrendatario) {
        this.arrendatario = arrendatario;
    }

    public Set<FormaPagamento> getFormasPagamentoBalcao() {
        return formasPagamentoBalcao;
    }

    public void setFormasPagamentoBalcao(Set<FormaPagamento> formasPagamentoBalcao) {
        this.formasPagamentoBalcao = formasPagamentoBalcao;
    }

    public Set<FormaPagamento> getFormasPagamentoEntrega() {
        return formasPagamentoEntrega;
    }

    public void setFormasPagamentoEntrega(Set<FormaPagamento> formasPagamentoEntrega) {
        this.formasPagamentoEntrega = formasPagamentoEntrega;
    }

}
