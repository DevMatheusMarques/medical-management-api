package com.devmatheusmarques.medicalManagement.dto;

import java.math.BigDecimal;

public class SpecialtyConsultationReportDTO {
    private String specialty;
    private BigDecimal consultationCount;
    private BigDecimal doctorCount;

    public SpecialtyConsultationReportDTO(String specialty, BigDecimal consultationCount, BigDecimal doctorCount) {
        this.specialty = specialty;
        this.consultationCount = consultationCount;
        this.doctorCount = doctorCount;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public BigDecimal getConsultationCount() {
        return consultationCount;
    }

    public void setConsultationCount(BigDecimal consultationCount) {
        this.consultationCount = consultationCount;
    }

    public BigDecimal getDoctorCount() {
        return doctorCount;
    }

    public void setDoctorCount(BigDecimal doctorCount) {
        this.doctorCount = doctorCount;
    }
}
