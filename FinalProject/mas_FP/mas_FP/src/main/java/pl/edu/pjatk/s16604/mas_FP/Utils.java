package pl.edu.pjatk.s16604.mas_FP;

import pl.edu.pjatk.s16604.mas_FP.model.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static List<LocalDateTime> getFreeSpots(List<Appointment> taken, LocalDate dateFrom, LocalDate dateTo) {

        List<LocalDate> dateList = dateFrom.datesUntil(dateTo).collect(Collectors.toList());
        List<LocalDateTime> allSpots = new ArrayList<>();

        dateList.forEach(date -> {
            allSpots.addAll(getSpotsInDay(date));
        });

        List<LocalDateTime> takenSpots = new ArrayList<>();
        taken.forEach(appointment -> takenSpots.add(appointment.getDate()));

        allSpots.removeAll(takenSpots);

        return allSpots;
    }

    public static List<LocalDateTime> getSpotsInDay(LocalDate date) {
        LocalDateTime timeFrom = date.atTime(8, 0);
        LocalDateTime timeTo = date.atTime(16, 0);

        List<LocalDateTime> spots = new ArrayList<>();

        while (!timeFrom.isEqual(timeTo)) {
            spots.add(timeFrom);
            timeFrom = timeFrom.plusMinutes(15);
        }
        return spots;
    }
}
