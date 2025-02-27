package com.devmatheusmarques.medicalManagement.service;

import com.devmatheusmarques.medicalManagement.exception.InactiveUserException;
import com.devmatheusmarques.medicalManagement.model.User;
import com.devmatheusmarques.medicalManagement.repository.UserRepository;
import com.devmatheusmarques.medicalManagement.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        if (user.getStatus() != Status.ACTIVE) {
            try {
                throw new InactiveUserException("Usuário inativo, acesso negado");
            } catch (InactiveUserException e) {
                throw new RuntimeException(e);
            }
        }

        return user;
    }
}
