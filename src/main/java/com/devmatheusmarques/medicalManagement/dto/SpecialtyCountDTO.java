package com.devmatheusmarques.medicalManagement.dto;

public class SpecialtyCountDTO {
    private String specialty;
    private Long count;

    public SpecialtyCountDTO(String specialty, Long count) {
        this.specialty = specialty;
        this.count = count;
    }

    public String getSpecialty() {
        return specialty;
    }

    public Long getCount() {
        return count;
    }
}
