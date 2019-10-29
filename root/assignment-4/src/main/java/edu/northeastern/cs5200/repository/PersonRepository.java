package edu.northeastern.cs5200.repository;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
