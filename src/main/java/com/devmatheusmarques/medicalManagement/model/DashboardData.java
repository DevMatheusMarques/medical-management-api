package com.devmatheusmarques.medicalManagement.model;

import java.util.List;
import java.util.Map;

public class DashboardData {
    private int[] patients;
    private int[] doctors;
    private int[] consultations;
    private String[] months;
    private List<Map<String, Object>> specialities;

    // Getters e setters
    public int[] getPatients() {
        return patients;
    }

    public void setPatients(int[] patients) {
        this.patients = patients;
    }

    public int[] getDoctors() {
        return doctors;
    }

    public void setDoctors(int[] doctors) {
        this.doctors = doctors;
    }

    public int[] getConsultations() {
        return consultations;
    }

    public void setConsultations(int[] consultations) {
        this.consultations = consultations;
    }

    public String[] getMonths() {
        return months;
    }

    public void setMonths(String[] months) {
        this.months = months;
    }

    public List<Map<String, Object>> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Map<String, Object>> specialities) {
        this.specialities = specialities;
    }
}
