package pl.edu.pjatk.s16604.mas_FP.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import pl.edu.pjatk.s16604.mas_FP.entity.Person;

@NoRepositoryBean
public interface PersonBaseRepository<T extends Person> extends CrudRepository<T, Long> {
}
