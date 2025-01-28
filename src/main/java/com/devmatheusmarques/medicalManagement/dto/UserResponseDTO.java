package com.devmatheusmarques.medicalManagement.dto;

import com.devmatheusmarques.medicalManagement.util.UserRole;

import java.util.Objects;

public class UserResponseDTO {
    private String username;
    private UserRole role;

    public UserResponseDTO() {
    }

    public UserResponseDTO(String username, UserRole role) {
        this.username = username;
        this.role = role;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseDTO that = (UserResponseDTO) o;
        return Objects.equals(username, that.username) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
