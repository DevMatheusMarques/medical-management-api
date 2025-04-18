package com.devmatheusmarques.medicalManagement.projection;

import java.math.BigDecimal;

public interface DoctorReportProjection {
    Long getDoctorId();
    String getDoctorName();
    String getSpecialtyName();
    String getDoctorEmail();
    String getDoctorTelephone();
    BigDecimal getTotalConsultations();
}

