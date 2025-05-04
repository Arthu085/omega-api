package com.omega.api.enums;

import com.omega.api.enums.converter.AbstractEnumConverter;
import com.omega.api.enums.converter.PersistableEnum;
import jakarta.persistence.Converter;

public enum StatusProducao implements PersistableEnum<String> {
    PARADO("P", "Parado"),
    EXECUTANDO("E", "Executando"),
    FINALIZADA("F", "Finalizada");

    private String value;
    private String descricao;

    StatusProducao(String value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static StatusProducao fromValue(String value) {
        for (StatusProducao status : values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Enum Inválido: " + value);
    }

    @Converter(autoApply = true)
    public static class StatusProducaoConverter extends AbstractEnumConverter<StatusProducao, String> {
        public StatusProducaoConverter() {
            super(StatusProducao.class);
        }
    }
}
