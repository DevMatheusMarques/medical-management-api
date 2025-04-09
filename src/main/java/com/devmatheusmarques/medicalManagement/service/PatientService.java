package com.devmatheusmarques.medicalManagement.service;

import com.devmatheusmarques.medicalManagement.dto.PatientEditDTO;
import com.devmatheusmarques.medicalManagement.dto.PatientRequestDTO;
import com.devmatheusmarques.medicalManagement.dto.PatientResponseDTO;
import com.devmatheusmarques.medicalManagement.model.Patient;
import com.devmatheusmarques.medicalManagement.repository.PatientRepository;
import com.devmatheusmarques.medicalManagement.util.CpfValidator;
import com.devmatheusmarques.medicalManagement.util.EmailValidator;
import com.devmatheusmarques.medicalManagement.util.PhoneValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ModelMapper modelMapper;

    public PatientResponseDTO patientRegister(PatientRequestDTO patientRequestDTO) {
        try {
            Patient patient = modelMapper.map(patientRequestDTO, Patient.class);

            if (!CpfValidator.isValid(patient.getCpf())) {
                throw new IllegalArgumentException("CPF inválido.");
            }

            if (!EmailValidator.isValid(patient.getEmail())) {
                throw new IllegalArgumentException("Email inválido.");
            }

            if (!PhoneValidator.isValid(patient.getTelephone())) {
                throw new IllegalArgumentException("Telefone inválido.");
            }

            if (patientRepository.findByCpf(patient.getCpf()).isPresent()) {
                throw new IllegalArgumentException("Já existe um paciente cadastrado com este CPF.");
            }

            if (patientRepository.findByEmail(patient.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Já existe um paciente cadastrado com este e-mail.");
            }

            patient.setCreated_at(LocalDateTime.now());
            Patient savedPatient = patientRepository.save(patient);
            return modelMapper.map(savedPatient, PatientResponseDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void patientEdit(Long id, PatientEditDTO patientEditDTO) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado."));

        if (!EmailValidator.isValid(patientEditDTO.getEmail())) {
            throw new IllegalArgumentException("Email inválido");
        }

        if (!PhoneValidator.isValid(patientEditDTO.getTelephone())) {
            throw new IllegalArgumentException("Telefone inválido");
        }

        if (patientEditDTO.getName() != null) {
            existingPatient.setName(patientEditDTO.getName());
        }
        if (patientEditDTO.getBirth_date() != null) {
            existingPatient.setBirth_date(patientEditDTO.getBirth_date());
        }
        if (patientEditDTO.getEmail() != null) {
            existingPatient.setEmail(patientEditDTO.getEmail());
        }
        if (patientEditDTO.getTelephone() != null) {
            existingPatient.setTelephone(patientEditDTO.getTelephone());
        }
        if (patientEditDTO.getAddress() != null) {
            existingPatient.setAddress(patientEditDTO.getAddress());
        }

        if (patientEditDTO.getStatus() != null) {
            existingPatient.setStatus(patientEditDTO.getStatus());
        }

        existingPatient.setUpdated_at(LocalDateTime.now());

        patientRepository.save(existingPatient);
    }

    public void patientDelete(Long id) {
        try {
            Patient existingPatient = patientRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));

            patientRepository.delete(existingPatient);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir paciente: " + e.getMessage(), e);
        }
    }

    public PatientResponseDTO findByCpf(String cpf) {
        try {
            if (cpf == null || cpf.isBlank()) {
                throw new IllegalArgumentException("CPF inválido ou ausente.");
            }
            Patient patient = patientRepository.findByCpf(cpf)
                    .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));

            return modelMapper.map(patient, PatientResponseDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<PatientResponseDTO> findAll() {
        try {
            List<Patient> patients = patientRepository.findAll();

            return patients.stream()
                    .map(patient -> modelMapper.map(patient, PatientResponseDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
