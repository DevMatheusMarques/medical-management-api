package com.devmatheusmarques.medicalManagement.service;

import com.devmatheusmarques.medicalManagement.dto.ConsultationRequestDTO;
import com.devmatheusmarques.medicalManagement.dto.ConsultationResponseDTO;
import com.devmatheusmarques.medicalManagement.dto.DoctorResponseDTO;
import com.devmatheusmarques.medicalManagement.model.Consultation;
import com.devmatheusmarques.medicalManagement.model.Doctor;
import com.devmatheusmarques.medicalManagement.model.Patient;
import com.devmatheusmarques.medicalManagement.repository.ConsultationRepository;
import com.devmatheusmarques.medicalManagement.repository.DoctorRepository;
import com.devmatheusmarques.medicalManagement.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ConsultationResponseDTO consultationRegister(ConsultationRequestDTO consultationRequestDTO) {
        try {
            Patient patient = patientRepository.findByName(consultationRequestDTO.getPatient());
            if (patient == null) {
                throw new IllegalArgumentException("Paciente não encontrado.");
            }

            Doctor doctor = doctorRepository.findByName(consultationRequestDTO.getDoctor());
            if (doctor == null) {
                throw new IllegalArgumentException("Médico não encontrado.");
            }

            Consultation consultation = modelMapper.map(consultationRequestDTO, Consultation.class);
            consultation.setPatient(patient);
            consultation.setDoctor(doctor);

            consultationRepository.save(consultation);

            return getConsultationResponseDTO(consultation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static ConsultationResponseDTO getConsultationResponseDTO(Consultation consultation) {
        ConsultationResponseDTO responseDTO = new ConsultationResponseDTO();
        responseDTO.setId(consultation.getId());
        responseDTO.setPatient(consultation.getPatient().getName());
        responseDTO.setDoctor(consultation.getDoctor().getName());
        responseDTO.setDate(consultation.getDate());
        responseDTO.setTime(consultation.getTime());
        responseDTO.setStatus(consultation.getStatus());
        responseDTO.setObservations(consultation.getObservations());
        return responseDTO;
    }

    public void consultationEdit(Long id, ConsultationRequestDTO consultationRequestDTO) {
        Consultation existingConsultation = consultationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada."));

        if (consultationRequestDTO.getDate() != null) {
            existingConsultation.setDate(consultationRequestDTO.getDate());
        }
        if (consultationRequestDTO.getTime() != null) {
            existingConsultation.setTime(consultationRequestDTO.getTime());
        }
        if (consultationRequestDTO.getStatus() != null) {
            existingConsultation.setStatus(consultationRequestDTO.getStatus());
        }
        if (consultationRequestDTO.getObservations() != null) {
            existingConsultation.setObservations(consultationRequestDTO.getObservations());
        }

        if (consultationRequestDTO.getPatient() != null) {
            Patient patient = patientRepository.findByName(consultationRequestDTO.getPatient());
            if (patient == null) {
                throw new IllegalArgumentException("Paciente não encontrado.");
            }
            existingConsultation.setPatient(patient);
        }

        if (consultationRequestDTO.getDoctor() != null) {
            Doctor doctor = doctorRepository.findByName(consultationRequestDTO.getDoctor());
            if (doctor == null) {
                throw new IllegalArgumentException("Médico não encontrado.");
            }
            existingConsultation.setDoctor(doctor);
        }

        consultationRepository.save(existingConsultation);
    }

    public void consultationDelete(Long id) {
        try {
            Consultation existingConsultation = consultationRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));

            consultationRepository.delete(existingConsultation);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir paciente: " + e.getMessage(), e);
        }
    }

    public ConsultationResponseDTO findById(Long id) {
        try {
            Consultation consultation = consultationRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));

            return getConsultationResponseDTO(consultation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ConsultationResponseDTO> findAll() {
        try {
            List<Consultation> consultations = consultationRepository.findAll();

            return consultations.stream()
                    .map(ConsultationService::getConsultationResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
