package utils;

import composition.Folder;

import java.time.LocalDate;

public class ProjUtils {


    public static void checkIfNull(String param) {
        if (param == null || param.isEmpty()) {
            throw new IllegalArgumentException("not null string required");
        }
    }

    public static void checkIfNull(LocalDate param) {
        if (param == null) {
            throw new IllegalArgumentException("not null date required");
        }
    }

    public static void checkIfNull(Integer param) {
        if (param == null) {
            throw new IllegalArgumentException("not null number required");
        }
    }

    public static void checkIfNull(Folder folder) {
        if (folder == null) {
            throw new IllegalArgumentException("not null folder required");
        }
        if (folder.getName().isEmpty()) {
            throw new IllegalArgumentException("not empty folder required");
        }
    }
}
