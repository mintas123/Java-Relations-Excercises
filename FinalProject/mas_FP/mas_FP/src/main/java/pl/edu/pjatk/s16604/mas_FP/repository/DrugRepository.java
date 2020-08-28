package pl.edu.pjatk.s16604.mas_FP.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjatk.s16604.mas_FP.entity.Drug;

import javax.transaction.Transactional;

@Transactional
public interface DrugRepository extends CrudRepository<Drug, Long> {

    Drug getAllByDrugId(long id);
}
