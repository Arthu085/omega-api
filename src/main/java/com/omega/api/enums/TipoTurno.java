package com.omega.api.enums;

import com.omega.api.enums.converter.AbstractEnumConverter;
import com.omega.api.enums.converter.PersistableEnum;
import jakarta.persistence.Converter;

public enum TipoTurno implements PersistableEnum<String> {

    DIA("D", "Dia"),
    NOITE("N", "Noite");

    private String value;
    private String descricao;

    TipoTurno(String value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static TipoTurno fromValue(String value) {
        for (TipoTurno status : values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Enum Inválido: " + value);
    }

    @Converter(autoApply = true)
    public static class TipoTurnoConverter extends AbstractEnumConverter<TipoTurno, String> {
        public TipoTurnoConverter() {
            super(TipoTurno.class);
        }
    }
}