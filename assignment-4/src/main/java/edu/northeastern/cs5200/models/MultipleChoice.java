package edu.northeastern.cs5200.models;

import javax.persistence.*;

//@Entity
public class MultipleChoice extends Question {
  private String choices;
  private int correct;

  public String getChoices() {
    return choices;
  }

  public void setChoices(String choices) {
    this.choices = choices;
  }

  public int getCorrect() {
    return correct;
  }

  public void setCorrect(int correct) {
    this.correct = correct;
  }

  public MultipleChoice() {
  }
}
