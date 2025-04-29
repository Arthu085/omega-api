package com.omega.api.enums;

public enum StatusProducao {
    PARADO("P", "Parado"),
    EXECUTANDO("E", "Executando"),
    FINALIZADA("F", "Finalizada");

    private String value;
    private String descricao;

    StatusProducao(String value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }
}
