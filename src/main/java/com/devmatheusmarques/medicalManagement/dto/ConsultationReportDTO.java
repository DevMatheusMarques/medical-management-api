package com.devmatheusmarques.medicalManagement.dto;

import java.time.LocalDate;

public class ConsultationReportDTO {
    private Long consultationId;
    private String patientName;
    private String doctorName;
    private String doctorSpecialty;
    private LocalDate date;
    private String status;

    public ConsultationReportDTO(Long consultationId, String patientName, String doctorName, String doctorSpecialty, LocalDate date, String status) {
        this.consultationId = consultationId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.doctorSpecialty = doctorSpecialty;
        this.date = date;
        this.status = status;
    }

    public Long getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(Long consultationId) {
        this.consultationId = consultationId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialty() {
        return doctorSpecialty;
    }

    public void setSpecialty(String doctorSpecialty) {
        this.doctorSpecialty = doctorSpecialty;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
