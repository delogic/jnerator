package br.com.delogic.jnerator.test.entities_;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("Cidade")
public class RegiaoAtendimentoCidade extends RegiaoAtendimento {

    @NotNull
    private Cidade cidade;

    @Override
    public String getDescricao() {
        return cidade.getNome();
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

}
