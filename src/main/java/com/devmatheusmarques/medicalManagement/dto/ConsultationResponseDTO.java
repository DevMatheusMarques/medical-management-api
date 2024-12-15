package com.devmatheusmarques.medicalManagement.dto;

import com.devmatheusmarques.medicalManagement.model.Doctor;
import com.devmatheusmarques.medicalManagement.model.Patient;
import com.devmatheusmarques.medicalManagement.util.ConsultationStatus;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class ConsultationResponseDTO {

    private Patient patient;
    private Doctor doctor;
    private Date dateTime;
    private ConsultationStatus status;
    private String observations;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public ConsultationStatus getStatus() {
        return status;
    }

    public void setStatus(ConsultationStatus status) {
        this.status = status;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ConsultationResponseDTO that = (ConsultationResponseDTO) o;
        return Objects.equals(patient, that.patient) && Objects.equals(doctor, that.doctor) && Objects.equals(dateTime, that.dateTime) && status == that.status && Objects.equals(observations, that.observations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patient, doctor, dateTime, status, observations);
    }

    @Override
    public String toString() {
        return "ConsultationResponseDTO{" +
                "patient=" + patient +
                ", doctor=" + doctor +
                ", dateTime=" + dateTime +
                ", status=" + status +
                ", observations='" + observations + '\'' +
                '}';
    }
}
