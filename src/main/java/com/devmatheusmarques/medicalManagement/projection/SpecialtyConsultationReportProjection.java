package com.devmatheusmarques.medicalManagement.projection;

import java.math.BigDecimal;

public interface SpecialtyConsultationReportProjection {
    String getSpecialtyName();
    BigDecimal getTotalConsultations();
    BigDecimal getTotalDoctors();
}
