package com.omega.api.enums;

public enum SituacaoForno {

    DESLIGADO(0, "Desligado"),
    LIGADO(1, "Ligado");

    private Integer value;
    private String descricao;

    SituacaoForno(Integer value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }
}
