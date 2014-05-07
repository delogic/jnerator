package br.com.delogic.jnerator.test.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.delogic.jnerator.test.entities.enums.SocialNetwork;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("Online")
public class OnlineClient extends Client {

    @Column(length = 50)
    private String        phone;

    @Column(unique = true, length = 50, nullable = false)
    private String        email;

    @Column(columnDefinition = "BYTEA", precision = 255)
    private byte[]        password;

    @Column(columnDefinition = "BYTEA", precision = 255)
    private byte[]        salt;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date          birthDate;

    private Integer       successfulOrders;

    private Integer       canceledOrders;

    @Column(length = 200)
    private String        socialId;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private SocialNetwork socialNetwork;

    @Column(length = 300)
    private String        socialUrl;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getSuccessfulOrders() {
        return successfulOrders;
    }

    public void setSuccessfulOrders(Integer successfulOrders) {
        this.successfulOrders = successfulOrders;
    }

    public Integer getCanceledOrders() {
        return canceledOrders;
    }

    public void setCanceledOrders(Integer canceledOrders) {
        this.canceledOrders = canceledOrders;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public SocialNetwork getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(SocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public String getSocialUrl() {
        return socialUrl;
    }

    public void setSocialUrl(String socialUrl) {
        this.socialUrl = socialUrl;
    }

}
