package br.com.delogic.jnerator.test.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Store")
public class StoreDelivered extends DeliveryMode {

}
