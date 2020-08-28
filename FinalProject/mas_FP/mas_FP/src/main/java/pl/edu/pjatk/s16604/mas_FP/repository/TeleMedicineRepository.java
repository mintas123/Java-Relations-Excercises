package pl.edu.pjatk.s16604.mas_FP.repository;

import pl.edu.pjatk.s16604.mas_FP.entity.TeleMedicine;

import javax.transaction.Transactional;

@Transactional
public interface TeleMedicineRepository extends AppointmentBaseRepository<TeleMedicine> {

    TeleMedicine getAllByAppointmentId(long id);
}
