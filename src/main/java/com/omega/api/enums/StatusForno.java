package com.omega.api.enums;

public enum StatusForno {
    EM_MANUTENCAO(0, "Em manutenção"),
    ATIVO(1, "Ativo");

    private Integer value;
    private String descricao;

    StatusForno(Integer value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }
}
