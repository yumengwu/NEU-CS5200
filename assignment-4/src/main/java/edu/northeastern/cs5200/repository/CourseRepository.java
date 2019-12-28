package edu.northeastern.cs5200.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import edu.northeastern.cs5200.models.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {
  @Query(value = "select c.* from course c left join person p on c.author_id=p.id where p.username=?1", nativeQuery = true)
  List<Course> findByAuthorUsername(String username);

  @Query("select c from Course c where c.label=?1")
  Course findCourseByLabel(String label);
}
