package de.doubleslash.openpolicyageent.api;

import de.doubleslash.openpolicyageent.control.BeerService;
import de.doubleslash.openpolicyageent.entity.Beer;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RequestMapping("/beers")
@RestController
public class BeerApi {

    private final BeerService service;

    public BeerApi(BeerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Beer>> getAllBeers() {
        return ResponseEntity.ok(service.getAllBeers());
    }

    @GetMapping("/{brewery}")
    public ResponseEntity<List<Beer>> getBeersForBrewery(@PathVariable(value = "brewery") final String brewery) {
        return ResponseEntity.ok(service.getBeerForBrewery(brewery));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Beer> addBeer(@RequestBody final Beer beer) {
        return new ResponseEntity<>(service.addBeer(beer), HttpStatus.CREATED);
    }


    // TODO: How should we update? What if the beer does not exist?
    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Beer> updateBeer(@RequestBody final Beer beer) {
        return new ResponseEntity<>(service.updateBeer(beer), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeer(@PathVariable(value = "id") final Long id) {
        service.deleteBeer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
