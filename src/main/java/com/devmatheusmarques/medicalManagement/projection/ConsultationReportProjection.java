package com.devmatheusmarques.medicalManagement.projection;

import java.time.LocalDate;

public interface ConsultationReportProjection {
    Long getConsultationId();
    String getPatientName();
    String getDoctorName();
    String getDoctorSpecialty();
    LocalDate getDate();
    String getStatus();
}
