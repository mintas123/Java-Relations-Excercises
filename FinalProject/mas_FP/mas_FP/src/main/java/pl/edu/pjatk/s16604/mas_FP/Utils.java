package pl.edu.pjatk.s16604.mas_FP;

import pl.edu.pjatk.s16604.mas_FP.entity.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static List<LocalDateTime> getFreeSpots(List<Appointment> takenByDoc,
                                                   List<Appointment> takenByPatient,
                                                   LocalDateTime dateFrom, LocalDateTime dateTo,
                                                   boolean hasReferral) {

        List<LocalDate> datesBetween = dateFrom.toLocalDate().datesUntil(dateTo.toLocalDate()).collect(Collectors.toList());
        datesBetween.add(dateTo.toLocalDate());
        List<LocalDateTime> freeSpots = new ArrayList<>();

        datesBetween.forEach(date ->
                freeSpots.addAll(getSpotsInDay(date))
        );

        List<LocalDateTime> takenSpotsByDoc = new ArrayList<>();
        takenByDoc.forEach(appointment -> takenSpotsByDoc.add(appointment.getDate()));

        List<LocalDateTime> takenSpotsByPatient = new ArrayList<>();
        takenByPatient.forEach(appointment -> takenSpotsByPatient.add(appointment.getDate()));

        freeSpots.removeAll(takenSpotsByDoc);
        freeSpots.removeAll(takenSpotsByPatient);

        if (!hasReferral) {
            return freeSpots.stream()
                    .filter(spot -> spot.getMinute() == 0 || spot.getMinute() == 30)
                    .collect(Collectors.toList());
        }

        return freeSpots;
    }

    public static List<LocalDateTime> getSpotsInDay(LocalDate date) {
        LocalDateTime timeFrom = date.atTime(8, 0);
        LocalDateTime timeTo = date.atTime(16, 0);

        List<LocalDateTime> spots = new ArrayList<>();

        while (!timeFrom.isEqual(timeTo)) {
            spots.add(timeFrom);
            timeFrom = timeFrom.plusMinutes(15);
        }
        spots.add(timeTo);
        return spots;
    }
}
