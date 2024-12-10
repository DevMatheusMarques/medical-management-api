package com.devmatheusmarques.medicalManagement.dto;

import com.devmatheusmarques.medicalManagement.model.Doctor;
import com.devmatheusmarques.medicalManagement.model.Patient;
import com.devmatheusmarques.medicalManagement.util.ConsultationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationDTO {

    private Patient patient;
    private Doctor doctor;
    private Date dateTime;
    private ConsultationStatus status;
    private String observations;
}
