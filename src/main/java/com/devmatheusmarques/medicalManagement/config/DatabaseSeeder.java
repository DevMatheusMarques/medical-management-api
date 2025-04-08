package com.devmatheusmarques.medicalManagement.config;

import com.devmatheusmarques.medicalManagement.model.User;
import com.devmatheusmarques.medicalManagement.repository.UserRepository;
import com.devmatheusmarques.medicalManagement.util.Status;
import com.devmatheusmarques.medicalManagement.util.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            if (!userRepository.existsByLogin("admin")) {
                User user = new User(
                        "admin",
                        new BCryptPasswordEncoder().encode("admin"), // senha segura
                        UserRole.ADMIN,
                        Status.ACTIVE,
                        LocalDateTime.now()
                );

                userRepository.save(user);
                System.out.println("✅ Usuário admin criado com sucesso.");
            }
        };
    }
}
