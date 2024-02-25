package de.doubleslash.openpolicyageent.core.control;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.doubleslash.openpolicyageent.core.entity.BeerBE;

@Repository
public interface BeerRepository extends JpaRepository<BeerBE, UUID> {

    List<BeerBE> findByBreweryIgnoringCase(final String brewery);

    Optional<BeerBE> findByBreweryIgnoringCaseAndId(final String brewery, final UUID id);

}
