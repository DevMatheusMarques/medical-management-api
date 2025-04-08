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

    public ConsultationResponseDTO consultationRegister(ConsultationRequestDTO consultationRequestDTO) {
        Optional<Patient> patient = patientRepository.findById(consultationRequestDTO.getPatient().getId());
        if (patient.isEmpty()) {
            throw new IllegalArgumentException("Paciente não encontrado.");
        }

        Optional<Doctor> doctor = doctorRepository.findById(consultationRequestDTO.getDoctor().getId());
        if (doctor.isEmpty()) {
            throw new IllegalArgumentException("Médico não encontrado.");
        }

        Consultation consultation = modelMapper.map(consultationRequestDTO, Consultation.class);
        consultation.setCreated_at(LocalDateTime.now());

        consultationRepository.save(consultation);

        return getConsultationResponseDTO(consultation);

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

        if (consultationEditDTO.getDate() != null) {
            existingConsultation.setDate(consultationEditDTO.getDate());
        }
        if (consultationEditDTO.getTime() != null) {
            existingConsultation.setTime(consultationEditDTO.getTime());
        }
        if (consultationEditDTO.getStatus() != null) {
            existingConsultation.setStatus(consultationEditDTO.getStatus());
        }
        if (consultationEditDTO.getObservations() != null) {
            existingConsultation.setObservations(consultationEditDTO.getObservations());
        }

        if (consultationEditDTO.getPatient() != null) {
            Optional<Patient> patient = patientRepository.findById(consultationEditDTO.getPatient().getId());
            if (patient.isEmpty()) {
                throw new IllegalArgumentException("Paciente não encontrado.");
            }
            existingConsultation.setPatient(patient.orElse(null));
        }

        if (consultationEditDTO.getDoctor() != null) {
            Optional<Doctor> doctor = doctorRepository.findById(consultationEditDTO.getDoctor().getId());
            if (doctor.isEmpty()) {
                throw new IllegalArgumentException("Médico não encontrado.");
            }
            existingConsultation.setDoctor(doctor.orElse(null));
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

    public List<Consultation> getConsultationsPerPeriod(LocalDate start, LocalDate end) {
        try {
            LocalTime startTime = LocalTime.MIN;
            LocalTime endTime = LocalTime.MAX;

            return consultationRepository.findByDateAndTimeBetween(
                    java.sql.Date.valueOf(start),

                    startTime,
                    endTime
            );
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao buscar consultas no período.", e);
        }
    }

    public List<Patient> getPatientsPerDoctor(Long idDoctor) {
        List<Consultation> consultations = consultationRepository.findByDoctorId(idDoctor);
        return consultations.stream()
                .map(Consultation::getPatient)
                .distinct()
                .collect(Collectors.toList());
    }

    public String generateReportQueriesCSV(List<Consultation> consultations) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID, Paciente, Médico, Data, Status, Observações\n");

        for (Consultation consulta : consultations) {
            sb.append(consulta.getId()).append(", ");
            sb.append(consulta.getPatient().getName()).append(", ");
            sb.append(consulta.getDoctor().getName()).append(", ");
            sb.append(consulta.getDate()).append(", ");
            sb.append(consulta.getTime()).append(", ");
            sb.append(consulta.getStatus()).append(", ");
            sb.append(consulta.getObservations()).append("\n");
        }
        return sb.toString();
    }

//    public byte[] generateReportConsultationsPDF(List<Consultation> consultations) {
//        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
//            PdfWriter writer = new PdfWriter(outputStream);
//            PdfDocument pdf = new PdfDocument(writer);
//            Document document = new Document(pdf);
//
//            // Adiciona um título ao documento
//            document.add(new Paragraph("Relatório de Consultas").setBold().setFontSize(16));
//
//            // Criar tabela com cabeçalhos
//            float[] columnWidths = {50f, 150f, 150f, 100f, 100f, 200f};
//            Table table = new Table(columnWidths);
//            table.addHeaderCell(new Cell().add(new Paragraph("ID")));
//            table.addHeaderCell(new Cell().add(new Paragraph("Paciente")));
//            table.addHeaderCell(new Cell().add(new Paragraph("Médico")));
//            table.addHeaderCell(new Cell().add(new Paragraph("Data")));
//            table.addHeaderCell(new Cell().add(new Paragraph("Horário")));
//            table.addHeaderCell(new Cell().add(new Paragraph("Status")));
//            table.addHeaderCell(new Cell().add(new Paragraph("Observações")));
//
//            // Preenchendo a tabela com os dados das consultations
//            for (Consultation consultation : consultations) {
//                table.addCell(new Cell().add(new Paragraph(String.valueOf(consultation.getId()))));
//                table.addCell(new Cell().add(new Paragraph(consultation.getPatient().getName())));
//                table.addCell(new Cell().add(new Paragraph(consultation.getDoctor().getName())));
//                table.addCell(new Cell().add(new Paragraph(consultation.getDate().toString())));
//                table.addCell(new Cell().add(new Paragraph(consultation.getTime().toString())));
//                table.addCell(new Cell().add(new Paragraph(String.valueOf(consultation.getStatus()))));
//                table.addCell(new Cell().add(new Paragraph(consultation.getObservations() != null ? consultation.getObservations() : "-")));
//            }
//
//            // Adicionando a tabela ao documento
//            document.add(table);
//            document.close();
//
//            return outputStream.toByteArray(); // Retorna o PDF como um array de bytes
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
