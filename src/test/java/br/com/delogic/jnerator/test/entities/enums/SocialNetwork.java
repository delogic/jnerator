package br.com.delogic.jnerator.test.entities.enums;

public enum SocialNetwork {

    FACEBOOK("dd/MM/yyyy"), GOOGLE_PLUS("yyyy-MM-dd");

    private final String dateFormat;

    private SocialNetwork(String df) {
        this.dateFormat = df;
    }

    public String getDateFormat() {
        return dateFormat;
    }

}
