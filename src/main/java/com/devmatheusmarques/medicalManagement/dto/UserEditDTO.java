package com.devmatheusmarques.medicalManagement.dto;

import com.devmatheusmarques.medicalManagement.util.UserRole;

public record UserEditDTO(String password, UserRole role) {
}
