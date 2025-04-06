package com.devmatheusmarques.medicalManagement.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ConsultationStatus {
    PENDING("Pendente"),
    FINISHED("Finalizada"),
    CANCELED("Cancelada");

    private final String descricao;

    ConsultationStatus(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    public static ConsultationStatus fromString(String text) {
        for (ConsultationStatus status : ConsultationStatus.values()) {
            if (status.descricao.equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException("ConsultationStatus inválido: " + text);
    }
}
