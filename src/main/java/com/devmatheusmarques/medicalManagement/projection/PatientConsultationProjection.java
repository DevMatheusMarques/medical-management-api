package com.devmatheusmarques.medicalManagement.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PatientConsultationProjection {
    Long getPatientId();
    String getPatientName();
    String getPatientEmail();
    String getPatientTelephone();
    String getPatientStatus();
    BigDecimal getConsultationCount();
    LocalDate getLastConsultation();
}

