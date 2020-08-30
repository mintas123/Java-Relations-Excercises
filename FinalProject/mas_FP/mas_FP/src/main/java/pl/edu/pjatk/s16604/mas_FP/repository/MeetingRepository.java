package pl.edu.pjatk.s16604.mas_FP.repository;

import pl.edu.pjatk.s16604.mas_FP.entity.Meeting;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface MeetingRepository extends AppointmentBaseRepository<Meeting> {

    Optional<Meeting> getAllByAppointmentId(long id);


}
