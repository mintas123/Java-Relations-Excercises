package pl.edu.pjatk.s16604.mas_FP.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjatk.s16604.mas_FP.model.Doctor;
import pl.edu.pjatk.s16604.mas_FP.model.Patient;
import pl.edu.pjatk.s16604.mas_FP.model.Prescription;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {

    Prescription getAllByPrescriptionId(long id);

    List<Prescription> getAllByPatient(Patient patient);

    List<Prescription> getAllByDoctor(Doctor doctor);

}
