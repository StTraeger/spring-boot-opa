package de.doubleslash.openpolicyageent.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Beer", description = "Data object holding the beer data")
public record Beer (

    @Schema(description = "The id of the beer", example = "1")
    long id,
    @Schema(description = "The brewery of the beer", example = "Brewery")
    String brewery,
    @Schema(description = "The name of the beer", example = "Beer")
    String name,
    @Schema(description = "The alcohol content of the beer", example = "5.0")
    double alcohol
){}
