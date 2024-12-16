package com.devmatheusmarques.medicalManagement.repository;

import com.devmatheusmarques.medicalManagement.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
