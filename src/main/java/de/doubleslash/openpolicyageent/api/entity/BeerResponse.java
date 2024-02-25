package de.doubleslash.openpolicyageent.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(name = "BeerResponse", description = "Data object holding the beer data")
@Builder
public record BeerResponse(

        @Schema(description = "Identifier of the beer entry", example = "d371ed5d-a3a3-43ed-9af4-5db1b7141bfa")
        String id,
        @Schema(description = "The brewery of the beer", example = "Brewery")
        String brewery,
        @Schema(description = "The name of the beer", example = "Beer")
        String name,
        @Schema(description = "The alcohol content of the beer", example = "5.0")
        double alcohol
) {

}
