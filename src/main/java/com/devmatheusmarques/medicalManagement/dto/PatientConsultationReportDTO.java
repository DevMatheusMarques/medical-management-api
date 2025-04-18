package com.devmatheusmarques.medicalManagement.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PatientConsultationReportDTO {
    private Long patientId;
    private String patientName;
    private String patientEmail;
    private String patientTelephone;
    private String patientStatus;
    private BigDecimal consultationCount;
    private LocalDate lastConsultation;

    public PatientConsultationReportDTO(Long patientId, String patientName, String patientEmail, String patientTelephone, String patientStatus, BigDecimal consultationCount, LocalDate lastConsultation) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.patientTelephone = patientTelephone;
        this.patientStatus = patientStatus;
        this.consultationCount = consultationCount;
        this.lastConsultation = lastConsultation;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientTelephone() {
        return patientTelephone;
    }

    public void setPatientTelephone(String patientTelephone) {
        this.patientTelephone = patientTelephone;
    }

    public String getPatientStatus() {
        return patientStatus;
    }

    public void setPatientStatus(String patientStatus) {
        this.patientStatus = patientStatus;
    }

    public BigDecimal getConsultationCount() {
        return consultationCount;
    }

    public void setConsultationCount(BigDecimal consultationCount) {
        this.consultationCount = consultationCount;
    }

    public LocalDate getLastConsultation() {
        return lastConsultation;
    }

    public void setLastConsultation(LocalDate lastConsultation) {
        this.lastConsultation = lastConsultation;
    }
}
