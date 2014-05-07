package br.com.delogic.jnerator.test.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("Zip")
public class DeliveryRegionByZipCode extends DeliveryRegion {

    @NotNull
    @Min(1001000)
    @Max(99999999)
    @Column(precision = 8)
    private Integer zipFrom;

    @NotNull
    @Min(1001000)
    @Max(99999999)
    @Column(precision = 8)
    private Integer zipTo;

    public Integer getZipFrom() {
        return zipFrom;
    }

    public void setZipFrom(Integer zipFrom) {
        this.zipFrom = zipFrom;
    }

    public Integer getZipTo() {
        return zipTo;
    }

    public void setZipTo(Integer zipTo) {
        this.zipTo = zipTo;
    }

}
