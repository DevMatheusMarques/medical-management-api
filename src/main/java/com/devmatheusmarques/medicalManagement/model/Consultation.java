package com.devmatheusmarques.medicalManagement.model;

import com.devmatheusmarques.medicalManagement.util.ConsultationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(name = "time", nullable = false)
    private LocalTime time;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ConsultationStatus status;
    @Column(name = "observations")
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
        Consultation that = (Consultation) o;
        return Objects.equals(id, that.id) && Objects.equals(patient, that.patient) && Objects.equals(doctor, that.doctor) && Objects.equals(date, that.date) && Objects.equals(time, that.time) && status == that.status && Objects.equals(observations, that.observations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, doctor, date, time, status, observations);
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
                '}';
    }
}
