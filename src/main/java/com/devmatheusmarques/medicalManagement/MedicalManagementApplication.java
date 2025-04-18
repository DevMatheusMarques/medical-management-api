package com.devmatheusmarques.medicalManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.devmatheusmarques.medicalManagement")
@EnableFeignClients(basePackages = "com.devmatheusmarques.medicalManagement.service")
public class MedicalManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalManagementApplication.class, args);
	}

}
