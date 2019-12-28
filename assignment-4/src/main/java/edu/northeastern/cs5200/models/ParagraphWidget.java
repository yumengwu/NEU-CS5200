package edu.northeastern.cs5200.models;

import javax.persistence.*;

//@Entity
public class ParagraphWidget extends Widget {
  private String text;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public ParagraphWidget() {
    super();
  }
}
