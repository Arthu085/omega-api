package com.omega.api.enums;

public enum StatusUsuario {
    INATIVO(0, "Inativo"),
    ATIVO(1, "Ativo");

    private Integer value;
    private String descricao;

    StatusUsuario(Integer value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

}
