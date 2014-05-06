package br.com.delogic.jnerator.test.entities_;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import br.com.delogic.jnerator.test.entities.City;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("Cidade")
public class RegiaoAtendimentoCidade extends RegiaoAtendimento {

    @NotNull
    private City cidade;

    @Override
    public String getDescricao() {
        return cidade.getName();
    }

    public City getCidade() {
        return cidade;
    }

    public void setCidade(City cidade) {
        this.cidade = cidade;
    }

}
