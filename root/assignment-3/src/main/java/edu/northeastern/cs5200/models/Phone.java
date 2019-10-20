package edu.northeastern.cs5200.models;

public class Phone {
  private String number;
  private boolean primary;

  public Phone() {
  }

  public Phone(String number, boolean primary) {
    this.number = number;
    this.primary = primary;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public boolean getPrimary() {
    return primary;
  }

  public void setPrimary(boolean primary) {
    this.primary = primary;
  }

  @Override
  public String toString() {
    return "number: " + this.getNumber() + ", primary: " + this.getPrimary();
  }
}
