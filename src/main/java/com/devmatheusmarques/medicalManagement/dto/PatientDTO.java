package com.devmatheusmarques.medicalManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private String name;
    private String cpf;
    private Date birth_date;
    private String email;
    private String telephone;
    private String address;
}
