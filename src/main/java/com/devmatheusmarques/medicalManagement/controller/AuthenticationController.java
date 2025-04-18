package com.devmatheusmarques.medicalManagement.controller;

import com.devmatheusmarques.medicalManagement.dto.AuthenticationDTO;
import com.devmatheusmarques.medicalManagement.dto.LoginResponseDTO;
import com.devmatheusmarques.medicalManagement.dto.RegisterDTO;
import com.devmatheusmarques.medicalManagement.model.User;
import com.devmatheusmarques.medicalManagement.repository.UserRepository;
import com.devmatheusmarques.medicalManagement.service.TokenService;
import com.devmatheusmarques.medicalManagement.util.Status;
import com.devmatheusmarques.medicalManagement.util.UserRole;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        if (auth == null) return ResponseEntity.badRequest().build();

        var user = (User) auth.getPrincipal();

        var accessToken = tokenService.generateToken(user);
        var refreshToken = tokenService.generateRefreshToken(user);

        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        return ResponseEntity.ok(new LoginResponseDTO(accessToken, refreshToken));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if(userRepository.findByLogin(data.login()).isPresent()) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        UserRole userRole = UserRole.fromString(data.role());
        Status status = Status.fromString(data.status());

        User newUser = new User(data.login(), encryptedPassword, userRole, status, LocalDateTime.now());

        userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refresh_token");

        if (refreshToken == null || refreshToken.isBlank()) {
            return ResponseEntity.badRequest().body("Refresh token is required");
        }

        String userLogin = tokenService.validateToken(refreshToken);

        if (userLogin.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }

        User user = userRepository.findByLogin(userLogin)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // VERIFICAÇÃO EXTRA: comparar o token recebido com o armazenado
        if (!refreshToken.equals(user.getRefreshToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }

        String newAccessToken = tokenService.generateToken(user);

        return ResponseEntity.ok(Map.of("access_token", newAccessToken));
    }
}
