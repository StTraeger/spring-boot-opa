package de.sttraeger.springbootopa.control;

import de.sttraeger.springbootopa.entity.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface DocumentRepository extends CrudRepository<Document, Long> {
}
