package com.devmatheusmarques.medicalManagement.dto;

import com.devmatheusmarques.medicalManagement.util.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class PatientEditDTO {

    private Long id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date birth_date;
    private String email;
    private String telephone;
    private String address;
    private Status status;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PatientEditDTO that = (PatientEditDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(birth_date, that.birth_date) && Objects.equals(email, that.email) && Objects.equals(telephone, that.telephone) && Objects.equals(address, that.address) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birth_date, email, telephone, address, status);
    }

    @Override
    public String toString() {
        return "PatientEditDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth_date=" + birth_date +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }
}
