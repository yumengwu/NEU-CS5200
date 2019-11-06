package edu.northeastern.cs5200.models;

import javax.persistence.*;

//@Entity
public class Question {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String question;
  private float points;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public float getPoints() {
    return points;
  }

  public void setPoints(float points) {
    this.points = points;
  }

  public Question() {
  }
}
