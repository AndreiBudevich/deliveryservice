package by.deliveryservice.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@UtilityClass
@Slf4j
public class DateTimeUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDate getBirthday(String stringBirthday) {
        try {
            return LocalDate.parse(stringBirthday, formatter);
        } catch (DateTimeParseException e) {
            log.info("Unable to recognize date");
            return null;
        }
    }

    public static LocalDate getToday() {
        return LocalDate.now();
    }
}
