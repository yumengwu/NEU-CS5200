package edu.northeastern.cs5200.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.models.Faculty;

@Repository
public interface FacultyRepository extends CrudRepository<Faculty, Integer> {
  @Query("select f from Faculty f where f.username=?1")
  Faculty findFacultyByUsername(String username);
}
