package com.example.springbootopa.control;

import java.util.Optional;

import com.example.springbootopa.entity.Salary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SalaryRepository extends CrudRepository<Salary, Long> {
    Optional<Salary> findByUsername(String username);
}
