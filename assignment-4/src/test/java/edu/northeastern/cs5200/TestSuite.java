package edu.northeastern.cs5200;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringRunner;

import edu.northeastern.cs5200.daos.FinderDao;
import edu.northeastern.cs5200.daos.UniversityDao;
import edu.northeastern.cs5200.models.Course;
import edu.northeastern.cs5200.models.Faculty;
import edu.northeastern.cs5200.models.Section;
import edu.northeastern.cs5200.models.Student;
import edu.northeastern.cs5200.repository.SectionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSuite {
  @Autowired
  private UniversityDao universityDao;

  @Autowired
  private FinderDao finderDao;

  @Autowired
  private SectionRepository sectionRepository;

  @Before
  public void setUp() {
    /**
     * 1. Empty the database
     */
    universityDao.truncateDatabase();

    /**
     * 2. Creates faculties
     */
    Faculty alan = new Faculty("Alan", "Turin", "alan",
            "password", "123A", true);
    universityDao.createFaculty(alan);

    Faculty ada = new Faculty("Ada", "Lovelace", "ada",
            "password", "123B", true);
    universityDao.createFaculty(ada);

    /**
     * 10. Creates students
     */
    Student alice = new Student("Alice", "Wonderland", "alice",
            "password", 2020, 12000);
    universityDao.createStudent(alice);

    Student bob = new Student("Bob", "Hope", "bob",
            "password", 2021, 23000);
    universityDao.createStudent(bob);

    Student charlie = new Student("Charlie", "Brown", "charlie",
            "password", 2019, 21000);
    universityDao.createStudent(charlie);

    universityDao.createStudent(new Student("Dan", "Craig", "dan",
            "password", 2019, 0));

    universityDao.createStudent(new Student("Edward", "Scissorhands", "edward",
            "password", 2022, 11000));

    universityDao.createStudent(new Student("Frank", "Herbert", "frank",
            "password", 2018, 0));

    universityDao.createStudent(new Student("Gregory", "Peck", "gregory",
            "password", 2023, 10000));

    /**
     * 11. Create courses
     */
    Course cs1234 = new Course("CS1234", alan);
    universityDao.createCourse(cs1234);

    Course cs2345 = new Course("CS2345", alan);
    universityDao.createCourse(cs2345);

    Course cs3456 = new Course("CS3456", ada);
    universityDao.createCourse(cs3456);

    universityDao.createCourse(new Course("CS4567", ada));

    /**
     * 12. Create sections
     */
    Section sec4321 = new Section("SEC4321", 50, cs1234);
    universityDao.createSection(sec4321);

    Section sec5432 = new Section("SEC5432", 50, cs1234);
    universityDao.createSection(sec5432);

    Section sec6543 = new Section("SEC6543", 50, cs2345);
    universityDao.createSection(sec6543);

    universityDao.createSection(new Section("SEC7654", 50, cs3456));

    /**
     * 13. Enroll students in sections
     */
    universityDao.enrollStudentInSection(alice, sec4321);
    universityDao.enrollStudentInSection(alice, sec5432);
    universityDao.enrollStudentInSection(bob, sec5432);
    universityDao.enrollStudentInSection(charlie, sec6543);
  }

  @Test
  public void testTotalNumberOfUsers() {
    assertEquals(finderDao.findAllUsers().size(), 9);
  }

  @Test
  public void testTotalNumberOfFaculty() {
    assertEquals(finderDao.findAllFaculty().size(), 2);
  }

  @Test
  public void testTotalNumberOfStudent() {
    assertEquals(finderDao.findAllStudents().size(), 7);
  }

  @Test
  public void testTotalNumberOfCourse() {
    assertEquals(finderDao.findAllCourses().size(), 4);
  }

  @Test
  public void testTotalNumberOfSection() {
    assertEquals(finderDao.findAllSections().size(), 4);
  }

  @Test
  public void testCourseAuthorship() {
    Faculty alan = new Faculty("Alan", "Turin", "alan",
            "password", "123A", true);
    assertEquals(finderDao.findCoursesForAuthor(alan).size(), 2);
    Faculty ada = new Faculty("Ada", "Lovelace", "ada",
            "password", "123B", true);
    assertEquals(finderDao.findCoursesForAuthor(ada).size(), 2);
  }

  @Test
  public void testSectionPerCourse() {
    assertEquals(finderDao.findSectionForCourse(new Course("CS1234", null)).size(), 2);
    assertEquals(finderDao.findSectionForCourse(new Course("CS2345", null)).size(), 1);
    assertEquals(finderDao.findSectionForCourse(new Course("CS3456", null)).size(), 1);
    assertEquals(finderDao.findSectionForCourse(new Course("CS4567", null)).size(), 0);
  }

  @Test
  public void testSectionEnrollments() {
    assertEquals(finderDao.findStudentsInSection(new Section("SEC4321", 0, null)).size(), 1);
    assertEquals(finderDao.findStudentsInSection(new Section("SEC5432", 0, null)).size(), 2);
    assertEquals(finderDao.findStudentsInSection(new Section("SEC6543", 0, null)).size(), 1);
    assertEquals(finderDao.findStudentsInSection(new Section("SEC7654", 0, null)).size(), 0);
  }

  @Test
  public void testStudentEnrollments() {
    assertEquals(finderDao.findSectionsForStudent(new Student("", "", "alice", "", 0, 0)).size(), 2);
    assertEquals(finderDao.findSectionsForStudent(new Student("", "", "bob", "", 0, 0)).size(), 1);
    assertEquals(finderDao.findSectionsForStudent(new Student("", "", "charlie", "", 0, 0)).size(), 1);
    assertEquals(finderDao.findSectionsForStudent(new Student("", "", "dan", "", 0, 0)).size(), 0);
    assertEquals(finderDao.findSectionsForStudent(new Student("", "", "edward", "", 0, 0)).size(), 0);
    assertEquals(finderDao.findSectionsForStudent(new Student("", "", "frank", "", 0, 0)).size(), 0);
    assertEquals(finderDao.findSectionsForStudent(new Student("", "", "gregory", "", 0, 0)).size(), 0);
  }

  @Test
  public void testSectionSeats() {
    assertEquals(sectionRepository.findSeatsByTitle("SEC4321"), 49);
    assertEquals(sectionRepository.findSeatsByTitle("SEC5432"), 48);
    assertEquals(sectionRepository.findSeatsByTitle("SEC6543"), 49);
    assertEquals(sectionRepository.findSeatsByTitle("SEC7654"), 50);
  }
}
