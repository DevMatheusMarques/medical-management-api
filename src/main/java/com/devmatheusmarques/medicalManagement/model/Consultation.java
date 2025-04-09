package com.devmatheusmarques.medicalManagement.model;

import com.devmatheusmarques.medicalManagement.util.ConsultationStatus;
import com.devmatheusmarques.medicalManagement.util.ConsultationStatusConverter;
import com.devmatheusmarques.medicalManagement.util.StatusConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

@Entity(name = "consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false, foreignKey = @ForeignKey(name = "FK_consultation_patient"))
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false, foreignKey = @ForeignKey(name = "FK_consultation_doctor"))
    private Doctor doctor;
    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date date;
    @Temporal(TemporalType.TIME)
    @Column(name = "time", nullable = false)
    private LocalTime time;
    @Convert(converter = ConsultationStatusConverter.class)
    @Column(name = "status", nullable = false)
    private ConsultationStatus status;
    @Column(name = "observations")
    private String observations;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    public Consultation() {
    }

    public Consultation(Long id, Patient patient, Doctor doctor, Date date, LocalTime time, ConsultationStatus status, String observations, LocalDateTime created_at) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.status = status;
        this.observations = observations;
        this.created_at = created_at;
    }

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
        Consultation that = (Consultation) o;
        return Objects.equals(id, that.id) && Objects.equals(patient, that.patient) && Objects.equals(doctor, that.doctor) && Objects.equals(date, that.date) && Objects.equals(time, that.time) && status == that.status && Objects.equals(observations, that.observations) && Objects.equals(created_at, that.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, doctor, date, time, status, observations, created_at);
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "id=" + id +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", date=" + date +
                ", time=" + time +
                ", status=" + status +
                ", observations='" + observations + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
