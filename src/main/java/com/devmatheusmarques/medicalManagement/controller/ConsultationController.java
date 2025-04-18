package com.devmatheusmarques.medicalManagement.controller;

import com.devmatheusmarques.medicalManagement.dto.ConsultationRequestDTO;
import com.devmatheusmarques.medicalManagement.dto.ConsultationEditDTO;
import com.devmatheusmarques.medicalManagement.dto.ConsultationResponseDTO;
import com.devmatheusmarques.medicalManagement.service.ConsultationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConsultationResponseDTO> createConsultation(@Valid @RequestBody ConsultationRequestDTO consultationRequestDTO) {
        consultationService.consultationRegister(consultationRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> editConsultation(@PathVariable Long id, @Valid @RequestBody ConsultationEditDTO consultationEditDTO) {
        consultationService.consultationEdit(id, consultationEditDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        consultationService.consultationDelete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationResponseDTO> getConsultationByCpf(@PathVariable Long id) {
        ConsultationResponseDTO response = consultationService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ConsultationResponseDTO>> getAllConsultations() {
        List<ConsultationResponseDTO> response = consultationService.findAll();
        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
}
