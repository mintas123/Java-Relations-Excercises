package pl.edu.pjatk.s16604.mas_FP.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.s16604.mas_FP.Utils;
import pl.edu.pjatk.s16604.mas_FP.model.Appointment;
import pl.edu.pjatk.s16604.mas_FP.model.Doctor;
import pl.edu.pjatk.s16604.mas_FP.repository.AppointmentRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.DoctorRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    private List<LocalDateTime> getFreeSpots(List<Appointment> taken, LocalDate dateFrom, LocalDate dateTo) {
        return Utils.getFreeSpots(taken, dateFrom, dateTo);
    }

    public List<Doctor> searchDoctorByIdOrName(String string) {
        try {
            long l = Long.parseLong(string);
            Doctor byPersonId = doctorRepository.getByPersonId(l);
            return Arrays.asList(byPersonId);
        } catch (Exception e) {
            System.out.println("NOT A NUMBER");
        }
        return doctorRepository.findAllByName(string);
    }

    public List<LocalDateTime> searchByCriteria(Doctor doctor, LocalDate dateFrom, LocalDate dateTo) {
        List<Appointment> appointmentList = appointmentRepository.getAllByCriteria(doctor, dateFrom, dateTo);
        return getFreeSpots(appointmentList, dateFrom, dateTo);
    }

    public List<Appointment> getSchedule(Long doctorId, LocalDate dateFrom, LocalDate dateTo) {
        Doctor doctor = doctorRepository.getByPersonId(doctorId);
        if (doctor != null) {
            return doctor.getVisits().stream()
                    .filter(appointment -> (appointment.getDate().isAfter(ChronoLocalDateTime.from(dateFrom))))
                    .filter(appointment -> (appointment.getDate().isBefore(ChronoLocalDateTime.from(dateTo))))
                    .collect(Collectors.toList());
        }
        return null;
    }
}
