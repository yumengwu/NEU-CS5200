package edu.northeastern.cs5200.models;

import javax.persistence.*;

//@Entity
public class Topic {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String label;

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

  public Topic() {
  }
}
