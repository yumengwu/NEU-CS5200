package edu.northeastern.cs5200.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import edu.northeastern.cs5200.models.Section;

public interface SectionRepository extends CrudRepository<Section, Integer> {
  @Query("select s from Section s where s.title=?1")
  Section findByTitle(String title);

  @Query(value = "select s.* from section s left join course c on s.course_id=c.id where c.label=?1", nativeQuery = true)
  List<Section> findByCourseLabel(String courseTitle);

  @Query(value = "select s.* from enrollment e, person p, section s where e.student_id=p.id and e.section_id=s.id and p.username=?1", nativeQuery = true)
  List<Section> findByStudentUsername(String username);

  @Query("select s.seats from Section s where s.title=?1")
  int findSeatsByTitle(String title);
}
