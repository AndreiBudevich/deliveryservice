package by.deliveryservice.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@UtilityClass
public class DateTimeUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDate getDateOfBirth(String stringDateOfBirth) {
        try {
            return LocalDate.parse(stringDateOfBirth, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Невозможно распознать дату");
            return null;
        }
    }
}
