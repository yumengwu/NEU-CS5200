package edu.northeastern.cs5200.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import edu.northeastern.cs5200.models.Course;
import edu.northeastern.cs5200.models.Faculty;
import edu.northeastern.cs5200.models.Person;
import edu.northeastern.cs5200.models.Section;
import edu.northeastern.cs5200.models.Student;
import edu.northeastern.cs5200.repository.CourseRepository;
import edu.northeastern.cs5200.repository.EnrollmentRepository;
import edu.northeastern.cs5200.repository.FacultyRepository;
import edu.northeastern.cs5200.repository.PersonRepository;
import edu.northeastern.cs5200.repository.SectionRepository;
import edu.northeastern.cs5200.repository.StudentRepository;

@Component
public class FinderDaoImpl implements FinderDao {
  private static FinderDaoImpl instance = null;

  private FinderDaoImpl() {
  }

  public static FinderDaoImpl getInstance() {
    if (instance == null) {
      instance = new FinderDaoImpl();
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

  public List<Person> findAllUsers() {
    return (List<Person>) personRepository.findAll();
  }

  public List<Faculty> findAllFaculty() {
    return (List<Faculty>) facultyRepository.findAll();
  }

  public List<Student> findAllStudents() {
    return (List<Student>) studentRepository.findAll();
  }

  public List<Course> findAllCourses() {
    return (List<Course>) courseRepository.findAll();
  }

  public List<Section> findAllSections() {
    return (List<Section>) sectionRepository.findAll();
  }

  public List<Course> findCoursesForAuthor(Faculty faculty) {
    return courseRepository.findByAuthorUsername(faculty.getUsername());
  }

  public List<Section> findSectionForCourse(Course course) {
    return sectionRepository.findByCourseLabel(course.getLabel());
  }

  public List<Student> findStudentsInSection(Section section) {
    return studentRepository.findBySectionTitle(section.getTitle());
  }

  public List<Section> findSectionsForStudent(Student student) {
    return sectionRepository.findByStudentUsername(student.getUsername());
  }
}
