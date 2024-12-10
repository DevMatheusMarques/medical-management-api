package com.devmatheusmarques.medicalManagement.util;

import java.util.regex.Pattern;

public class PhoneValidator {

    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^\\(?\\d{2}\\)?\\s?9?\\d{4}-\\d{4}$"
    );

    /**
     * Valida se um número de telefone está no formato correto.
     *
     * Exemplos válidos:
     * - (11) 91234-5678
     * - 11912345678
     * - 11 91234-5678
     *
     * @param phone O número de telefone para validar.
     * @return true se for válido, false caso contrário.
     */
    public static boolean isValid(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }
}
