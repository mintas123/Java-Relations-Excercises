package pl.edu.pjatk.s16604.mas5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.s16604.mas5.model.Sedan;
import pl.edu.pjatk.s16604.mas5.repository.SedanRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SedanService {
    @Autowired
    SedanRepository repo;


    public void persistSedan(Sedan car) {
        repo.save(car);
    }

    public void deleteSedan(Long id) {
        repo.deleteById(id);
    }

    public List<Sedan> getSedans() {
        return (List<Sedan>) repo.findAll();
    }

    public List<Sedan> getSedansWithAndroidAuto() {
        return (List<Sedan>) repo.findAllByHasAndroidAutoEquals(true);
    }

    public Sedan getSedanById(Long id) {
        Optional<Sedan> optionalSedan = repo.findById(id);
        return optionalSedan.orElseThrow(() -> new NoSuchElementException("Couldn't find a Sedan with id: " + id));
    }

    public List<Sedan> findAllByBrand(String brand) {
        return repo.findAllByBrand(brand);
    }

}
