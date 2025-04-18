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

    public DashboardData getDashboardData() {
        DashboardData data = new DashboardData();

        data.setPatients(getNewPatientsPerMonth());
        data.setDoctors(getNewDoctorsPerMonth());
        data.setConsultations(getNewConsultationsPerMonth());

        data.setMonths(new String[]{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"});

        data.setSpecialities(countConsultationsBySpecialtyNative());

        return data;
    }

    public int[] getNewPatientsPerMonth() {
        List<Object[]> results = patientRepository.countPatientsByMonth();
        return getInts(results);
    }

    private int[] getInts(List<Object[]> results) {
        int[] patientsPerMonth = new int[12];

        for (Object[] result : results) {
            int month = ((Number) result[1]).intValue();
            int count = ((Number) result[0]).intValue();
            patientsPerMonth[month - 1] = count;
        }

        return patientsPerMonth;
    }

    public int[] getNewDoctorsPerMonth() {
        List<Object[]> results = doctorRepository.countDoctorsByMonth();
        return getInts(results);
    }

    public int[] getNewConsultationsPerMonth() {
        List<Object[]> results = consultationRepository.countConsultationsByMonth();
        return getInts(results);
    }

    public List<Map<String, Object>> countConsultationsBySpecialtyNative() {
        List<Object[]> results = consultationRepository.countConsultationsBySpecialtyNative();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", result[0]);
            map.put("count", ((Number) result[1]).intValue());
            response.add(map);
        }

        return response;
    }
}

