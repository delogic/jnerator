package br.com.delogic.jnerator.test.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
public class Tenent extends LongEntityId {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqArrendatario")
    @SequenceGenerator(name = "seqArrendatario", sequenceName = "seq_arrendatario", allocationSize = 1, initialValue = 1)
    @Column(length = 15, precision = 15, scale = 15)
    private Long    id;

    @NotEmpty
    @Size(min = 1, max = 100)
    @Column(length = 100)
    private String  companyName;

    @NotEmpty
    @Email
    @Size(min = 1, max = 50)
    @Column(length = 50, nullable = false)
    private String  email;

    @Column(nullable = false)
    private Boolean active;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date    lastAccess;

    @Column(length = 100, unique = true)
    private String  domain;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

}
