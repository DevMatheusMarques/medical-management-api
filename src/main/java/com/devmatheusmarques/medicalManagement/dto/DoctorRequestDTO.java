package com.devmatheusmarques.medicalManagement.dto;

import com.devmatheusmarques.medicalManagement.model.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequestDTO {

    private Long id;
    private String name;
    private String crm;
    private Specialty specialty;
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

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
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
        DoctorRequestDTO that = (DoctorRequestDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(crm, that.crm) && Objects.equals(specialty, that.specialty) && Objects.equals(email, that.email) && Objects.equals(telephone, that.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, crm, specialty, email, telephone);
    }
}
