package de.doubleslash.openpolicyageent.business.control;

import de.doubleslash.openpolicyageent.business.entity.BeerBE;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BeerRepository extends JpaRepository<BeerBE, UUID> {

    List<BeerBE> findByBreweryIgnoringCase(final String brewery);

    Optional<BeerBE> findByBreweryIgnoringCaseAndId(final String brewery, final UUID id);

}
