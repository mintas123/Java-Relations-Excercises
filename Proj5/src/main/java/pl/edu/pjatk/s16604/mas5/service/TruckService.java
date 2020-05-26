package pl.edu.pjatk.s16604.mas5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.s16604.mas5.model.Truck;
import pl.edu.pjatk.s16604.mas5.repository.TruckRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TruckService {

    @Autowired
    TruckRepository repo;

    public void persistTruck(Truck car) {
        repo.save(car);
    }

    public void deleteTruck(Long id) {
        repo.deleteById(id);
    }

    public List<Truck> getTrucks() {
        return (List<Truck>) repo.findAll();
    }

    public Truck getTruckById(Long id) {
        Optional<Truck> optionalTruck = repo.findById(id);
        return optionalTruck.orElseThrow(() -> new NoSuchElementException("Couldn't find a Truck with id: " + id));
    }

    public List<Truck> findAllByBrand(String brand) {
        return repo.findAllByBrand(brand);
    }


}
