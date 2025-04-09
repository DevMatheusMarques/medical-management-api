package com.devmatheusmarques.medicalManagement.model;

import com.devmatheusmarques.medicalManagement.util.Status;
import com.devmatheusmarques.medicalManagement.util.StatusConverter;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", nullable = false)
    private Date birth_date;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "telephone", nullable = false)
    private String telephone;
    @Column(name = "address", nullable = false)
    private String address;
    @Convert(converter = StatusConverter.class)
    @Column(name = "status", nullable = false)
    private Status status;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    public Patient() {
    }

    public Patient(Long id, String name, String cpf, Date birth_date, String email, String telephone, String address, Status status, LocalDateTime created_at) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.birth_date = birth_date;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.status = status;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) && Objects.equals(name, patient.name) && Objects.equals(cpf, patient.cpf) && Objects.equals(birth_date, patient.birth_date) && Objects.equals(email, patient.email) && Objects.equals(telephone, patient.telephone) && Objects.equals(address, patient.address) && status == patient.status && Objects.equals(created_at, patient.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cpf, birth_date, email, telephone, address, status, created_at);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", birth_date=" + birth_date +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", created_at=" + created_at +
                '}';
    }
}
