package br.com.delogic.jnerator.test.entities_;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("CEP")
public class RegiaoAtendimentoCep extends RegiaoAtendimento {

    @NotNull
    @Min(1001000)
    @Max(99999999)
    @Column(precision = 8)
    private Integer cepInicio;

    @NotNull
    @Min(1001000)
    @Max(99999999)
    @Column(precision = 8)
    private Integer cepFim;

    @Override
    public String getDescricao() {
        return cepFim.toString();
    }

    public Integer getCepInicio() {
        return cepInicio;
    }

    public void setCepInicio(Integer cepInicio) {
        this.cepInicio = cepInicio;
    }

    public Integer getCepFim() {
        return cepFim;
    }

    public void setCepFim(Integer cepFim) {
        this.cepFim = cepFim;
    }

}
