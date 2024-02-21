package de.doubleslash.openpolicyageent.control;

import de.doubleslash.openpolicyageent.entity.BeerBE;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeerService {

    private final BeerRepository repository;

    public BeerService(final BeerRepository repository) {
        this.repository = repository;
    }

    public List<BeerBE> getBeerForBrewery(final String brewery) {
        return repository.findByBreweryIgnoringCase(brewery);
    }

    public List<BeerBE> getAllBeers() {
        return repository.findAll();
    }

    public BeerBE getBeerById(final long id) {
        return repository.getReferenceById(id);
    }

    public BeerBE addBeer(final BeerBE beerBE) {
        return repository.save(beerBE);
    }

    public void deleteBeer(final long id) {
        repository.deleteById(id);
    }

    public BeerBE updateBeer(final long id, final BeerBE beerBE) {
        final BeerBE updateCandidate = repository.getReferenceById(id);

        return repository.save(beerBE);
    }


}
