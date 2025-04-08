package com.devmatheusmarques.medicalManagement.controller;

import com.devmatheusmarques.medicalManagement.model.Consultation;
import com.devmatheusmarques.medicalManagement.model.Patient;
import com.devmatheusmarques.medicalManagement.service.ConsultationService;
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
    private ConsultationService consultationService;

    @GetMapping("/consultatations")
    public ResponseEntity<List<Consultation>> getConsultationsPerPeriod(
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        List<Consultation> consultations = consultationService.getConsultationsPerPeriod(start, end);
        return ResponseEntity.ok(consultations);
    }

    @GetMapping("/patients/{idDoctor}")
    public ResponseEntity<List<Patient>> getPacientesAtendidosPorMedico(@PathVariable Long idDoctor) {
        List<Patient> patients = consultationService.getPatientsPerDoctor(idDoctor);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/consultations/csv")
    public ResponseEntity<String> getConsultationsCSV(
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        List<Consultation> consultations = consultationService.getConsultationsPerPeriod(start, end);
        String csvReport = consultationService.generateReportQueriesCSV(consultations);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=consultations.csv")
                .contentType(MediaType.TEXT_PLAIN)
                .body(csvReport);
    }

//    @GetMapping("/consultations/pdf")
//    public ResponseEntity<byte[]> getConsultationsPDF(
//            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
//            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
//
//        List<Consultation> consultations = consultationService.getConsultationsPerPeriod(start, end);
//        byte[] pdfBytes = consultationService.generateReportConsultationsPDF(consultations);
//
//        if (pdfBytes == null) {
//            return ResponseEntity.internalServerError().build();
//        }
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio_consultas.pdf")
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(pdfBytes);
//    }
}
