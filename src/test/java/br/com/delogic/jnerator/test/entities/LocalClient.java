package br.com.delogic.jnerator.test.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("Local")
public class LocalClient extends Client {

    @JoinColumn(nullable = false)
    private Tenent tenent;

    public Tenent getTenent() {
        return tenent;
    }

    public void setTenent(Tenent tenent) {
        this.tenent = tenent;
    }

}
