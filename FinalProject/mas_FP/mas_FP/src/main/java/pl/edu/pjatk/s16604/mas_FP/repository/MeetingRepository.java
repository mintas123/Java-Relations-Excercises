package pl.edu.pjatk.s16604.mas_FP.repository;

import pl.edu.pjatk.s16604.mas_FP.model.Meeting;

import javax.transaction.Transactional;

@Transactional
public interface MeetingRepository extends AppointmentBaseRepository<Meeting> {

    Meeting getAllByAppointmentId(long id);
}
