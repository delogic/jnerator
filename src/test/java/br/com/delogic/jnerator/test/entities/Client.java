package br.com.delogic.jnerator.test.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE", length = 50)
public abstract class Client extends LongEntityId {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqClient")
    @SequenceGenerator(name = "seqClient", sequenceName = "seq_client", allocationSize = 1, initialValue = 1)
    private Long   id;

    @Column(length = 100, nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
