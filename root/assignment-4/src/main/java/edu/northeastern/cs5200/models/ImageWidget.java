package edu.northeastern.cs5200.models;

import javax.persistence.*;

//@Entity
public class ImageWidget extends Widget {
  private String src;

  public String getSrc() {
    return src;
  }

  public void setSrc(String src) {
    this.src = src;
  }

  public ImageWidget() {
    super();
  }
}
