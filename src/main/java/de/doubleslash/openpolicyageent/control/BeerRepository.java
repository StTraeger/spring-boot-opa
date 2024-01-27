package de.doubleslash.openpolicyageent.control;

import de.doubleslash.openpolicyageent.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {

    List<Beer> findByBrewery(final String brewery);

}
