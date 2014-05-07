package br.com.delogic.jnerator.test.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("City")
public class DeliveryRegionByCity extends DeliveryRegion {

    @NotNull
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
