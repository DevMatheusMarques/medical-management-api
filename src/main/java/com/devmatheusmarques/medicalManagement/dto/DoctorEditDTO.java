package com.devmatheusmarques.medicalManagement.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class DoctorEditDTO {

    private Long id;
    private String name;
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
        DoctorEditDTO that = (DoctorEditDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(specialty, that.specialty) && Objects.equals(email, that.email) && Objects.equals(telephone, that.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, specialty, email, telephone);
    }

    @Override
    public String toString() {
        return "DoctorEditDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialty='" + specialty + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
