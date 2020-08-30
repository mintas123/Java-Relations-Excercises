package pl.edu.pjatk.s16604.mas_FP.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.s16604.mas_FP.DTO.DoctorDTO;
import pl.edu.pjatk.s16604.mas_FP.DateUtils;
import pl.edu.pjatk.s16604.mas_FP.entity.Appointment;
import pl.edu.pjatk.s16604.mas_FP.entity.Doctor;
import pl.edu.pjatk.s16604.mas_FP.entity.Patient;
import pl.edu.pjatk.s16604.mas_FP.repository.AppointmentRepository;
import pl.edu.pjatk.s16604.mas_FP.repository.DoctorRepository;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
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

    public Doctor searchDoctorById(long doctorId) {
        Doctor doctor = doctorRepository.getByPersonId(doctorId);
        if (doctor == null) {
            throw new RuntimeException("No such doctor");
        } else {
            return doctor;
        }
    }

    // Search for free dates, where both patient and doc dont are free in the given period
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
        return DateUtils.getFreeSpots(appByDoc, appByPatient, dateFrom, dateTo, hasReferral);
    }


    // get info about taken spots for a selected doctor in the given period
    public List<Appointment> getSchedule(Long doctorId, LocalDateTime dateFrom, LocalDateTime dateTo) {
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
