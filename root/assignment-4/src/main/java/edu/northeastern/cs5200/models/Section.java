package edu.northeastern.cs5200.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "section")
public class Section {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String title;
  private int seats;

  @ManyToOne
  @JsonIgnore
  private Course course;

  @OneToMany(mappedBy = "section")
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Enrollment> enrollments;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getSeats() {
    return seats;
  }

  public void setSeats(int seats) {
    this.seats = seats;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
    if (!course.getSections().contains(this)) {
      course.getSections().add(this);
    }
  }

  public List<Enrollment> getEnrollments() {
    return enrollments;
  }

  public void setEnrollments(List<Enrollment> enrollments) {
    this.enrollments = enrollments;
  }

  public Section() {
  }

  public Section(String title, int seats, Course course) {
    this.title = title;
    this.seats = seats;
    this.course = course;
  }
}
