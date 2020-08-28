package pl.edu.pjatk.s16604.mas_FP.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import pl.edu.pjatk.s16604.mas_FP.entity.Appointment;

@NoRepositoryBean
public interface AppointmentBaseRepository<T extends Appointment> extends CrudRepository<T, Long> {
}
