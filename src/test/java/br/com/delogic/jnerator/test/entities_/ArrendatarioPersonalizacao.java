package br.com.delogic.jnerator.test.entities_;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.delogic.jnerator.test.entities.Tenent;
import br.com.delogic.jnerator.test.entities.util.TenentProperty;

@Entity
public class ArrendatarioPersonalizacao implements TenentProperty {

    @Id
    @OneToOne
    private Tenent arrendatario;

    @NotEmpty
    @Size(max = 5000, min = 1)
    @Column(length = 5000)
    private String       quemSomos;

    @NotEmpty
    @Size(max = 100, min = 1)
    @Column(length = 100)
    private String       logotipo;

    @NotEmpty
    @Size(max = 100, min = 1)
    @Column(length = 100)
    private String       banner;

    @Size(max = 200)
    @Column(length = 200)
    private String       facebook;

    public Tenent getTenent() {
        return arrendatario;
    }

    public void setTenent(Tenent arrendatario) {
        this.arrendatario = arrendatario;
    }

    public String getQuemSomos() {
        return quemSomos;
    }

    public void setQuemSomos(String quemSomos) {
        this.quemSomos = quemSomos;
    }

    public String getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(String logotipo) {
        this.logotipo = logotipo;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

}
