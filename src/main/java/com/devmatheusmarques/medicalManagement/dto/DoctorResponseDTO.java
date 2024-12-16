package com.devmatheusmarques.medicalManagement.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class DoctorResponseDTO {

    private Long id;
    private String name;
    private String crm;
    private String specialty;
    private String email;
    private String telephone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DoctorResponseDTO that = (DoctorResponseDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(crm, that.crm) && Objects.equals(specialty, that.specialty) && Objects.equals(email, that.email) && Objects.equals(telephone, that.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, crm, specialty, email, telephone);
    }

    @Override
    public String toString() {
        return "DoctorResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", crm='" + crm + '\'' +
                ", specialty='" + specialty + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
