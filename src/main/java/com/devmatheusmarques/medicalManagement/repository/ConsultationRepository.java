package com.devmatheusmarques.medicalManagement.repository;

import com.devmatheusmarques.medicalManagement.dto.SpecialtyCountDTO;
import com.devmatheusmarques.medicalManagement.model.Consultation;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findByDateAndTimeBetween(
            Date date, LocalTime time, LocalTime time2);

    List<Consultation> findByDoctorId(Long doctorId);

    @Query(value = "SELECT COUNT(*) AS count, MONTH(c.created_at) AS month " +
            "FROM consultations c " +
            "WHERE YEAR(c.created_at) = YEAR(CURRENT_DATE) " +
            "GROUP BY MONTH(c.created_at) " +
            "ORDER BY MONTH(c.created_at)", nativeQuery = true)
    List<Object[]> countConsultationsByMonth();

    @Query(value = "SELECT d.specialty AS specialty, COUNT(*) AS count " +
            "FROM consultations c " +
            "JOIN doctors d ON c.doctor_id = d.id " +
            "GROUP BY d.specialty " +
            "ORDER BY count DESC", nativeQuery = true)
    List<Object[]> countConsultationsBySpecialtyNative();


}
