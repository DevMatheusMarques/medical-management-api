package com.devmatheusmarques.medicalManagement.service;

import com.devmatheusmarques.medicalManagement.dto.*;
import com.devmatheusmarques.medicalManagement.projection.PatientConsultationProjection;
import com.devmatheusmarques.medicalManagement.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ConsultationRepository consultationRepository;

    public List<PatientConsultationReportDTO> getConsultationsByPatient(ReportFilterDTO filter) {
        List<PatientConsultationProjection> projections =
                consultationRepository.findConsultationsByPatient(filter.getStartDate(), filter.getEndDate(), filter.getStatus());

        return projections.stream()
                .map(p -> new PatientConsultationReportDTO(
                        p.getPatientId(),
                        p.getPatientName(),
                        p.getPatientEmail(),
                        p.getPatientTelephone(),
                        p.getPatientStatus(),
                        p.getConsultationCount(),
                        p.getLastConsultation()
                ))
                .collect(Collectors.toList());
    }

    public List<DoctorReportDTO> getDoctorsReport(ReportFilterDTO filter) {
        return consultationRepository
                .findConsultationsByDoctor(filter.getStartDate(), filter.getEndDate(), filter.getStatus(), filter.getSpecialty())
                .stream()
                .map(proj -> new DoctorReportDTO(
                        proj.getDoctorId(),
                        proj.getDoctorName(),
                        proj.getSpecialtyName(),
                        proj.getDoctorEmail(),
                        proj.getDoctorTelephone(),
                        proj.getTotalConsultations()))
                .toList();
    }

    public List<ConsultationReportDTO> getConsultations(ReportFilterDTO filter) {
        return consultationRepository
                .findConsultations(filter.getStartDate(), filter.getEndDate(), filter.getStatus(), filter.getSpecialty())
                .stream()
                .map(proj -> new ConsultationReportDTO(
                        proj.getConsultationId(),
                        proj.getPatientName(),
                        proj.getDoctorName(),
                        proj.getDoctorSpecialty(),
                        proj.getDate(),
                        proj.getStatus()))
                .toList();
    }

    public List<SpecialtyConsultationReportDTO> getConsultationsBySpecialty(ReportFilterDTO filter) {
        return consultationRepository
                .findConsultationsBySpecialty(filter.getStartDate(), filter.getEndDate(), filter.getStatus())
                .stream()
                .map(proj -> new SpecialtyConsultationReportDTO(
                        proj.getSpecialtyName(),
                        proj.getTotalConsultations(),
                        proj.getTotalDoctors()))
                .toList();
    }
}
