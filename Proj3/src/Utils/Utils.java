package Utils;

import overlapping_inheritance.User;

import java.time.LocalDate;
import java.util.EnumSet;

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

    public static void checkIfNull(Integer param) {
        if (param == null) {
            throw new IllegalArgumentException("not null number required");
        }
    }

    public static void checkIfNullRoles(EnumSet<User.UserRole> roles) {
        if (roles == null) {
            throw new IllegalArgumentException("roles cannot be null");
        }
        if (roles.isEmpty()) {
            throw new IllegalArgumentException("roles cannot be empty");
        }
    }


}
