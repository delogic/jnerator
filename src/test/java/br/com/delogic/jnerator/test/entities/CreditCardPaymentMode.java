package br.com.delogic.jnerator.test.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.delogic.jnerator.test.entities.enums.CreditCards;

@Entity
@DiscriminatorValue("Credit Card")
public class CreditCardPaymentMode extends PaymenteMode {

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private CreditCards creditCard;

    public CreditCards getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCards creditCard) {
        this.creditCard = creditCard;
    }

}
