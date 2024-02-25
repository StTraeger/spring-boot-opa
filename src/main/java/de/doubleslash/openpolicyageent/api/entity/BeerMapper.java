package de.doubleslash.openpolicyageent.api.entity;

import org.mapstruct.Mapper;

import de.doubleslash.openpolicyageent.core.entity.BeerBE;

@Mapper
public interface BeerMapper {

    BeerResponse toResponse(final BeerBE beerBE);

    BeerBE toBeerBE(final BeerRequest beerRequest, final String brewery);

}
