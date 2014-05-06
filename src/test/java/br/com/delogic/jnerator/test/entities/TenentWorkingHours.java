package br.com.delogic.jnerator.test.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.delogic.jnerator.test.entities.util.TenentProperty;

@Entity
public class TenentWorkingHours implements TenentProperty {

    @Id
    @OneToOne
    private Tenent tenent;

    @NotEmpty
    @Size(min = 1, max = 5000)
    @Column(length = 5000, nullable = false)
    private String workingHours;

    public Tenent getTenent() {
        return tenent;
    }

    public void setTenent(Tenent tenent) {
        this.tenent = tenent;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

}
