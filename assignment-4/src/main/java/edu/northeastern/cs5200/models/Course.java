package edu.northeastern.cs5200.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String label;

  @ManyToOne
  @JsonIgnore
  private Faculty author;

  @OneToMany(mappedBy = "course")
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Section> sections;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public List<Section> getSections() {
    return sections;
  }

  public void setSections(List<Section> sections) {
    this.sections = sections;
  }

  public void addSection(Section section) {
    if (!this.sections.contains(section)) {
      this.sections.add(section);
    }
    if (section.getCourse() != this) {
      section.setCourse(this);
    }
  }

  public Faculty getAuthor() {
    return author;
  }

  public void setAuthor(Faculty author) {
    this.author = author;
    if (!author.getCourses().contains(this)) {
      author.getCourses().add(this);
    }
  }

  public Course() {
  }

  public Course(String label, Faculty author) {
    this.label = label;
    this.author = author;
    this.sections = new ArrayList<>();
  }
}
