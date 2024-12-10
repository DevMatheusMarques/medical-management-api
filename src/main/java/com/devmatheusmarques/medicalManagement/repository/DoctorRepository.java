package com.devmatheusmarques.medicalManagement.repository;

import com.devmatheusmarques.medicalManagement.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
