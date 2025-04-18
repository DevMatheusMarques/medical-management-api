package com.devmatheusmarques.medicalManagement.repository;

import com.devmatheusmarques.medicalManagement.dto.*;
import com.devmatheusmarques.medicalManagement.model.Consultation;
import com.devmatheusmarques.medicalManagement.projection.ConsultationReportProjection;
import com.devmatheusmarques.medicalManagement.projection.DoctorReportProjection;
import com.devmatheusmarques.medicalManagement.projection.PatientConsultationProjection;
import com.devmatheusmarques.medicalManagement.projection.SpecialtyConsultationReportProjection;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findByDateAndTimeBetween(
            Date date, LocalTime time, LocalTime time2);

    List<Consultation> findByDoctorId(Long doctorId);

    @Query(value = "SELECT COUNT(*) AS count, EXTRACT(MONTH FROM c.created_at) AS month " +
            "FROM consultations c " +
            "WHERE EXTRACT(YEAR FROM c.created_at) = EXTRACT(YEAR FROM CURRENT_DATE) " +
            "GROUP BY EXTRACT(MONTH FROM c.created_at) " +
            "ORDER BY month", nativeQuery = true)
    List<Object[]> countConsultationsByMonth();

    @Query(value = "SELECT s.name AS specialty_name, COUNT(*) AS total " +
            "FROM consultations c " +
            "JOIN doctors d ON c.doctor_id = d.id " +
            "JOIN specialties s ON d.specialties_id = s.id " +
            "GROUP BY s.name " +
            "ORDER BY total DESC", nativeQuery = true)
    List<Object[]> countConsultationsBySpecialtyNative();

    @Query(value = """
    SELECT 
        p.id AS patientId,
        p.name AS patientName,
        p.email AS patientEmail,
        p.telephone AS patientTelephone,
        p.status AS patientStatus,
        COUNT(c.id) AS consultationCount,
        MAX(c.date) AS lastConsultation
    FROM consultations c
    JOIN patients p ON p.id = c.patient_id
    WHERE c.date BETWEEN :startDate AND :endDate
      AND (:status IS NULL OR c.status = :status)
    GROUP BY p.id, p.name, p.email, p.telephone, p.status
""", nativeQuery = true)
    List<PatientConsultationProjection> findConsultationsByPatient(LocalDate startDate, LocalDate endDate, String status);

    @Query(value = """
    SELECT d.id AS doctorId,
           d.name AS doctorName,
           s.name AS specialtyName,
           d.email AS doctorEmail,
           d.telephone AS doctorTelephone,
           COUNT(c.id) AS totalConsultations
    FROM consultations c
    JOIN doctors d ON d.id = c.doctor_id
    JOIN specialties s ON s.id = d.specialties_id
    WHERE c.date BETWEEN :startDate AND :endDate
      AND (:specialty IS NULL OR s.name = :specialty)
    GROUP BY d.id, d.name, d.email, d.telephone, s.name
""", nativeQuery = true)
    List<DoctorReportProjection> findConsultationsByDoctor(LocalDate startDate, LocalDate endDate, String status, String specialty);

    @Query(value = """
    SELECT c.id AS consultationId,
           p.name AS patientName,
           d.name AS doctorName,
           s.name AS specialtyName,
           c.date AS date,
           c.status AS status
    FROM consultations c
    JOIN patients p ON p.id = c.patient_id
    JOIN doctors d ON d.id = c.doctor_id
    JOIN specialties s ON s.id = d.specialties_id
    WHERE c.date BETWEEN :startDate AND :endDate
    AND (:status IS NULL OR c.status = :status)
    AND (:specialty IS NULL OR s.name = :specialty)
""", nativeQuery = true)
    List<ConsultationReportProjection> findConsultations(LocalDate startDate, LocalDate endDate, String status, String specialty);

    @Query(value = """
    SELECT s.name AS specialtyName,
          COUNT(DISTINCT c.id) AS totalConsultations,
          COUNT(DISTINCT d.id) AS totalDoctors
   FROM consultations c
   JOIN doctors d ON d.id = c.doctor_id
   JOIN specialties s ON s.id = d.specialties_id
   WHERE c.date BETWEEN :startDate AND :endDate
     AND (:status IS NULL OR c.status = :status)
   GROUP BY s.name
""", nativeQuery = true)
    List<SpecialtyConsultationReportProjection> findConsultationsBySpecialty(LocalDate startDate, LocalDate endDate, String status);

    @Query(value = "SELECT * FROM consultations WHERE doctor_id = :doctorId AND date = :date AND time = :time", nativeQuery = true)
    Optional<Consultation> findByDoctorAndDateTime(
            @Param("doctorId") Long doctorId,
            @Param("date") LocalDate date,
            @Param("time") LocalTime time
    );

}


