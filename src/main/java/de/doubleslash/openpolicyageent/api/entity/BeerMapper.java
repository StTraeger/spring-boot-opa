package de.doubleslash.openpolicyageent.api.entity;

import org.mapstruct.Mapper;

import de.doubleslash.openpolicyageent.entity.BeerBE;

@Mapper
public interface BeerMapper {

    Beer toBeer(final BeerBE beerBE);

    BeerBE toBeerBE(final Beer beer);

}
