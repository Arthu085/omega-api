package com.omega.api.enums;

import com.omega.api.enums.converter.AbstractEnumConverter;
import com.omega.api.enums.converter.PersistableEnum;
import jakarta.persistence.Converter;

public enum StatusForno implements PersistableEnum<String> {
    EM_MANUTENCAO("M", "Em manutenção"),
    ATIVO("A", "Ativo"),
    INATIVO("I", "Inativo");

    private String value;
    private String descricao;

    StatusForno(String value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static StatusForno fromValue(String value) {
        for (StatusForno status : values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Enum Inválido: " + value);
    }

    @Converter(autoApply = true)
    public static class StatusFornoConverter extends AbstractEnumConverter<StatusForno, String> {
        public StatusFornoConverter() {
            super(StatusForno.class);
        }
    }
}