package ua.com.company.hotels.business.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParser {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

    public static LocalDateTime parseToDate(String date) {
        return LocalDate.parse(date, formatter).atStartOfDay();
    }

    public static String parseFromDate(LocalDateTime localDateTime) {
        return formatter.format(localDateTime);
    }
}
