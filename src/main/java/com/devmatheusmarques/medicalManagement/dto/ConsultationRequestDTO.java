package com.devmatheusmarques.medicalManagement.dto;

import com.devmatheusmarques.medicalManagement.model.Doctor;
import com.devmatheusmarques.medicalManagement.model.Patient;
import com.devmatheusmarques.medicalManagement.util.ConsultationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class ConsultationRequestDTO {

    private Long id;
    private Patient patient;
    private Doctor doctor;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime time;
    private ConsultationStatus status;
    private String observations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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
        ConsultationRequestDTO that = (ConsultationRequestDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(patient, that.patient) && Objects.equals(doctor, that.doctor) && Objects.equals(date, that.date) && Objects.equals(time, that.time) && status == that.status && Objects.equals(observations, that.observations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, doctor, date, time, status, observations);
    }

    @Override
    public String toString() {
        return "ConsultationRequestDTO{" +
                "id=" + id +
                ", patient='" + patient + '\'' +
                ", doctor='" + doctor + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", status=" + status +
                ", observations='" + observations + '\'' +
                '}';
    }
}
