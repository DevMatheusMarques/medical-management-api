package com.devmatheusmarques.medicalManagement.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Converte uma string no formato dd/MM/yyyy para LocalDate.
     *
     * @param dateString Data no formato dd/MM/yyyy.
     * @return LocalDate correspondente.
     */
    public static LocalDate toDate(String dateString) {
        return LocalDate.parse(dateString, DATE_FORMAT);
    }

    /**
     * Formata uma data no formato dd/MM/yyyy.
     *
     * @param date LocalDate a ser formatada.
     * @return String formatada.
     */
    public static String formatDate(LocalDate date) {
        return date.format(DATE_FORMAT);
    }

    /**
     * Converte uma string no formato dd/MM/yyyy HH:mm para LocalDateTime.
     *
     * @param dateTimeString Data e hora no formato dd/MM/yyyy HH:mm.
     * @return LocalDateTime correspondente.
     */
    public static LocalDateTime toDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DATE_TIME_FORMAT);
    }

    /**
     * Formata uma data e hora no formato dd/MM/yyyy HH:mm.
     *
     * @param dateTime LocalDateTime a ser formatada.
     * @return String formatada.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMAT);
    }
}
