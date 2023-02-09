package com.marcoadp.github.bancario.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    final static DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static String LocalDateToBrazilFormat(LocalDate localDate) {
        return localDate.format(DATE_FORMATTER);
    }

    public static String LocalDateTimeToBrazilFormat(LocalDateTime localDateTime) {
        return localDateTime.format(DATETIME_FORMATTER);
    }

    public static LocalDate BrazilFormatToLocalDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    public static LocalDateTime BrazilFormatToLocalDateTime(String date) {
        return LocalDateTime.parse(date, DATETIME_FORMATTER);
    }


}
