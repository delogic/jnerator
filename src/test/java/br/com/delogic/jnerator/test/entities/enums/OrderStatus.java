package br.com.delogic.jnerator.test.entities.enums;

public enum OrderStatus {

    NOVO("Novo", "criado"), ACEITO("Aceito", "aceito"), ENTREGA("Em Entrega", "entregue"), FINALIZADO("Finalizado", "finalizado"), RECUSADO(
        "Recusado", "recusado");

    private final String descricao;
    private final String descricaoAcao;

    private OrderStatus(String desc, String acao) {
        this.descricao = desc;
        this.descricaoAcao = acao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDescricaoAcao() {
        return descricaoAcao;
    }

}
