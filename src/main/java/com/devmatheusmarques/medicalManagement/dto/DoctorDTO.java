package com.devmatheusmarques.medicalManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    private String name;
    private String crm;
    private String specialty;
    private String email;
    private String telephone;
}
