package com.omega.api.enums;

import com.omega.api.enums.converter.AbstractEnumConverter;
import com.omega.api.enums.converter.PersistableEnum;
import jakarta.persistence.Converter;

public enum StatusProducaoResponsavel implements PersistableEnum<String> {
    PRODUCAO_INICIADA("I", "Produção iniciada"),
    PRODUCAO_FINALIZADA("F", "Produção finalizada");

    private String value;
    private String descricao;

    StatusProducaoResponsavel(String value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static StatusProducaoResponsavel fromValue(String value) {
        for (StatusProducaoResponsavel status : values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Enum Inválido: " + value);
    }

    @Converter(autoApply = true)
    public static class StatusProducaoResponsavelConverter extends AbstractEnumConverter<StatusProducaoResponsavel, String> {
        public StatusProducaoResponsavelConverter() {
            super(StatusProducaoResponsavel.class);
        }
    }
}
