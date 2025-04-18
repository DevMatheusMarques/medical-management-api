package com.devmatheusmarques.medicalManagement.dto;

import java.math.BigDecimal;

public class DoctorReportDTO {
    private Long doctorId;
    private String doctorName;
    private String specialty;
    private String doctorEmail;
    private String doctorTelephone;
    private BigDecimal totalConsultations;

    public DoctorReportDTO(Long doctorId, String doctorName, String specialty, String doctorEmail, String doctorTelephone, BigDecimal totalConsultations) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialty = specialty;
        this.doctorEmail = doctorEmail;
        this.doctorTelephone = doctorTelephone;
        this.totalConsultations = totalConsultations;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getDoctorTelephone() {
        return doctorTelephone;
    }

    public void setDoctorTelephone(String doctorTelephone) {
        this.doctorTelephone = doctorTelephone;
    }

    public BigDecimal getTotalConsultations() {
        return totalConsultations;
    }

    public void setTotalConsultations(BigDecimal totalConsultations) {
        this.totalConsultations = totalConsultations;
    }
}