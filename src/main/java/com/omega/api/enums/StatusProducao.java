package com.omega.api.enums;

public enum StatusProducao {
    PARADO(0, "Parado"),
    EXECUTANDO(1, "Executando"),
    FINALIZADA(2, "Finalizada");

    private Integer value;
    private String descricao;

    private StatusProducao(Integer value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }
}
