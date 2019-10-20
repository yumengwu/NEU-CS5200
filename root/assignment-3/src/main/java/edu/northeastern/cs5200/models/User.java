package edu.northeastern.cs5200.models;

import java.util.List;

public class User extends Person {
  private String userKey;
  private boolean userAgreement;

  public User() {
    super();
  }

  public User(int id, String firstName, String lastName, String username, String password,
              String email, String dob) {
    super(id, firstName, lastName, username, password, email, dob);
  }

  public User(int id, String firstName, String lastName, String username, String password,
              String email, String dob, String userKey, boolean userAgreement) {
    super(id, firstName, lastName, username, password, email, dob);
    this.userKey = userKey;
    this.userAgreement = userAgreement;
  }

  public User(int id, String firstName, String lastName, String username, String password,
              String email, String dob, List<Phone> phones, List<Address> addresses) {
    super(id, firstName, lastName, username, password, email, dob, phones, addresses);
  }

  public User(int id, String firstName, String lastName, String username, String password,
              String email, String dob, List<Phone> phones, List<Address> addresses,
              String userKey, boolean userAgreement) {
    super(id, firstName, lastName, username, password, email, dob, phones, addresses);
    this.userKey = userKey;
    this.userAgreement = userAgreement;
  }

  public String getUserKey() {
    return userKey;
  }

  public void setUserKey(String userKey) {
    this.userKey = userKey;
  }

  public boolean isUserAgreement() {
    return userAgreement;
  }

  public void setUserAgreement(boolean userAgreement) {
    this.userAgreement = userAgreement;
  }

  @Override
  public String toString() {
    return "id: " + this.getId() + ", username: " + this.getUsername() + ", password: "
            + this.getPassword() + ", firstname: " + this.getFirstName() + ", lastname: "
            + this.getLastName() + ", email: " + this.getEmail() + ", dob: " + this.getDob()
            + ", userKey: " + this.getUserKey();
  }
}
