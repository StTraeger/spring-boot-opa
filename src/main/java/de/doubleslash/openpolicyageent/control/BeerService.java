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


}
