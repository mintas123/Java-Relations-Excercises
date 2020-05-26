package pl.edu.pjatk.s16604.mas5.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.s16604.mas5.model.Truck;

@Repository
public interface TruckRepository extends CarBaseRepository<Truck>, CrudRepository<Truck, Long> {
}
