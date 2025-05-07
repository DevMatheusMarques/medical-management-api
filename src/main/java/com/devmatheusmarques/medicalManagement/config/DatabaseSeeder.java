package com.devmatheusmarques.medicalManagement.config;

import com.devmatheusmarques.medicalManagement.model.User;
import com.devmatheusmarques.medicalManagement.repository.UserRepository;
import com.devmatheusmarques.medicalManagement.util.Status;
import com.devmatheusmarques.medicalManagement.util.UserRole;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Objects;

@Configuration
public class DatabaseSeeder {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            if (!userRepository.existsByLogin("admin")) {
                User user = new User(
                        "admin",
                        new BCryptPasswordEncoder().encode("admin"),
                        UserRole.ADMIN,
                        Status.ACTIVE,
                        LocalDateTime.now()
                );

                userRepository.save(user);
                System.out.println("✅ Usuário admin criado com sucesso.");
            }

            if (!tabelasExistem("users", "patients", "doctors", "consultations", "specialties")) {
                populateDataFromSqlScript();
            }
        };
    }

    private boolean tabelasExistem(String... tableNames) {
        for (String tableName : tableNames) {
            String sql = "SELECT EXISTS (" +
                    "SELECT FROM information_schema.tables " +
                    "WHERE table_schema = 'public' AND table_name = ?" +
                    ")";
            Boolean exists = jdbcTemplate.queryForObject(sql, Boolean.class, tableName);
            if (!Boolean.TRUE.equals(exists)) {
                return false;
            }
        }
        return true;
    }

    private void populateDataFromSqlScript() {
        ClassPathResource resource = new ClassPathResource("data.sql");
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(resource);

        databasePopulator.execute(Objects.requireNonNull(jdbcTemplate.getDataSource()));

        System.out.println("✅ Dados iniciais inseridos com sucesso.");
    }
}
