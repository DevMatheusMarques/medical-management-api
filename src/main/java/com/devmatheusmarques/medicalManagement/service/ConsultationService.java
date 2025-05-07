package com.devmatheusmarques.medicalManagement.service;

import com.devmatheusmarques.medicalManagement.dto.ConsultationEditDTO;
import com.devmatheusmarques.medicalManagement.dto.ConsultationRequestDTO;
import com.devmatheusmarques.medicalManagement.dto.ConsultationResponseDTO;
import com.devmatheusmarques.medicalManagement.model.Consultation;
import com.devmatheusmarques.medicalManagement.model.Doctor;
import com.devmatheusmarques.medicalManagement.model.Patient;
import com.devmatheusmarques.medicalManagement.repository.ConsultationRepository;
import com.devmatheusmarques.medicalManagement.repository.DoctorRepository;
import com.devmatheusmarques.medicalManagement.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;
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

    public void consultationRegister(ConsultationRequestDTO consultationRequestDTO) {
        Optional<Patient> patient = patientRepository.findById(consultationRequestDTO.getPatient().getId());
        if (patient.isEmpty()) {
            throw new IllegalArgumentException("Paciente não encontrado.");
        }

        Optional<Doctor> doctor = doctorRepository.findById(consultationRequestDTO.getDoctor().getId());
        if (doctor.isEmpty()) {
            throw new IllegalArgumentException("Médico não encontrado.");
        }

        LocalDateTime consultationDateTime = LocalDateTime.of(
                consultationRequestDTO.getDate(),
                consultationRequestDTO.getTime()
        );

        if (consultationDateTime.isBefore(LocalDateTime.now())) throw new IllegalArgumentException("Não é possível agendar uma consulta no passado.");

        boolean existsDoctorConsultation = consultationRepository.findByDoctorAndDateTime(
                consultationRequestDTO.getDoctor().getId(),
                consultationRequestDTO.getDate(),
                consultationRequestDTO.getTime()
        ).isPresent();

        if (existsDoctorConsultation) throw new IllegalArgumentException("O médico já possui uma consulta marcada nesse dia e horário.");

        boolean existsPatientConsultation = consultationRepository.findByPatientAndDateTime(
                consultationRequestDTO.getPatient().getId(),
                consultationRequestDTO.getDate(),
                consultationRequestDTO.getTime()
        ).isPresent();

        if (existsPatientConsultation) throw new IllegalArgumentException("O paciente já possui uma consulta marcada nesse dia e horário.");

        Consultation consultation = modelMapper.map(consultationRequestDTO, Consultation.class);
        consultation.setCreated_at(LocalDateTime.now());

        consultationRepository.save(consultation);
    }

    private static ConsultationResponseDTO getConsultationResponseDTO(Consultation consultation) {
        ConsultationResponseDTO responseDTO = new ConsultationResponseDTO();
        responseDTO.setId(consultation.getId());
        responseDTO.setPatient(consultation.getPatient());
        responseDTO.setDoctor(consultation.getDoctor());
        responseDTO.setDate(consultation.getDate());
        responseDTO.setTime(consultation.getTime());
        responseDTO.setStatus(consultation.getStatus());
        responseDTO.setObservations(consultation.getObservations());
        responseDTO.setCreated_at(consultation.getCreated_at());
        return responseDTO;
    }

    public void consultationEdit(Long id, ConsultationEditDTO consultationEditDTO) {
        Consultation existingConsultation = consultationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada."));

        Long doctorId = consultationEditDTO.getDoctor() != null ? consultationEditDTO.getDoctor().getId() : existingConsultation.getDoctor().getId();
        Long patientId = consultationEditDTO.getPatient() != null ? consultationEditDTO.getPatient().getId() : existingConsultation.getPatient().getId();
        LocalDate date = consultationEditDTO.getDate() != null ? consultationEditDTO.getDate() : existingConsultation.getDate();
        LocalTime time = consultationEditDTO.getTime() != null ? consultationEditDTO.getTime() : existingConsultation.getTime();

        LocalDateTime consultationDateTime = LocalDateTime.of(date, time);

        if (consultationDateTime.isBefore(LocalDateTime.now())) throw new IllegalArgumentException("Não é possível agendar uma consulta no passado.");

        Optional<Consultation> conflictDoctor = consultationRepository.findByDoctorAndDateTime(doctorId, date, time);
        if (conflictDoctor.isPresent() && !conflictDoctor.get().getId().equals(id)) {
            throw new IllegalArgumentException("O médico já possui uma consulta nesse dia e horário.");
        }

        Optional<Consultation> conflictPatient = consultationRepository.findByPatientAndDateTime(patientId, date, time);
        if (conflictPatient.isPresent() && !conflictPatient.get().getId().equals(id)) {
            throw new IllegalArgumentException("O paciente já possui uma consulta nesse dia e horário.");
        }

        if (consultationEditDTO.getDate() != null) existingConsultation.setDate(date);
        if (consultationEditDTO.getTime() != null) existingConsultation.setTime(time);
        if (consultationEditDTO.getStatus() != null) existingConsultation.setStatus(consultationEditDTO.getStatus());
        if (consultationEditDTO.getObservations() != null) existingConsultation.setObservations(consultationEditDTO.getObservations());

        if (consultationEditDTO.getPatient() != null) {
            Optional<Patient> patient = patientRepository.findById(consultationEditDTO.getPatient().getId());
            if (patient.isEmpty()) throw new IllegalArgumentException("Paciente não encontrado.");
            existingConsultation.setPatient(patient.get());
        }

        if (consultationEditDTO.getDoctor() != null) {
            Optional<Doctor> doctor = doctorRepository.findById(consultationEditDTO.getDoctor().getId());
            if (doctor.isEmpty()) throw new IllegalArgumentException("Médico não encontrado.");
            existingConsultation.setDoctor(doctor.get());
        }

        existingConsultation.setUpdated_at(LocalDateTime.now());
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
            throw new RuntimeException("Erro ao excluir consulta");
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
