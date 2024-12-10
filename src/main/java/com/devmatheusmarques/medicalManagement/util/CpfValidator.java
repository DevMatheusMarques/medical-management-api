package com.devmatheusmarques.medicalManagement.util;

import java.util.regex.Pattern;

public class CpfValidator {

    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");

    /**
     * Valida se o CPF está no formato correto.
     *
     * @param cpf CPF em formato string.
     * @return true se o CPF for válido, false caso contrário.
     */
    public static boolean isValid(String cpf) {
        if (cpf == null || !CPF_PATTERN.matcher(cpf).matches()) {
            return false;
        }

        return isCpfValidDigits(cpf.replace(".", "").replace("-", ""));
    }

    private static boolean isCpfValidDigits(String cpf) {
        if (cpf.length() != 11 || cpf.chars().distinct().count() == 1) {
            return false;
        }

        int[] weights = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int firstDigit = calculateDigit(cpf.substring(0, 9), weights);
        int secondDigit = calculateDigit(cpf.substring(0, 9) + firstDigit, weights);

        return cpf.equals(cpf.substring(0, 9) + firstDigit + secondDigit);
    }

    private static int calculateDigit(String base, int[] weights) {
        int sum = 0;
        for (int i = 0; i < base.length(); i++) {
            sum += Character.getNumericValue(base.charAt(i)) * weights[i];
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }
}
