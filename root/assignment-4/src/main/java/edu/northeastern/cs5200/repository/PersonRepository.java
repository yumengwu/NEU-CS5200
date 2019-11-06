package edu.northeastern.cs5200.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.models.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
