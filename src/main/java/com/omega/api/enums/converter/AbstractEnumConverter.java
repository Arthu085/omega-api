package com.omega.api.enums.converter;

import jakarta.persistence.AttributeConverter;

public abstract class AbstractEnumConverter<E extends Enum<E> & PersistableEnum<T>, T> implements AttributeConverter<E, T> {

    private final Class<E> enumClass;

    protected AbstractEnumConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public T convertToDatabaseColumn(E attribute) {
        return attribute == null ? null : attribute.getValue();
    }

    @Override
    public E convertToEntityAttribute(T dbData) {
        if (dbData == null) return null;


        for (E e : enumClass.getEnumConstants()) {
            if (e.getValue().equals(dbData)) return e;
        }
        throw new IllegalArgumentException("Código inválido: " + dbData + " para " + enumClass.getSimpleName());
    }
}