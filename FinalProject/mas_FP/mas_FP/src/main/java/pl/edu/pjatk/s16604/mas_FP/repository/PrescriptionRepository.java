package pl.edu.pjatk.s16604.mas_FP.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjatk.s16604.mas_FP.entity.Doctor;
import pl.edu.pjatk.s16604.mas_FP.entity.Patient;
import pl.edu.pjatk.s16604.mas_FP.entity.Prescription;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {

    Optional<Prescription> getAllByPrescriptionId(long id);

    List<Prescription> getAllByPatient(Patient patient);

    List<Prescription> getAllByDoctor(Doctor doctor);

}
