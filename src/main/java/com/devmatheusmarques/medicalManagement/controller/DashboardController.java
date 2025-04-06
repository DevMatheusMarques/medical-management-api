package com.devmatheusmarques.medicalManagement.controller;
import com.devmatheusmarques.medicalManagement.model.DashboardData;
import com.devmatheusmarques.medicalManagement.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // Endpoint para retornar os novos pacientes por mês
    @GetMapping("/api/dashboard/patients")
    public int[] getNewPatientsPerMonth() {
        return dashboardService.getNewPatientsPerMonth();
    }

    // Endpoint para retornar os novos médicos por mês
    @GetMapping("/api/dashboard/doctors")
    public int[] getNewDoctorsPerMonth() {
        return dashboardService.getNewDoctorsPerMonth();
    }

    // Endpoint para retornar as novas consultas por mês
    @GetMapping("/api/dashboard/consultations")
    public int[] getNewConsultationsPerMonth() {
        return dashboardService.getNewConsultationsPerMonth();
    }

//    @GetMapping("/api/dashboard/specialties")
//    public String[] getMostPopularSpecialties() {
//        return dashboardService.getMostPopularSpecialties();
//    }

    // Endpoint para retornar todos os dados do dashboard
    @GetMapping("/api/dashboard")
    public DashboardData getDashboardData() {
        return dashboardService.getDashboardData();
    }
}
