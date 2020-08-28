package pl.edu.pjatk.s16604.mas_FP.repository;

import pl.edu.pjatk.s16604.mas_FP.entity.Patient;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface PatientRepository extends PersonBaseRepository<Patient> {
    Optional<Patient> getByPersonId(long id);

    List<Patient> getAllByPeselStartingWith(String peselPart);

    @Override
    List<Patient> findAll();

    List<Patient> getAllByNameContainsOrLastNameContainsOrPeselContains(String name, String lastName, String pesel);


}
