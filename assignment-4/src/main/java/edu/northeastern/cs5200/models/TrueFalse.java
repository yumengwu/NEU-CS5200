package edu.northeastern.cs5200.models;

import javax.persistence.*;

//@Entity
public class TrueFalse extends Question {
  private Boolean isTrue;

  public Boolean getTrue() {
    return isTrue;
  }

  public void setTrue(Boolean aTrue) {
    isTrue = aTrue;
  }

  public TrueFalse() {
    super();
  }
}
