package de.doubleslash.openpolicyageent.control;

import de.doubleslash.openpolicyageent.entity.Beer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeerService {

    private final BeerRepository repository;

    public BeerService(final BeerRepository repository) {
        this.repository = repository;
    }

    public List<Beer> getBeerForBrewery(final String brewery) {
        return repository.findByBrewery(brewery);
    }

    public List<Beer> getAllBeers() {
        return repository.findAll();
    }

    public Beer addBeer(final Beer beer) {
        return repository.save(beer);
    }

    public void deleteBeer(final long id) {
        repository.deleteById(id);
    }

    public Beer updateBeer(final long id, final Beer beer) {
        final Beer updateCandidate = repository.getReferenceById(id);

        return repository.save(beer);
    }


}
