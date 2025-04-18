package com.devmatheusmarques.medicalManagement.service;

import com.devmatheusmarques.medicalManagement.dto.SpecialtyRequestDTO;
import com.devmatheusmarques.medicalManagement.dto.SpecialtyResponseDTO;
import com.devmatheusmarques.medicalManagement.model.Specialty;
import com.devmatheusmarques.medicalManagement.repository.SpecialtyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;
    @Autowired
    private ModelMapper modelMapper;

    public void specialtyRegister(SpecialtyRequestDTO specialtyRequestDTO) {

        Specialty specialty = modelMapper.map(specialtyRequestDTO, Specialty.class);

        if (specialtyRepository.findByName(specialty.getName()).isPresent()) {
            throw new IllegalArgumentException("Já existe uma especialidade cadastrada com este nome.");
        }

        specialty.setCreated_at(LocalDateTime.now());
        specialtyRepository.save(specialty);
    }

    public void specialtyEdit(Long id, SpecialtyRequestDTO specialtyRequestDTO) {
        Specialty existingSpecialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Especialidade não encontrada."));

        if (specialtyRequestDTO.getName() != null) {
            existingSpecialty.setName(specialtyRequestDTO.getName());
        }

        existingSpecialty.setUpdated_at(LocalDateTime.now());

        specialtyRepository.save(existingSpecialty);
    }

    public void specialtyDelete(Long id) {
        try {
            Specialty existingSpecialty = specialtyRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Especialidade não encontrada."));

            specialtyRepository.delete(existingSpecialty);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir especialidade: " + e.getMessage(), e);
        }
    }

    public List<SpecialtyResponseDTO> findAll() {
        try {
            List<Specialty> specialtys = specialtyRepository.findAll();

            return specialtys.stream()
                    .map(specialty -> modelMapper.map(specialty, SpecialtyResponseDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
