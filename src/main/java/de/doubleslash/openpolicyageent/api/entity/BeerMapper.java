package de.doubleslash.openpolicyageent.api.entity;

import java.util.List;

import org.mapstruct.Mapper;

import de.doubleslash.openpolicyageent.business.entity.BeerBE;

@Mapper
public interface BeerMapper {

    BeerResponse toResponse(final BeerBE beerBE);

    BeerBE toBeerBE(final BeerRequest beerRequest);

}
