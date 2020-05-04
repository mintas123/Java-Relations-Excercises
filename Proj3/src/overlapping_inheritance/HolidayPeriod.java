package overlapping_inheritance;

import java.time.LocalDate;

public class HolidayPeriod {
    private LocalDate start;
    private LocalDate end;

    public HolidayPeriod(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;

    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
