package br.com.delogic.jnerator.test.entities_;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;

import br.com.delogic.jnerator.test.entities.Address;

@SuppressWarnings("serial")
@Entity
public class ClienteOnlineEndereco extends LongEntityId {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqClienteOnlineEndereco")
    @SequenceGenerator(name = "seqClienteOnlineEndereco", sequenceName = "seq_clienteonlineendereco", allocationSize = 1, initialValue = 1)
    private Long          id;

    @JoinColumn(nullable = false)
    private ClienteOnline cliente;

    private Address      endereco;

    public ClienteOnline getCliente() {
        return cliente;
    }

    public void setCliente(ClienteOnline cliente) {
        this.cliente = cliente;
    }

    public Address getEndereco() {
        return endereco;
    }

    public void setEndereco(Address endereco) {
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}