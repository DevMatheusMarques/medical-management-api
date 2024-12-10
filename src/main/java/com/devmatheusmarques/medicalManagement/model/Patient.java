package com.devmatheusmarques.medicalManagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "cpf", unique = true, nullable = false)
    String cpf;
    @Column(name = "birth_date", nullable = false)
    Date birth_date;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "telephone", nullable = false)
    String telephone;
    @Column(name = "address", nullable = false)
    String address;
}
