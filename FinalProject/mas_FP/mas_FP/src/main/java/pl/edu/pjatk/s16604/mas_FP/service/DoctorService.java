package pl.edu.pjatk.s16604.mas_FP.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.s16604.mas_FP.DTO.DoctorDTO;
import pl.edu.pjatk.s16604.mas_FP.Utils;
import pl.edu.pjatk.s16604.mas_FP.entity.Appointment;
import pl.edu.pjatk.s16604.mas_FP.entity.Doctor;
import pl.edu.pjatk.s16604.mas_FP.entity.Patient;
import pl.edu.pjatk.s16604.mas_FP.repository.AppointmentRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.DoctorRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
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

    public List<DoctorDTO> getAllDoctors() {
        List<Doctor> all = doctorRepository.findAll();
        List<DoctorDTO> doctorDTOList = new ArrayList<>();
        all.forEach(doctor ->
                doctorDTOList.add(DoctorDTO.builder()
                        .personId(doctor.getPersonId())
                        .name(doctor.getName())
                        .lastName(doctor.getLastName())
                        .divisionName(doctor.getDivision().getName())
                        .build()
                )
        );
        return doctorDTOList;
    }

//    private List<LocalDateTime> getFreeSpots(List<Appointment> takenByDoc,
//                                             List<Appointment> takenByPatient,
//                                             LocalDate dateFrom, LocalDate dateTo,
//                                             boolean ) {
//        return Utils.getFreeSpots(takenByDoc, dateFrom, dateTo);
//    }

    public Doctor searchDoctorById(long doctorId) {
        Doctor doctor = doctorRepository.getByPersonId(doctorId);
        if (doctor == null) {
            throw new RuntimeException("No such doctor");
        } else {
            return doctor;
        }
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

    public List<LocalDateTime> searchByCriteria(Doctor doctor, Patient patient,
                                                LocalDateTime dateFrom, LocalDateTime dateTo, boolean hasReferral) {
        List<Appointment> appByDoc = appointmentRepository.
                getTakenByDocBetween(doctor,
                        dateFrom.minusDays(1),
                        dateTo.plusDays(1));
        List<Appointment> appByPatient = appointmentRepository.
                getTakenByPatientBetween(patient,
                        dateFrom.minusDays(1),
                        dateTo.plusDays(1));
        return Utils.getFreeSpots(appByDoc, appByPatient, dateFrom, dateTo, hasReferral);
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
