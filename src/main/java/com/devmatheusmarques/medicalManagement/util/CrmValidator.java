package com.devmatheusmarques.medicalManagement.util;

import java.util.regex.Pattern;

public class CrmValidator {

    private static final Pattern CRM_PATTERN = Pattern.compile("^\\d{2,3}\\.\\d{3}-[A-Z]{2}$");

    /**
     * Valida se o CRM está no formato correto.
     *
     * @param crm CRM em formato string.
     * @return true se o CRM for válido, false caso contrário.
     */
    public static boolean isValid(String crm) {
        if (crm == null || !CRM_PATTERN.matcher(crm).matches()) {
            return false;
        }

        return true;
    }
}
