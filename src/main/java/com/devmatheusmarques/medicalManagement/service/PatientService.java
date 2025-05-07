package com.devmatheusmarques.medicalManagement.service;

import com.devmatheusmarques.medicalManagement.dto.AddressRequestDTO;
import com.devmatheusmarques.medicalManagement.dto.PatientEditDTO;
import com.devmatheusmarques.medicalManagement.dto.PatientRequestDTO;
import com.devmatheusmarques.medicalManagement.dto.PatientResponseDTO;
import com.devmatheusmarques.medicalManagement.model.Patient;
import com.devmatheusmarques.medicalManagement.model.Address;
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

    public void patientRegister(PatientRequestDTO patientRequestDTO) {

        Patient patient = convertToEntity(patientRequestDTO);

        if (!CpfValidator.isValid(patient.getCpf())) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        if (!EmailValidator.isValid(patient.getEmail())) {
            throw new IllegalArgumentException("Email inválido.");
        }

        if (!PhoneValidator.isValid(patient.getTelephone())) {
            throw new IllegalArgumentException("Telefone inválido.");
        }

        System.out.println("CPF recebido: " + patient.getCpf());

        if (patientRepository.findByCpf(patient.getCpf()).isPresent()) {
            throw new IllegalArgumentException("Já existe um paciente cadastrado com este CPF.");
        }

        if (patientRepository.findByEmail(patient.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Já existe um paciente cadastrado com este e-mail.");
        }

        patient.setCreated_at(LocalDateTime.now());
        patientRepository.save(patient);
    }

    public Patient convertToEntity(PatientRequestDTO patientRequestDTO) {
        Address address = new Address(
                patientRequestDTO.getAddress().getCep(),
                patientRequestDTO.getAddress().getLogradouro(),
                patientRequestDTO.getAddress().getComplemento(),
                patientRequestDTO.getAddress().getBairro(),
                patientRequestDTO.getAddress().getLocalidade(),
                patientRequestDTO.getAddress().getUf()
        );

        Patient patient = modelMapper.map(patientRequestDTO, Patient.class);
        patient.setAddress(address);

        return patient;
    }

    public Patient convertToEntity(PatientEditDTO patientEditDTO) {
        Patient patient = modelMapper.map(patientEditDTO, Patient.class);

        if (patientEditDTO.getAddress() != null) {
            Address address = convertToAddressEntity(patientEditDTO.getAddress());
            patient.setAddress(address);
        }

        return patient;
    }

    private Address convertToAddressEntity(AddressRequestDTO dto) {
        return new Address(
                dto.getCep(),
                dto.getLogradouro(),
                dto.getComplemento(),
                dto.getBairro(),
                dto.getLocalidade(),
                dto.getUf()
        );
    }


    private void updateAddress(Address existingAddress, Address newAddress) {
        if (newAddress.getZipCode() != null) existingAddress.setZipCode(newAddress.getZipCode());
        if (newAddress.getStreet() != null) existingAddress.setStreet(newAddress.getStreet());
        if (newAddress.getComplement() != null) existingAddress.setComplement(newAddress.getComplement());
        if (newAddress.getNeighborhood() != null) existingAddress.setNeighborhood(newAddress.getNeighborhood());
        if (newAddress.getCity() != null) existingAddress.setCity(newAddress.getCity());
        if (newAddress.getState() != null) existingAddress.setState(newAddress.getState());
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
            Address newAddress = convertToAddressEntity(patientEditDTO.getAddress());

            if (existingPatient.getAddress() == null) {
                existingPatient.setAddress(newAddress);
            } else {
                updateAddress(existingPatient.getAddress(), newAddress);
            }
        }

        if (patientEditDTO.getAddress_number() != null) {
            existingPatient.setAddress_number(patientEditDTO.getAddress_number());
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
            throw new RuntimeException("Erro ao excluir paciente");
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
