package de.doubleslash.openpolicyageent.api;

import de.doubleslash.openpolicyageent.control.BeerService;
import de.doubleslash.openpolicyageent.entity.Beer;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

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
@OpenAPIDefinition(info = @Info(title = "Beer API", version = "1.0", description = "Beer API"))
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class BeerApi {

    private final BeerService service;

    public BeerApi(final BeerService service) {
        this.service = service;
    }


    @Operation(summary = "Get all beers")
    @GetMapping
    public ResponseEntity<List<Beer>> getAllBeers() {
        return ResponseEntity.ok(service.getAllBeers());
    }

    @Operation(summary = "Get beers for a brewery")
    @GetMapping("/{brewery}")
    public ResponseEntity<List<Beer>> getBeersForBrewery(@PathVariable(value = "brewery") final String brewery) {
        return ResponseEntity.ok(service.getBeerForBrewery(brewery));
    }

    @Operation(summary = "Create and save a new beer")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Beer> addBeer(@RequestBody final Beer beer) {
        return new ResponseEntity<>(service.addBeer(beer), HttpStatus.CREATED);
    }


    // TODO: How should we update? What if the beer does not exist?
    @Operation(summary = "Update an existing beer")
    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Beer> updateBeer(@PathVariable("id") final long id, @RequestBody final Beer beer) {
        return new ResponseEntity<>(service.updateBeer(id, beer), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a beer by its id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeer(@PathVariable(value = "id") final long id) {
        service.deleteBeer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
