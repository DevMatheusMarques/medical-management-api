package com.devmatheusmarques.medicalManagement.service;

import com.devmatheusmarques.medicalManagement.dto.UserResponseDTO;
import com.devmatheusmarques.medicalManagement.model.User;
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

    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception exception) {
            return List.of();
        }
    }
    public List<UserResponseDTO> findAll() {
        try {
            List<User> users = userRepository.findAll();

            return users.stream()
                    .map(patient -> modelMapper.map(patient, UserResponseDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public UserResponseDTO findByLogin(String login) {
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("Login inválido ou ausente.");
        }

        UserDetails userDetails = userRepository.findByLogin(login);
        if (userDetails == null) {
            throw new NoSuchElementException("Usuário não encontrado");
        }

        return modelMapper.map(userDetails, UserResponseDTO.class);
    }
}
