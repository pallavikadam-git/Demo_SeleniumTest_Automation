package client.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static String yyyymmdd = "MM/dd/yyyy";

    // Method to get today's date mm/dd/yyyy
    public static String getTodaysDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(yyyymmdd));
    }

    // Method to get a past date mm/dd/yyyy
    public static String getPastDate(int days) {
        return LocalDate.now().minusDays(days).format(DateTimeFormatter.ofPattern(yyyymmdd));
    }

    // Method to get a future date mm/dd/yyyy
    public static String getFutureDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(yyyymmdd));
    }

    // Method to format a LocalDate as a string
    public static String formatDate(LocalDate date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }
}
