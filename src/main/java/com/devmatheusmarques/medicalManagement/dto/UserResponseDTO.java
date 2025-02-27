package com.devmatheusmarques.medicalManagement.dto;

import com.devmatheusmarques.medicalManagement.util.Status;
import com.devmatheusmarques.medicalManagement.util.UserRole;

import java.util.Objects;

public class UserResponseDTO {
    private String username;
    private UserRole role;
    private Status status;

    public UserResponseDTO() {
    }

    public UserResponseDTO(String username, UserRole role, Status status) {
        this.username = username;
        this.role = role;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseDTO that = (UserResponseDTO) o;
        return Objects.equals(username, that.username) && role == that.role && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role, status);
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "username='" + username + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}
