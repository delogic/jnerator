package br.com.delogic.jnerator.test.entities_;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.delogic.jnerator.test.entities.TenentEntity;

@SuppressWarnings("serial")
@Entity
public class VisualizacaoVitrine extends TenentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqVisualizacaoVitrine")
    @SequenceGenerator(name = "seqVisualizacaoVitrine", sequenceName = "seq_visualizacaovitrine", allocationSize = 1, initialValue = 1)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date data;

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
