package edu.northeastern.cs5200.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.northeastern.cs5200.models.Course;
import edu.northeastern.cs5200.models.Enrollment;
import edu.northeastern.cs5200.models.Faculty;
import edu.northeastern.cs5200.models.Section;
import edu.northeastern.cs5200.models.Student;
import edu.northeastern.cs5200.repository.CourseRepository;
import edu.northeastern.cs5200.repository.EnrollmentRepository;
import edu.northeastern.cs5200.repository.FacultyRepository;
import edu.northeastern.cs5200.repository.PersonRepository;
import edu.northeastern.cs5200.repository.SectionRepository;
import edu.northeastern.cs5200.repository.StudentRepository;

@Component
public class UniversityDaoImpl implements UniversityDao {
  private static UniversityDaoImpl instance = null;

  private UniversityDaoImpl() {
  }

  public static UniversityDaoImpl getInstance() {
    if (instance == null) {
      instance = new UniversityDaoImpl();
    }
    return instance;
  }

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private EnrollmentRepository enrollmentRepository;

  @Autowired
  private FacultyRepository facultyRepository;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private SectionRepository sectionRepository;

  @Autowired
  private StudentRepository studentRepository;

  public void truncateDatabase() {
    enrollmentRepository.deleteAll();
    sectionRepository.deleteAll();
    courseRepository.deleteAll();
    facultyRepository.deleteAll();
    studentRepository.deleteAll();
    personRepository.deleteAll();
  }

  public Faculty createFaculty(Faculty faculty) {
    return facultyRepository.save(faculty);
  }

  public Student createStudent(Student student) {
    return studentRepository.save(student);
  }

  public Course createCourse(Course course) {
    return courseRepository.save(course);
  }

  public Section createSection(Section section) {
    return sectionRepository.save(section);
  }

  public Course addSectionToCourse(Section section, Course course) {
    course = courseRepository.findCourseByLabel(course.getLabel());
    course.addSection(section);
    return courseRepository.save(course);
  }

  public Course setAuthorForCourse(Faculty faculty, Course course) {
    faculty = facultyRepository.findFacultyByUsername(faculty.getUsername());
    faculty.authoredCourse(course);
    course.setAuthor(faculty);
    facultyRepository.save(faculty);
    return courseRepository.save(course);
  }

  public Boolean enrollStudentInSection(Student student, Section section) {
    section = sectionRepository.findByTitle(section.getTitle());
    if (section.getSeats() > 0) {
      student = studentRepository.findByUsername(student.getUsername());
      Enrollment enrollment = new Enrollment(student, section);
      student.getEnrollments().add(enrollment);
      section.getEnrollments().add(enrollment);
      enrollmentRepository.save(enrollment);
      section.setSeats(section.getSeats() - 1);
      sectionRepository.save(section);
      return true;
    }
    else {
      return false;
    }
  }
}
