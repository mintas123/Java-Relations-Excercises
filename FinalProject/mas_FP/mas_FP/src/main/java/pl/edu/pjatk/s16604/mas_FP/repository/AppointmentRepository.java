package pl.edu.pjatk.s16604.mas_FP.repository;

import org.springframework.data.jpa.repository.Query;
import pl.edu.pjatk.s16604.mas_FP.entity.Appointment;
import pl.edu.pjatk.s16604.mas_FP.entity.Doctor;
import pl.edu.pjatk.s16604.mas_FP.entity.Patient;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface AppointmentRepository extends AppointmentBaseRepository<Appointment> {


    Appointment getByAppointmentId(long id);

    List<Appointment> getAllByPatient(Patient patient);

    @Query("SELECT a from Appointment  a where a.doctor = ?1 and a.date > ?2 and a.date < ?3 ")
    List<Appointment> getTakenByDocBetween(Doctor doctor, LocalDateTime dateFrom, LocalDateTime dateTo);

    @Query("SELECT a from Appointment  a where a.patient = ?1 and a.date > ?2 and a.date < ?3 ")
    List<Appointment> getTakenByPatientBetween(Patient patient, LocalDateTime dateFrom, LocalDateTime dateTo);


}
