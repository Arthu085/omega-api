package com.omega.api.enums;

public enum StatusUsuario {
    INATIVO("I", "Inativo"),
    ATIVO("A", "Ativo");

    private String value;
    private String descricao;

    StatusUsuario(String value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

}
