package pl.edu.pjatk.s16604.mas5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.s16604.mas5.model.Dealer;
import pl.edu.pjatk.s16604.mas5.repository.DealerRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class DealerService {

    @Autowired
    DealerRepository repo;

    public void persist(Dealer dealer) {
        repo.save(dealer);
    }

    public List<Dealer> getByName(String name) {
        return repo.findByNameIgnoreCase(name);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Dealer> getDealers() {
        return (List<Dealer>) repo.findAll();
    }

    public Dealer getDealerById(Long id) {
        Optional<Dealer> optionalDealer = repo.findById(id);
        return optionalDealer.orElseThrow(() -> new NoSuchElementException("Couldn't find a Dealer with id: " + id));
    }

}
