package com.omega.api.enums;

public enum SituacaoForno {

    DESLIGADO("D", "Desligado"),
    LIGADO("L", "Ligado");

    private String value;
    private String descricao;

    SituacaoForno(String value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }
}
