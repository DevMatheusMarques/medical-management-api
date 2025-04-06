package com.devmatheusmarques.medicalManagement.service;

import com.devmatheusmarques.medicalManagement.dto.UserEditDTO;
import com.devmatheusmarques.medicalManagement.dto.UserResponseDTO;
import com.devmatheusmarques.medicalManagement.model.User;
import com.devmatheusmarques.medicalManagement.util.EmailValidator;
import com.devmatheusmarques.medicalManagement.util.PhoneValidator;
import com.devmatheusmarques.medicalManagement.util.UserRole;
import org.modelmapper.ModelMapper;
import com.devmatheusmarques.medicalManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  ModelMapper modelMapper;

    public void userEdit(Long id, UserEditDTO userEditDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        if (userEditDTO.getUsername() != null) {
            existingUser.setLogin(userEditDTO.getUsername());
        }
        if (userEditDTO.getPassword() != null) {
            existingUser.setPassword(userEditDTO.getPassword());
        }
        if (userEditDTO.getRole() != null) {
            existingUser.setRole(UserRole.fromString(userEditDTO.getRole()));
        }

        if (userEditDTO.getStatus() != null) {
            existingUser.setStatus(userEditDTO.getStatus());
        }

        userRepository.save(existingUser);
    }

    public void userDelete(Long id) {
        try {
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

            userRepository.delete(existingUser);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir paciente: " + e.getMessage(), e);
        }
    }

    public List<UserResponseDTO> findAll() {
        try {
            List<User> users = userRepository.findAll();

            return users.stream()
                    .map(user -> modelMapper.map(user, UserResponseDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public UserResponseDTO findByLogin(String login) {
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("Login inválido ou ausente.");
        }

        var user = userRepository.findByLogin(login)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        return modelMapper.map(user, UserResponseDTO.class);
    }
}
