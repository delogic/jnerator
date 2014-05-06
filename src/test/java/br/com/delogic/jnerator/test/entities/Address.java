package br.com.delogic.jnerator.test.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.delogic.jnerator.test.entities.enums.AddressType;

@Embeddable
public class Address {

    @NotEmpty
    @Size(max = 8, min = 8)
    @Column(length = 8)
    private String      zipCode;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private AddressType addressType;

    @NotEmpty
    @Size(max = 100, min = 1)
    @Column(length = 100)
    private String      address;

    @NotEmpty
    @Size(max = 100, min = 1)
    @Column(length = 100)
    private String      district;

    @NotNull
    @Valid
    private City        city;

    @NotEmpty
    @Size(max = 50, min = 1)
    @Column(length = 50)
    private String      number;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
