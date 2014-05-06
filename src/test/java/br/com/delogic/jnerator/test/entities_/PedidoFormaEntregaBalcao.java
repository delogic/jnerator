package br.com.delogic.jnerator.test.entities_;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Balcão")
public class PedidoFormaEntregaBalcao extends PedidoFormaEntrega {

}
