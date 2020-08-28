package pl.edu.pjatk.s16604.mas_FP.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjatk.s16604.mas_FP.entity.Division;

import javax.transaction.Transactional;

@Transactional
public interface DivisionRepository extends CrudRepository<Division, Long> {

    Division getAllByDivisionId(long id);

}
