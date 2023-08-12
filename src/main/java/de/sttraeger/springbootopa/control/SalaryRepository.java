package de.sttraeger.springbootopa.control;

import java.util.Optional;

import de.sttraeger.springbootopa.entity.Salary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SalaryRepository extends CrudRepository<Salary, Long> {
    Optional<Salary> findByUsername(String username);
}
