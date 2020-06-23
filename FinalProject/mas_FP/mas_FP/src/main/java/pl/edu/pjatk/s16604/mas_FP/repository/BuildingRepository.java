package pl.edu.pjatk.s16604.mas_FP.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjatk.s16604.mas_FP.model.Building;

import javax.transaction.Transactional;

@Transactional
public interface BuildingRepository extends CrudRepository<Building, Long> {

    Building getAllByBuildingId(long id);
}
