package com.devmatheusmarques.medicalManagement.util;

import jakarta.persistence.AttributeConverter;


public class ConsultationStatusConverter implements AttributeConverter<ConsultationStatus, String> {
    @Override
    public String convertToDatabaseColumn(ConsultationStatus consultationStatus) {
        if (consultationStatus == null) {
            return null;
        }
        switch (consultationStatus) {
            case PENDING:
                return "Pendente";
            case FINISHED:
                return "Finalizada";
            case CANCELED:
                return "Cancelada";
            default:
                throw new IllegalArgumentException("Unknown status: " + consultationStatus);
        }
    }

    @Override
    public ConsultationStatus convertToEntityAttribute(String descricao) {
        if (descricao == null) {
            return null;
        }
        if ("Pendente".equalsIgnoreCase(descricao)) {
            return ConsultationStatus.PENDING;
        } else if ("Finalizada".equalsIgnoreCase(descricao)) {
            return ConsultationStatus.FINISHED;
        } else if ("Cancelada".equalsIgnoreCase(descricao)) {
            return ConsultationStatus.CANCELED;
        } else {
            throw new IllegalArgumentException("Unknown description: " + descricao);
        }
    }

}
