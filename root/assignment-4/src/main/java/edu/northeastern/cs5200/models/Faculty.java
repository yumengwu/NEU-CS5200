package edu.northeastern.cs5200.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "faculty")
@Table(name = "faculty")
public class Faculty extends Person {
  @Column(name = "office")
  private String office;

  @Column(name = "tenured")
  private boolean tenured;

  @OneToMany(mappedBy = "author")
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Course> courses;

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public boolean isTenured() {
    return tenured;
  }

  public void setTenured(boolean tenured) {
    this.tenured = tenured;
  }

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }

  public void authoredCourse(Course course) {
    if (!this.courses.contains(course)) {
      this.courses.add(course);
    }
    if (course.getAuthor() != this) {
      course.setAuthor(this);
    }
  }

  public Faculty() {
    super();
  }

  public Faculty(String firstName, String lastName, String username, String password,
                 String office, boolean tenured) {
    super(firstName, lastName, username, password);
    this.office = office;
    this.tenured = tenured;
    this.courses = new ArrayList<>();
  }

  public Faculty(String firstName, String lastName, String username, String password,
                 String office, boolean tenured, List<Course> courses) {
    super(firstName, lastName, username, password);
    this.office = office;
    this.tenured = tenured;
    this.courses = courses;
  }
}
