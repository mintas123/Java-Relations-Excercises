package pl.edu.pjatk.s16604.mas_FP.repository;

import pl.edu.pjatk.s16604.mas_FP.model.Person;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface PersonRepository extends PersonBaseRepository<Person> {

    Person getAllByPersonId(long id);

    List<Person> getAllByPeselStartingWith(String peselPart);
}
