package com.devmatheusmarques.medicalManagement.controller;

import com.devmatheusmarques.medicalManagement.dto.*;
import com.devmatheusmarques.medicalManagement.model.Consultation;
import com.devmatheusmarques.medicalManagement.model.Patient;
import com.devmatheusmarques.medicalManagement.service.ConsultationService;
import com.devmatheusmarques.medicalManagement.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/patients")
    public List<PatientConsultationReportDTO> getPatients(@RequestBody ReportFilterDTO filter) {
        return reportService.getConsultationsByPatient(filter);
    }

    @PostMapping("/doctors")
    public List<DoctorReportDTO> getDoctors(@RequestBody ReportFilterDTO filter) {
        return reportService.getDoctorsReport(filter);
    }

    @PostMapping("/consultations")
    public List<ConsultationReportDTO> getConsultations(@RequestBody ReportFilterDTO filter) {
        return reportService.getConsultations(filter);
    }

    @PostMapping("/specialty-consultations")
    public List<SpecialtyConsultationReportDTO> getBySpecialty(@RequestBody ReportFilterDTO filter) {
        return reportService.getConsultationsBySpecialty(filter);
    }

    @PostMapping("/patient-consultations")
    public List<PatientConsultationReportDTO> getByPatient(@RequestBody ReportFilterDTO filter) {
        return reportService.getConsultationsByPatient(filter);
    }

    @PostMapping("/doctor-consultations")
    public List<DoctorReportDTO> getByDoctor(@RequestBody ReportFilterDTO filter) {
        return reportService.getDoctorsReport(filter);
    }
}

