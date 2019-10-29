package edu.northeastern.cs5200.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "student")
@Table(name = "student")
public class Student extends Person {
  @Column(name = "grad_year")
  private int gradYear;

  @Column(name = "scholarship")
  private long scholarship;

  @OneToMany(mappedBy = "student")
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Enrollment> enrollments;

  public int getGradYear() {
    return gradYear;
  }

  public void setGradYear(int gradYear) {
    this.gradYear = gradYear;
  }

  public long getScholarship() {
    return scholarship;
  }

  public void setScholarship(long scholarship) {
    this.scholarship = scholarship;
  }

  public List<Enrollment> getEnrollments() {
    return enrollments;
  }

  public void setEnrollments(List<Enrollment> enrollments) {
    this.enrollments = enrollments;
  }

  public Student() {
    super();
  }

  public Student(String firstName, String lastName, String username, String password,
                 int gradYear, long scholarship) {
    super(firstName, lastName, username, password);
    this.gradYear = gradYear;
    this.scholarship = scholarship;
    this.enrollments = new ArrayList<>();
  }

  public Student(String firstName, String lastName, String username, String password,
                 int gradYear, long scholarship, List<Enrollment> enrollments) {
    super(firstName, lastName, username, password);
    this.gradYear = gradYear;
    this.scholarship = scholarship;
    this.enrollments = enrollments;
  }
}
