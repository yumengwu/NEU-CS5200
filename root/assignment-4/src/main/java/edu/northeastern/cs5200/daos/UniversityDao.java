package edu.northeastern.cs5200.daos;

import org.springframework.stereotype.Component;

import edu.northeastern.cs5200.models.Course;
import edu.northeastern.cs5200.models.Faculty;
import edu.northeastern.cs5200.models.Section;
import edu.northeastern.cs5200.models.Student;

@Component
public interface UniversityDao {
  void truncateDatabase();

  Faculty createFaculty(Faculty faculty);

  Student createStudent(Student student);

  Course createCourse(Course course);

  Section createSection(Section section);

  Course addSectionToCourse(Section section, Course course);

  Course setAuthorForCourse(Faculty faculty, Course course);

  Boolean enrollStudentInSection(Student student, Section section);
}
