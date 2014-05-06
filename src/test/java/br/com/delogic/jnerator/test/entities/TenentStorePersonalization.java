package br.com.delogic.jnerator.test.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.delogic.jnerator.test.entities.util.TenentProperty;

@Entity
public class TenentStorePersonalization implements TenentProperty {

    @Id
    @OneToOne
    private Tenent tenent;

    @NotEmpty
    @Size(max = 5000, min = 1)
    @Column(length = 5000)
    private String aboutUs;

    @NotEmpty
    @Size(max = 100, min = 1)
    @Column(length = 100)
    private String logo;

    @NotEmpty
    @Size(max = 100, min = 1)
    @Column(length = 100)
    private String banner;

    @Size(max = 200)
    @Column(length = 200)
    private String facebookUrl;

    public Tenent getTenent() {
        return tenent;
    }

    public void setTenent(Tenent tenent) {
        this.tenent = tenent;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

}
