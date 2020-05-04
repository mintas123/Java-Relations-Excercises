package dynamic_inheritance;

import java.time.LocalDate;

public interface IManager {
    void giveHoliday(int empId, LocalDate from, LocalDate to);
}
