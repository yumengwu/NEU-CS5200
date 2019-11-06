package edu.northeastern.cs5200.models;

import javax.persistence.*;

//@Entity
public class Widget {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String type;
  private int width;
  private int height;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public Widget() {
  }
}
