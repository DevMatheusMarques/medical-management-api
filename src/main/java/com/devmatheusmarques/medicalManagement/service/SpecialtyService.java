package com.devmatheusmarques.medicalManagement.service;

import com.devmatheusmarques.medicalManagement.dto.SpecialtyRequestDTO;
import com.devmatheusmarques.medicalManagement.dto.SpecialtyResponseDTO;
import com.devmatheusmarques.medicalManagement.model.Specialty;
import com.devmatheusmarques.medicalManagement.repository.DoctorRepository;
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
    @Autowired
    private DoctorRepository doctorRepository;

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

        boolean hasDoctors = doctorRepository.existsBySpecialty(existingSpecialty);
        if (hasDoctors) {
            throw new IllegalStateException("Não é possível editar a especialidade pois existem médicos vinculados a ela.");
        }

        existingSpecialty.setUpdated_at(LocalDateTime.now());

        specialtyRepository.save(existingSpecialty);
    }

    public void specialtyDelete(Long id) {
        Specialty existingSpecialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Especialidade não encontrada."));

        boolean hasDoctors = doctorRepository.existsBySpecialty(existingSpecialty);
        if (hasDoctors) {
            throw new IllegalStateException("Não é possível excluir a especialidade pois existem médicos vinculados a ela.");
        }

        specialtyRepository.delete(existingSpecialty);
    }

    public List<SpecialtyResponseDTO> findAll() {
        List<Specialty> specialtys = specialtyRepository.findAll();

        return specialtys.stream()
                .map(specialty -> modelMapper.map(specialty, SpecialtyResponseDTO.class))
                .collect(Collectors.toList());
    }
}
