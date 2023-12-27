package de.doubleslash.openpolicyageent.api;

import de.doubleslash.openpolicyageent.control.BeerService;
import de.doubleslash.openpolicyageent.entity.Beer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/{brewery}")
    public ResponseEntity<List<Beer>> getBeersForBrewery(@PathVariable(value = "brewery") final String brewery) {
        return ResponseEntity.ok(service.getBeerForBrewery(brewery));
    }

}
