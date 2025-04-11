package com.omega.api.enums;

public enum StatusProducaoResponsavel {
    PRODUCAO_INICIADA("I", "Produção iniciada"),
    PRODUCAO_FINALIZADA("F", "Produção finalizada");

    private String value;
    private String descricao;

    StatusProducaoResponsavel(String value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }
}
