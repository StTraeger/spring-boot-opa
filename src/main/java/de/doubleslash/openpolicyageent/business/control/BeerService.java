package de.doubleslash.openpolicyageent.business.control;

import de.doubleslash.openpolicyageent.business.entity.BeerBE;
import de.doubleslash.openpolicyageent.business.exception.BeerBusinessException;
import de.doubleslash.openpolicyageent.business.exception.LogErrorId;

import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class BeerService {

    private static final Logger LOGGER = Logger.getLogger(BeerService.class.getName());

    private final BeerRepository repository;

    public BeerService(final BeerRepository repository) {
        this.repository = repository;
    }

    public List<BeerBE> getBeerForBrewery(final String brewery) {
        return repository.findByBreweryIgnoringCase(brewery);
    }

    public BeerBE getBeerById(final String brewery, final UUID id) {
        final Optional<BeerBE> searchResult = repository.findByBreweryIgnoringCaseAndId(brewery, id);

        if (searchResult.isEmpty()) {
            throw new BeerBusinessException(LogErrorId.BEER_NOT_FOUND, HttpStatus.NOT_FOUND, id);
        }
        return searchResult.get();
    }

    public BeerBE addBeer(final String brewery, final BeerBE beerBE) {
        final Example<BeerBE> example = Example.of(beerBE);

        repository.findOne(example)
                .ifPresent(searchResult -> {
                    LOGGER.log(Level.SEVERE, "Beer already exists");
                    throw new BeerBusinessException(LogErrorId.BEER_ALREADY_EXISTS,
                            HttpStatus.CONFLICT, searchResult.getId());
                });

        if (!beerBE.getBrewery().equalsIgnoreCase(brewery)) {
            LOGGER.log(Level.SEVERE, "Brewery does not match");
            throw new BeerBusinessException(LogErrorId.BREWERY_DOES_NOT_MATCH, HttpStatus.BAD_REQUEST);
        }

        return repository.save(beerBE);
    }

    public void deleteBeer(final String brewery, final UUID id) {
        repository.findById(id)
                .ifPresentOrElse(beerBE -> {
                    if (!beerBE.getBrewery().equalsIgnoreCase(brewery)) {
                        LOGGER.log(Level.SEVERE, "Brewery does not match");
                        throw new BeerBusinessException(LogErrorId.BREWERY_DOES_NOT_MATCH, HttpStatus.BAD_REQUEST);
                    }
                    repository.delete(beerBE);
                }, () -> {
                    LOGGER.log(Level.SEVERE, "No beer found for given ID. No delete possible.");
                    throw new BeerBusinessException(LogErrorId.BEER_NOT_FOUND, HttpStatus.NOT_FOUND, id);
                });
    }

    public BeerBE updateBeer(final UUID id, final BeerBE beerBE) {
        final Optional<BeerBE> searchResult = repository.findById(id);

        if (searchResult.isEmpty()) {
            LOGGER.log(Level.SEVERE, "No beer found for given ID. No update possible.");
            throw new BeerBusinessException(LogErrorId.BEER_NOT_FOUND, HttpStatus.NOT_FOUND, id);
        }

        final BeerBE beerToUpdate = searchResult.get();
        beerToUpdate.setName(beerBE.getName());
        beerToUpdate.setBrewery(beerBE.getBrewery());
        beerToUpdate.setAlcohol(beerBE.getAlcohol());

        return repository.save(beerToUpdate);
    }


}
