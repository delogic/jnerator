package br.com.delogic.jnerator.test.entities_;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.delogic.jnerator.test.entities.enums.TipoLogradouro;

@Embeddable
public class Endereco {

    @NotEmpty
    @Size(max = 8, min = 8)
    @Column(length = 8)
    private String         cep;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private TipoLogradouro tipoLogradouro;

    @NotEmpty
    @Size(max = 100, min = 1)
    @Column(length = 100)
    private String         logradouro;

    @NotEmpty
    @Size(max = 100, min = 1)
    @Column(length = 100)
    private String         bairro;

    @NotNull
    @Valid
    private Cidade         cidade;

    @NotEmpty
    @Size(max = 50, min = 1)
    @Column(length = 50)
    private String         numeroComplemento;

    public String getDescricao() {
        StringBuilder sb = new StringBuilder();
        sb.append(tipoLogradouro.getAbreviacao());
        sb.append(" ");
        sb.append(logradouro);
        sb.append(", ");
        sb.append(numeroComplemento);
        sb.append(", ");
        sb.append(bairro);
        sb.append(", ");
        sb.append(cidade.getNome());
        sb.append(" - ");
        sb.append(cidade.getUf().name());
        return sb.toString();
    }

    public TipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumeroComplemento() {
        return numeroComplemento;
    }

    public void setNumeroComplemento(String numeroComplemento) {
        this.numeroComplemento = numeroComplemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

}
