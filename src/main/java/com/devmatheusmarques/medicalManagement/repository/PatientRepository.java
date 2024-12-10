package com.devmatheusmarques.medicalManagement.repository;

import com.devmatheusmarques.medicalManagement.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
