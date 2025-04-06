package com.devmatheusmarques.medicalManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.devmatheusmarques.medicalManagement")
public class MedicalManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalManagementApplication.class, args);
	}

}
