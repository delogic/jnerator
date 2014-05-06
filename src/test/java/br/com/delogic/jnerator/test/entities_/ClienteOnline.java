package br.com.delogic.jnerator.test.entities_;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.delogic.jnerator.test.entities.enums.RedeSocial;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("Online")
public class ClienteOnline extends Cliente {

    @Column(length = 50)
    private String     telefone;

    @Column(unique = true, length = 50, nullable = false)
    private String     email;

    @Column(columnDefinition = "BYTEA", precision = 255)
    private byte[]     senha;

    @Column(columnDefinition = "BYTEA", precision = 255)
    private byte[]     salt;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date       dataNascimento;

    private Integer    pedidosFinalizados;

    private Integer    pedidosCancelados;

    @Column(length = 200)
    private String     idSocial;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private RedeSocial redeSocial;

    @Column(length = 300)
    private String     url;

    @Override
    public String getInformacoesCompletas() {
        return "";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getSenha() {
        return senha;
    }

    public void setSenha(byte[] senha) {
        this.senha = senha;
    }

    public Integer getPedidosFinalizados() {
        return pedidosFinalizados;
    }

    public void setPedidosFinalizados(Integer pedidosFinalizados) {
        this.pedidosFinalizados = pedidosFinalizados;
    }

    public Integer getPedidosCancelados() {
        return pedidosCancelados;
    }

    public void setPedidosCancelados(Integer pedidosCancelados) {
        this.pedidosCancelados = pedidosCancelados;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getIdSocial() {
        return idSocial;
    }

    public void setIdSocial(String idSocial) {
        this.idSocial = idSocial;
    }

    public RedeSocial getRedeSocial() {
        return redeSocial;
    }

    public void setRedeSocial(RedeSocial redeSocial) {
        this.redeSocial = redeSocial;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
