package com.omega.api.enums;

public enum StatusForno {
    EM_MANUTENCAO("M", "Em manutenção"),
    ATIVO("A", "Ativo");

    private String value;
    private String descricao;

    StatusForno(String value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }
}