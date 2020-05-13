package utils;

import java.time.LocalDate;


public class Utils {

    public static void checkIfNull(String param) {
        if (param == null || param.isEmpty()) {
            throw new IllegalArgumentException("not null/ non-empty string required");
        }
    }

    public static void checkIfNull(LocalDate param) {
        if (param == null) {
            throw new IllegalArgumentException("not null date required");
        }
    }
}