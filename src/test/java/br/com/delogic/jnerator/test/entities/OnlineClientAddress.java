package br.com.delogic.jnerator.test.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
public class OnlineClientAddress extends LongEntityId {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqOnlineClientAddress")
    @SequenceGenerator(name = "seqOnlineClientAddress", sequenceName = "seq_OnlineClientAddress", allocationSize = 1, initialValue = 1)
    private Long         id;

    @JoinColumn(nullable = false)
    private OnlineClient client;

    private Address      address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OnlineClient getClient() {
        return client;
    }

    public void setClient(OnlineClient client) {
        this.client = client;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}