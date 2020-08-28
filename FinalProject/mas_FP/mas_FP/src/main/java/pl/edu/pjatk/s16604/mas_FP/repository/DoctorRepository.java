package pl.edu.pjatk.s16604.mas_FP.repository;

import pl.edu.pjatk.s16604.mas_FP.entity.Doctor;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface DoctorRepository extends PersonBaseRepository<Doctor> {

    @Override
    List<Doctor> findAll();

    Doctor getByPersonId(long id);

    List<Doctor> findAllByName(String string);

}
