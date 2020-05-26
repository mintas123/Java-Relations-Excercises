package pl.edu.pjatk.s16604.mas5.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjatk.s16604.mas5.model.Sedan;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface SedanRepository extends CarBaseRepository<Sedan>, CrudRepository<Sedan, Long> {

    List<Sedan> findAllByHasAndroidAutoEquals(boolean b);
}
