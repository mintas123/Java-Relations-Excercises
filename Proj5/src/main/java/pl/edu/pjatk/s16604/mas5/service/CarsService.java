package pl.edu.pjatk.s16604.mas5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.s16604.mas5.model.Car;
import pl.edu.pjatk.s16604.mas5.model.Dealer;
import pl.edu.pjatk.s16604.mas5.repository.CarRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CarsService {

    @Autowired
    CarRepository repo;

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Car> getCars() {
        return (List<Car>) repo.findAll();
    }

    public Car getCarById(Long id) {
        Optional<Car> optionalDog = repo.findById(id);
        return optionalDog.orElseThrow(() -> new NoSuchElementException("Couldn't find a Car with id: " + id));
    }

    public List<Car> findAllByBrand(String brand) {
        return repo.findAllByBrand(brand);
    }


}
