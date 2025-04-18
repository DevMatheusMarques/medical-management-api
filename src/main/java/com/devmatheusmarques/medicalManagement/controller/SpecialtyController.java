package com.devmatheusmarques.medicalManagement.controller;

import com.devmatheusmarques.medicalManagement.dto.SpecialtyRequestDTO;
import com.devmatheusmarques.medicalManagement.dto.SpecialtyResponseDTO;
import com.devmatheusmarques.medicalManagement.service.SpecialtyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialties")
public class SpecialtyController {

    @Autowired
    private SpecialtyService specialtyService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpecialtyResponseDTO> createSpecialty(@Valid @RequestBody SpecialtyRequestDTO specialtyRequestDTO) {
        specialtyService.specialtyRegister(specialtyRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> editSpecialty(@PathVariable Long id, @Valid @RequestBody SpecialtyRequestDTO specialtyRequestDTO) {
        specialtyService.specialtyEdit(id, specialtyRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialty(@PathVariable Long id) {
        specialtyService.specialtyDelete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SpecialtyResponseDTO>> getAllSpecialtys() {
        List<SpecialtyResponseDTO> response = specialtyService.findAll();
        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
}
