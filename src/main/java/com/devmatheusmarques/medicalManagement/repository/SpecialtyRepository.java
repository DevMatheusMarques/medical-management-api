package com.devmatheusmarques.medicalManagement.repository;

import com.devmatheusmarques.medicalManagement.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

    Optional<Specialty> findByName(String name);
}
