package edu.northeastern.cs5200.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import edu.northeastern.cs5200.models.Person;
import edu.northeastern.cs5200.repository.PersonRepository;

@RestController
public class PersonController {
  @Autowired
  PersonRepository personRepository;

  @GetMapping("/api/persons")
  public List<Person> getAllPerson() {
    return (List<Person>) personRepository.findAll();
  }
}
