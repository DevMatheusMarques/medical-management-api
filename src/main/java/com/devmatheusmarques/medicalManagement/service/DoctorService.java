package com.devmatheusmarques.medicalManagement.service;

import com.devmatheusmarques.medicalManagement.dto.DoctorEditDTO;
import com.devmatheusmarques.medicalManagement.dto.DoctorRequestDTO;
import com.devmatheusmarques.medicalManagement.dto.DoctorResponseDTO;
import com.devmatheusmarques.medicalManagement.model.Doctor;
import com.devmatheusmarques.medicalManagement.repository.DoctorRepository;
import com.devmatheusmarques.medicalManagement.util.CrmValidator;
import com.devmatheusmarques.medicalManagement.util.EmailValidator;
import com.devmatheusmarques.medicalManagement.util.PhoneValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ModelMapper modelMapper;

    public DoctorResponseDTO doctorRegister(DoctorRequestDTO doctorRequestDTO) {

        Doctor doctor = modelMapper.map(doctorRequestDTO, Doctor.class);

        if (!CrmValidator.isValid(doctor.getCrm())) {
            throw new IllegalArgumentException("CRM inválido.");
        }

        if (!EmailValidator.isValid(doctor.getEmail())) {
            throw new IllegalArgumentException("Email inválido.");
        }

        if (!PhoneValidator.isValid(doctor.getTelephone())) {
            throw new IllegalArgumentException("Telefone inválido.");
        }

        if (doctorRepository.findByCrm(doctor.getCrm()).isPresent()) {
            throw new IllegalArgumentException("Já existe um médico cadastrado com este CRM.");
        }

        if (doctorRepository.findByEmail(doctor.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Já existe um médico cadastrado com este e-mail.");
        }

        doctor.setCreated_at(LocalDateTime.now());

        Doctor savedDoctor = doctorRepository.save(doctor);
        return modelMapper.map(savedDoctor, DoctorResponseDTO.class);
    }

    public void doctorEdit(Long id, DoctorEditDTO doctorEditDTO) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado."));

        if (!EmailValidator.isValid(doctorEditDTO.getEmail())) {
            throw new IllegalArgumentException("Email inválido");
        }

        if (!PhoneValidator.isValid(doctorEditDTO.getTelephone())) {
            throw new IllegalArgumentException("Telefone inválido");
        }

        if (doctorEditDTO.getName() != null) {
            existingDoctor.setName(doctorEditDTO.getName());
        }
        if (doctorEditDTO.getSpecialty() != null) {
            existingDoctor.setSpecialty(doctorEditDTO.getSpecialty());
        }
        if (doctorEditDTO.getEmail() != null) {
            existingDoctor.setEmail(doctorEditDTO.getEmail());
        }
        if (doctorEditDTO.getTelephone() != null) {
            existingDoctor.setTelephone(doctorEditDTO.getTelephone());
        }

        existingDoctor.setUpdated_at(LocalDateTime.now());

        doctorRepository.save(existingDoctor);
    }

    public void doctorDelete(Long id) {
        try {
            Doctor existingDoctor = doctorRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));

            doctorRepository.delete(existingDoctor);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir paciente: " + e.getMessage(), e);
        }
    }

    public DoctorResponseDTO findByCrm(String crm) {
        try {
            if (crm == null || crm.isBlank()) {
                throw new IllegalArgumentException("CRM inválido ou ausente.");
            }
            Doctor doctor = doctorRepository.findByCrm(crm)
                    .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));

            return modelMapper.map(doctor, DoctorResponseDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<DoctorResponseDTO> findAll() {
        try {
            List<Doctor> doctors = doctorRepository.findAll();

            return doctors.stream()
                    .map(doctor -> modelMapper.map(doctor, DoctorResponseDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
