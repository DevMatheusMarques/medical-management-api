package com.devmatheusmarques.medicalManagement.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }
        // Converte o enum para "Ativo" ou "Inativo"
        return status == Status.ACTIVE ? "Ativo" : "Inativo";
    }

    @Override
    public Status convertToEntityAttribute(String descricao) {
        if (descricao == null) {
            return null;
        }
        // Converte "Ativo" para Status.ACTIVE e "Inativo" para Status.INACTIVE
        return "Ativo".equalsIgnoreCase(descricao) ? Status.ACTIVE : Status.INACTIVE;
    }
}

