package edu.northeastern.cs5200.models;

import java.util.List;

public class Person {
  private int id;
  private String firstName;
  private String lastName;
  private String username;
  private String password;
  private String email;
  private String dob;
  private List<Phone> phones;
  private List<Address> addresses;

  public Person() {
  }

  public Person(int id, String firstName, String lastName, String username, String password,
                String email, String dob) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.email = email;
    this.dob = dob;
    this.phones = null;
    this.addresses = null;
  }

  public Person(int id, String firstName, String lastName, String username, String password,
                String email, String dob, List<Phone> phones, List<Address> addresses) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.email = email;
    this.dob = dob;
    this.phones = phones;
    this.addresses = addresses;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public List<Phone> getPhones() {
    return phones;
  }

  public void setPhones(List<Phone> phones) {
    this.phones = phones;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }
}
