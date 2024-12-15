package com.devmatheusmarques.medicalManagement.controller;

import com.devmatheusmarques.medicalManagement.dto.PatientEditDTO;
import com.devmatheusmarques.medicalManagement.dto.PatientRequestDTO;
import com.devmatheusmarques.medicalManagement.dto.PatientResponseDTO;
import com.devmatheusmarques.medicalManagement.model.Patient;
import com.devmatheusmarques.medicalManagement.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO response = patientService.patientRegister(patientRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> editPatient(@PathVariable Long id, @Valid @RequestBody PatientEditDTO patientEditDTO) {
        patientService.patientEdit(id, patientEditDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.patientDelete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<PatientResponseDTO> getPatientByCpf(@PathVariable String cpf) {
        PatientResponseDTO response = patientService.findByCpf(cpf);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> response = patientService.findAll();
        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
}

