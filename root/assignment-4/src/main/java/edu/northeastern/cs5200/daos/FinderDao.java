package edu.northeastern.cs5200.daos;

import org.springframework.stereotype.Component;

import java.util.List;

import edu.northeastern.cs5200.models.Course;
import edu.northeastern.cs5200.models.Faculty;
import edu.northeastern.cs5200.models.Person;
import edu.northeastern.cs5200.models.Section;
import edu.northeastern.cs5200.models.Student;

@Component
public interface FinderDao {
  List<Person> findAllUsers();

  List<Faculty> findAllFaculty();

  List<Student> findAllStudents();

  List<Course> findAllCourses();

  List<Section> findAllSections();

  List<Course> findCoursesForAuthor(Faculty faculty);

  List<Section> findSectionForCourse(Course course);

  List<Student> findStudentsInSection(Section section);

  List<Section> findSectionsForStudent(Student student);
}
