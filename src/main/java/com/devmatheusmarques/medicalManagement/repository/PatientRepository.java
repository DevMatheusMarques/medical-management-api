package com.devmatheusmarques.medicalManagement.repository;

import com.devmatheusmarques.medicalManagement.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByCpf(String cpf);

    Optional<Patient> findByEmail(String email);

    Patient findByName(String name);

    @Query(value = "SELECT COUNT(*) AS count, MONTH(p.created_at) AS month " +
            "FROM patients p " +
            "WHERE YEAR(p.created_at) = YEAR(CURRENT_DATE) " +
            "GROUP BY MONTH(p.created_at) " +
            "ORDER BY MONTH(p.created_at)", nativeQuery = true)
    List<Object[]> countPatientsByMonth();
}
