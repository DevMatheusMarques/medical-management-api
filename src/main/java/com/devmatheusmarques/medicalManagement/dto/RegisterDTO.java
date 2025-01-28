package com.devmatheusmarques.medicalManagement.dto;

import com.devmatheusmarques.medicalManagement.util.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
