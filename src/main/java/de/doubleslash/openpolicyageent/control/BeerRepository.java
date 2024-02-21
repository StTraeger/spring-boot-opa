package de.doubleslash.openpolicyageent.control;

import de.doubleslash.openpolicyageent.entity.BeerBE;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeerRepository extends JpaRepository<BeerBE, Long> {

    List<BeerBE> findByBreweryIgnoringCase(final String brewery);

}
