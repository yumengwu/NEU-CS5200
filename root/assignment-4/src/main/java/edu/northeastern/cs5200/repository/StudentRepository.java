package edu.northeastern.cs5200.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import edu.northeastern.cs5200.models.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
  @Query("select s from Student s where s.username = ?1")
  Student findByUsername(String username);

  @Query(value = "select p.* from enrollment e, person p, section s where e.student_id=p.id and e.section_id=s.id and s.title=?1", nativeQuery = true)
  List<Student> findBySectionTitle(String sectionTitle);
}
