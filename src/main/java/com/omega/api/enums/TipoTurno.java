package com.omega.api.enums;

public enum TipoTurno {

    DIA("D", "Dia"),
    NOITE("N", "Noite");

    private String value;
    private String descricao;

    TipoTurno(String value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }
}