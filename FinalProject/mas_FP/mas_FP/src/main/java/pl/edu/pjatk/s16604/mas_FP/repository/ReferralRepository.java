package pl.edu.pjatk.s16604.mas_FP.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjatk.s16604.mas_FP.entity.Doctor;
import pl.edu.pjatk.s16604.mas_FP.entity.Patient;
import pl.edu.pjatk.s16604.mas_FP.entity.Referral;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ReferralRepository extends CrudRepository<Referral, Long> {

    Referral getAllByReferralId(long id);

    List<Referral> getAllByPatient(Patient patient);

    List<Referral> getAllByDoctor(Doctor doctor);


}
