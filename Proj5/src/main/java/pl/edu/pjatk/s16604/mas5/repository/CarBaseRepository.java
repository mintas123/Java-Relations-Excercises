package pl.edu.pjatk.s16604.mas5.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import pl.edu.pjatk.s16604.mas5.model.Car;

import java.util.List;

@NoRepositoryBean
public interface CarBaseRepository<T extends Car> extends CrudRepository<T, Long> {

    @Query("select x from #{#entityName} as x where x.brand = ?1 ")
    List<T> findAllByBrand(String brand);
}
