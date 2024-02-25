package de.doubleslash.openpolicyageent.api.control;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

import de.doubleslash.openpolicyageent.api.entity.BeerMapper;
import de.doubleslash.openpolicyageent.api.entity.BeerMapperImpl;
import de.doubleslash.openpolicyageent.api.entity.BeerRequest;
import de.doubleslash.openpolicyageent.api.entity.BeerResponse;
import de.doubleslash.openpolicyageent.core.control.BeerService;
import de.doubleslash.openpolicyageent.core.entity.BeerBE;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@RequestMapping("/breweries")
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

    @Operation(summary = "Get all beers from a brewery",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of beers"),
                    @ApiResponse(responseCode = "404", description = "Brewey not found")
            })
    @GetMapping("/{brewery}/beers")
    public ResponseEntity<List<BeerResponse>> getAllBeers(@PathVariable(value = "brewery") final String brewery) {

        return ResponseEntity.ok(service.getBeerForBrewery(brewery)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList()));
    }

    @Operation(summary = "Get a specific beer by its id", responses = {
            @ApiResponse(responseCode = "200", description = "Beer found"),
            @ApiResponse(responseCode = "404", description = "Beer not found")
    })
    @GetMapping("/{brewery}/beers/{id}")
    public ResponseEntity<BeerResponse> getBeerById(@PathVariable(value = "brewery") final String brewery,
            @PathVariable(value = "id") final UUID id) {

        final BeerBE beerById = service.getBeerById(brewery, id);

        return ResponseEntity.ok(mapper.toResponse(beerById));
    }

    @Operation(summary = "Create and save a new beer", responses = {
            @ApiResponse(responseCode = "201", description = "Beer created"),
            @ApiResponse(responseCode = "409", description = "Beer already exists"),
            @ApiResponse(responseCode = "400", description = "Brewery does not match")
    })
    @PostMapping(value = "/{brewery}/beers", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BeerResponse> addBeer(@PathVariable(value = "brewery") final String brewery,
            @RequestBody final BeerRequest beerRequest) {

        final BeerBE insertedBeer = service.addBeer(mapper.toBeerBE(beerRequest, brewery));

        return new ResponseEntity<>(mapper.toResponse(insertedBeer), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing beer", responses = {
            @ApiResponse(responseCode = "201", description = "Beer updated"),
            @ApiResponse(responseCode = "404", description = "Beer not found")
    })
    @PutMapping(path = "/{brewery}/beers/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BeerResponse> updateBeer(@PathVariable(value = "brewery") final String brewery,
            @PathVariable("id") final UUID id, @RequestBody final BeerRequest beerRequest) {
        return new ResponseEntity<>(
                mapper.toResponse(service.updateBeer(id, mapper.toBeerBE(beerRequest, brewery))),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a beer by its id", responses = {
            @ApiResponse(responseCode = "204", description = "Beer deleted"),
            @ApiResponse(responseCode = "404", description = "Beer not found")
    })
    @DeleteMapping("/{brewery}/beers/{id}")
    public ResponseEntity<Void> deleteBeer(@PathVariable(value = "brewery") final String brewery,
            @PathVariable(value = "id") final UUID id) {
        service.deleteBeer(brewery, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
