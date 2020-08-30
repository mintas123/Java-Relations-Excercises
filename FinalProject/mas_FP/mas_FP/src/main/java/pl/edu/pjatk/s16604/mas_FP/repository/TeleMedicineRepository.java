package pl.edu.pjatk.s16604.mas_FP.repository;

import pl.edu.pjatk.s16604.mas_FP.entity.TeleMedicine;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface TeleMedicineRepository extends AppointmentBaseRepository<TeleMedicine> {

    Optional<TeleMedicine> getAllByAppointmentId(long id);
}
