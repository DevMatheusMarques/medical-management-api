package com.devmatheusmarques.medicalManagement.repository;

import com.devmatheusmarques.medicalManagement.model.Doctor;
import com.devmatheusmarques.medicalManagement.model.Patient;
import com.devmatheusmarques.medicalManagement.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByCrm(String crm);

    Optional<Doctor> findByEmail(String email);

    boolean existsBySpecialty(Specialty specialty);

    @Query(value = "SELECT COUNT(*) AS count, EXTRACT(MONTH FROM d.created_at) AS month " +
            "FROM doctors d " +
            "WHERE EXTRACT(YEAR FROM d.created_at) = EXTRACT(YEAR FROM CURRENT_DATE) " +
            "GROUP BY EXTRACT(MONTH FROM d.created_at) " +
            "ORDER BY month", nativeQuery = true)
    List<Object[]> countDoctorsByMonth();

}
