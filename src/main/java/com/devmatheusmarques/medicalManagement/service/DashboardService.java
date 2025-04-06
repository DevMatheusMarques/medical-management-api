package com.devmatheusmarques.medicalManagement.service;

import com.devmatheusmarques.medicalManagement.model.DashboardData;
import com.devmatheusmarques.medicalManagement.repository.ConsultationRepository;
import com.devmatheusmarques.medicalManagement.repository.DoctorRepository;
import com.devmatheusmarques.medicalManagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ConsultationRepository consultationRepository;

    // Método para obter os dados de dashboard
    public DashboardData getDashboardData() {
        DashboardData data = new DashboardData();

        // Obtendo o número de pacientes por mês
        data.setPatients(getNewPatientsPerMonth());

        // Obtendo o número de médicos por mês
        data.setDoctors(getNewDoctorsPerMonth());

        // Obtendo o número de consultas por mês
        data.setConsultations(getNewConsultationsPerMonth());

        // Definir os meses do ano (pode ser fixo ou calculado)
        data.setMonths(new String[]{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"});

        data.setSpecialities(countConsultationsBySpecialtyNative());

        return data;
    }

    // Métodos para obter os novos pacientes, médicos e consultas por mês
    public int[] getNewPatientsPerMonth() {
        List<Object[]> results = patientRepository.countPatientsByMonth();
        int[] patientsPerMonth = new int[12];

        for (Object[] result : results) {
            int month = (Integer) result[1];
            int count = ((Long) result[0]).intValue();
            patientsPerMonth[month - 1] = count;
        }

        return patientsPerMonth;
    }

    public int[] getNewDoctorsPerMonth() {
        List<Object[]> results = doctorRepository.countDoctorsByMonth();
        int[] doctorsPerMonth = new int[12];

        for (Object[] result : results) {
            int month = (Integer) result[1];
            int count = ((Long) result[0]).intValue();
            doctorsPerMonth[month - 1] = count;
        }

        return doctorsPerMonth;
    }

    public int[] getNewConsultationsPerMonth() {
        List<Object[]> results = consultationRepository.countConsultationsByMonth();
        int[] consultationsPerMonth = new int[12];

        for (Object[] result : results) {
            int month = (Integer) result[1];
            int count = ((Long) result[0]).intValue();
            consultationsPerMonth[month - 1] = count;
        }

        return consultationsPerMonth;
    }

    public List<Map<String, Object>> countConsultationsBySpecialtyNative() {
        List<Object[]> results = consultationRepository.countConsultationsBySpecialtyNative();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", result[0]);
            map.put("count", result[1]);
            response.add(map);
        }
        return response;
    }

}

