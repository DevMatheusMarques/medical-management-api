package com.devmatheusmarques.medicalManagement.util;

import java.util.regex.Pattern;

public class EmailValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    /**
     * Valida se um endereço de e-mail está no formato correto.
     *
     * @param email O endereço de e-mail para validar.
     * @return true se for válido, false caso contrário.
     */
    public static boolean isValid(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}
