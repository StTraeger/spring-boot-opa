package de.doubleslash.openpolicyageent.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "BeerRequest", description = "Data object holding the beer data to insert")
public record BeerRequest(
        @Schema(description = "The name of the beer", example = "Beer")
        String name,
        @Schema(description = "The alcohol content of the beer", example = "5.0")
        double alcohol
) {

}
