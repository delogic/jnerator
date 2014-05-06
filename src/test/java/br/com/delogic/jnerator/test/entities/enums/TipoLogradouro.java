package br.com.delogic.jnerator.test.entities.enums;

public enum TipoLogradouro {

    RUA("Rua", "Rua"),
    AVENIDA("Avenida", "Av."),
    AEROPORTO("Aeroporto", "Aer."),
    ALAMEDA("Alameda", "Al."),
    AREA("Área", "Á."),
    CAMPO("Campo", "Cpo."),
    CHACARA("Chácara", "Chác."),
    COLONIA("Colônia", "Col."),
    CONDOMINIO("Condomínio", "Cond."),
    CONJUNTO("Conjunto", "Cj."),
    DISTRITO("Distrito", "Dt."),
    ESPLANADA("Esplanada", "Esp."),
    ESTACAO("Estação", "Etç."),
    ESTRADA("Estrada", "Est."),
    FAVELA("Favela", "Fav."),
    FAZENDA("Fazenda", "Faz."),
    FEIRA("Feira", "Fra."),
    JARDIM("Jardim", "Jd."),
    LADEIRA("Ladeira", "Ld."),
    LAGO("Lago", "Lg."),
    LAGOA("Lagoa", "Lga."),
    LARGO("Largo", "Lrg."),
    LOTEAMENTO("Loteamento", "Lot."),
    MORRO("Morro", "Mro."),
    NUCLEO("Núcleo", "Núc."),
    PARQUE("Parque", "Prq."),
    PASSARELA("Passarela", "Psa."),
    PATIO("Pátio", "Pát."),
    PRACA("Praça", "Pça."),
    QUADRA("Quadra", "Q."),
    RECANTO("Recanto", "Rec."),
    RESIDENCIAL("Residencial", "Res."),
    RODOVIA("Rodovia", "Rod."),
    SETOR("Setor", "St."),
    SITIO("Sítio", "Sít."),
    TRAVESSA("Travessa", "Tv."),
    TRECHO("Trecho", "Trv."),
    TREVO("Trevo", "Trv."),
    VALE("Vale", "Vle."),
    VEREDA("Vereda", "Ver."),
    VIA("Via", "V."),
    VIADUTO("Viaduto", "Vd."),
    VIELA("Viela", "Vla."),
    VILA("Vila", "Vl."),
    OUTRO("Outro", "");

    private final String descricao;
    private final String abreviacao;

    private TipoLogradouro(String desc, String abr) {
        this.descricao = desc;
        this.abreviacao = abr;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

}
