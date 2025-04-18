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

    @GetMapping("/api/dashboard/patients")
    public int[] getNewPatientsPerMonth() {
        return dashboardService.getNewPatientsPerMonth();
    }

    @GetMapping("/api/dashboard/doctors")
    public int[] getNewDoctorsPerMonth() {
        return dashboardService.getNewDoctorsPerMonth();
    }

    @GetMapping("/api/dashboard/consultations")
    public int[] getNewConsultationsPerMonth() {
        return dashboardService.getNewConsultationsPerMonth();
    }

//    @GetMapping("/api/dashboard/specialties")
//    public String[] getMostPopularSpecialties() {
//        return dashboardService.getMostPopularSpecialties();
//    }

    @GetMapping("/api/dashboard")
    public DashboardData getDashboardData() {
        return dashboardService.getDashboardData();
    }
}
