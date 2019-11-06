package edu.northeastern.cs5200.models;

import javax.persistence.*;

//@Entity
public class Answer {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private Student student;
  private Question question;
  private boolean trueFalseAnswer;
  private int multipleChoiceAnswer;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public boolean isTrueFalseAnswer() {
    return trueFalseAnswer;
  }

  public void setTrueFalseAnswer(boolean trueFalseAnswer) {
    this.trueFalseAnswer = trueFalseAnswer;
  }

  public int getMultipleChoiceAnswer() {
    return multipleChoiceAnswer;
  }

  public void setMultipleChoiceAnswer(int multipleChoiceAnswer) {
    this.multipleChoiceAnswer = multipleChoiceAnswer;
  }

  public Answer() {
  }
}
