package com.devmatheusmarques.medicalManagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "crm", unique = true, nullable = false)
    private String crm;
    @Column(name = "specialty", nullable = false)
    private String specialty;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "telephone", nullable = false)
    private String telephone;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    public Doctor() {
    }

    public Doctor(Long id, String name, String crm, String specialty, String email, String telephone, LocalDateTime created_at) {
        this.id = id;
        this.name = name;
        this.crm = crm;
        this.specialty = specialty;
        this.email = email;
        this.telephone = telephone;
        this.created_at = created_at;
    }

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

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) && Objects.equals(name, doctor.name) && Objects.equals(crm, doctor.crm) && Objects.equals(specialty, doctor.specialty) && Objects.equals(email, doctor.email) && Objects.equals(telephone, doctor.telephone) && Objects.equals(created_at, doctor.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, crm, specialty, email, telephone, created_at);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", crm='" + crm + '\'' +
                ", specialty='" + specialty + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
