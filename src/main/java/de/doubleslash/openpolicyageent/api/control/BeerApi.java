package de.doubleslash.openpolicyageent.api.control;

import de.doubleslash.openpolicyageent.api.entity.Beer;
import de.doubleslash.openpolicyageent.api.entity.BeerMapper;
import de.doubleslash.openpolicyageent.api.entity.BeerMapperImpl;
import de.doubleslash.openpolicyageent.control.BeerService;
import de.doubleslash.openpolicyageent.entity.BeerBE;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

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

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/brewerys")
@RestController
@OpenAPIDefinition(info = @Info(title = "Beer API", version = "1.0", description = "Beer API"))
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
@SecurityRequirement(name = "basicAuth")
public class BeerApi {

    private final BeerService service;
    private final BeerMapper mapper = new BeerMapperImpl();

    public BeerApi(final BeerService service) {
        this.service = service;
    }


    @Operation(summary = "Get all beers from a brewery")
    @GetMapping("/{brewery}")
    public ResponseEntity<List<Beer>> getAllBeers(@PathVariable(value = "brewery") final String brewery) {

        return ResponseEntity.ok(service.getBeerForBrewery(brewery)
                .stream()
                .map(mapper::toBeer)
                .collect(Collectors.toList()));
    }

    @Operation(summary = "Create and save a new beer")
    @PostMapping(value = "/{brewery}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Beer> addBeer(@PathVariable(value = "brewery") final String brewery, @RequestBody final Beer beer) {
        return new ResponseEntity<>(mapper.toBeer(service.addBeer(mapper.toBeerBE(beer))), HttpStatus.CREATED);
    }


    @Operation(summary = "Update an existing beer")
    @PutMapping(path = "/{brewery}/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Beer> updateBeer(@PathVariable(value = "brewery") final String brewery, @PathVariable("id") final long id, @RequestBody final Beer beer) {
        return new ResponseEntity<>(mapper.toBeer(service.updateBeer(id, mapper.toBeerBE(beer))), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a beer by its id")
    @DeleteMapping("/{brewery}/{id}")
    public ResponseEntity<Void> deleteBeer(@PathVariable(value = "brewery") final String brewery, @PathVariable(value = "id") final long id) {
        service.deleteBeer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
