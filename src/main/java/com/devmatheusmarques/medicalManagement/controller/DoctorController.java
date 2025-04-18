package com.devmatheusmarques.medicalManagement.controller;

import com.devmatheusmarques.medicalManagement.dto.DoctorEditDTO;
import com.devmatheusmarques.medicalManagement.dto.DoctorRequestDTO;
import com.devmatheusmarques.medicalManagement.dto.DoctorResponseDTO;
import com.devmatheusmarques.medicalManagement.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.devmatheusmarques.medicalManagement.exception.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDoctor(@Valid @RequestBody DoctorRequestDTO doctorRequestDTO) {
        doctorService.doctorRegister(doctorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> editDoctor(@PathVariable Long id, @Valid @RequestBody DoctorEditDTO doctorEditDTO) {
        doctorService.doctorEdit(id, doctorEditDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.doctorDelete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable Long id) {
        DoctorResponseDTO response = doctorService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/crm/{crm}")
    public ResponseEntity<DoctorResponseDTO> getDoctorByCrm(@PathVariable String crm) {
        DoctorResponseDTO response = doctorService.findByCrm(crm);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors() {
        List<DoctorResponseDTO> response = doctorService.findAll();
        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
}
