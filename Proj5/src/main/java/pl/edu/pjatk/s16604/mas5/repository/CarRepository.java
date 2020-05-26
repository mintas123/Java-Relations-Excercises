package pl.edu.pjatk.s16604.mas5.repository;

import pl.edu.pjatk.s16604.mas5.model.Car;

import javax.transaction.Transactional;

@Transactional
public interface CarRepository extends CarBaseRepository<Car> {
}
