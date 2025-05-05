package com.omega.api.enums;

import com.omega.api.enums.converter.AbstractEnumConverter;
import com.omega.api.enums.converter.PersistableEnum;
import jakarta.persistence.Converter;

public enum StatusUsuario implements PersistableEnum<String> {
    INATIVO("I", "Inativo"),
    ATIVO("A", "Ativo");

    private String value;
    private String descricao;

    StatusUsuario(String value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static StatusUsuario fromValue(String value) {
        for (StatusUsuario status : values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Enum Inválido: " + value);
    }

    @Converter(autoApply = true)
    public static class StatusUsuarioConverter extends AbstractEnumConverter<StatusUsuario, String> {
        public StatusUsuarioConverter() {
            super(StatusUsuario.class);
        }
    }
}