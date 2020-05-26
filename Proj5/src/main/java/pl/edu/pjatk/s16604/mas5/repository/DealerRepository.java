package pl.edu.pjatk.s16604.mas5.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.edu.pjatk.s16604.mas5.model.Dealer;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface DealerRepository extends CrudRepository<Dealer, Long> {

    @Query(value = "SELECT d from Dealer d left join fetch d.fleet WHERE d.name = (:name)")
    List<Dealer> findByNameIgnoreCase(String name);

    @Query(value = "SELECT d from Dealer d left join fetch d.fleet WHERE d.dealerId = (:id)")
    @Override
    Optional<Dealer> findById(Long id);

}
