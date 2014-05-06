package br.com.delogic.jnerator.test.entities.enums;

public enum RedeSocial {

    FACEBOOK("dd/MM/yyyy"), GOOGLE_PLUS("yyyy-MM-dd");

    private final String dateFormat;

    private RedeSocial(String df) {
        this.dateFormat = df;
    }

    public String getDateFormat() {
        return dateFormat;
    }

}
