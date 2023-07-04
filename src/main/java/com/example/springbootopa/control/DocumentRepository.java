package com.example.springbootopa.control;

import com.example.springbootopa.entity.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface DocumentRepository extends CrudRepository<Document, Long> {
}
