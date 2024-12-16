package com.devmatheusmarques.medicalManagement.dto;

import com.devmatheusmarques.medicalManagement.model.Doctor;
import com.devmatheusmarques.medicalManagement.model.Patient;
import com.devmatheusmarques.medicalManagement.util.ConsultationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class ConsultationRequestDTO {

    private Long id;
    private String patient;
    private String doctor;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date date;
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

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
