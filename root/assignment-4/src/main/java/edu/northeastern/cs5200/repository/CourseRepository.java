package edu.northeastern.cs5200.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import edu.northeastern.cs5200.models.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {
  @Query(value = "select c.* from course c left join person p on c.author_id=p.id where p.username=?1", nativeQuery = true)
  List<Course> findByAuthorUsername(String username);
}
