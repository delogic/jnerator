package br.com.delogic.jnerator.test.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Dinheiro")
public class CashPaymentMode extends PaymenteMode {

    @Column(precision = 10, scale = 2)
    private BigDecimal presented;

    @Column(precision = 10, scale = 2)
    private BigDecimal change;

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public BigDecimal getPresented() {
        return presented;
    }

    public void setPresented(BigDecimal presented) {
        this.presented = presented;
    }

}