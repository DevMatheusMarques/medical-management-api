package com.devmatheusmarques.medicalManagement.repository;

import com.devmatheusmarques.medicalManagement.model.Doctor;
import com.devmatheusmarques.medicalManagement.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByCrm(String crm);

    Optional<Doctor> findByEmail(String email);

    Doctor findByName(String name);

    //Modelo MySQL

//    @Query(value = "SELECT COUNT(*) AS count, MONTH(d.created_at) AS month " +
//            "FROM doctors d " +
//            "WHERE YEAR(d.created_at) = YEAR(CURRENT_DATE) " +
//            "GROUP BY MONTH(d.created_at) " +
//            "ORDER BY MONTH(d.created_at)", nativeQuery = true)
//    List<Object[]> countDoctorsByMonth();

    @Query(value = "SELECT COUNT(*) AS count, EXTRACT(MONTH FROM d.created_at) AS month " +
            "FROM doctors d " +
            "WHERE EXTRACT(YEAR FROM d.created_at) = EXTRACT(YEAR FROM CURRENT_DATE) " +
            "GROUP BY EXTRACT(MONTH FROM d.created_at) " +
            "ORDER BY month", nativeQuery = true)
    List<Object[]> countDoctorsByMonth();

}
