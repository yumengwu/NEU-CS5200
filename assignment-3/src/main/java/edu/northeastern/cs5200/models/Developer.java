package edu.northeastern.cs5200.models;

import java.util.List;

public class Developer extends Person {
  private String developerKey;

  public Developer() {
  }

  public Developer(String developerKey, int id, String firstName, String lastName) {
    super(id, firstName, lastName, firstName.toLowerCase() + lastName.toLowerCase(),
            firstName.toLowerCase() + lastName.toLowerCase(), null, null);
    this.developerKey = developerKey;
  }

  public Developer(String developerKey, int id, String firstName, String lastName, String username,
                   String password, String email, String dob) {
    super(id, firstName, lastName, username, password, email, dob);
    this.developerKey = developerKey;
  }

  public Developer(String developerKey, int id, String firstName, String lastName, String username,
                   String password, String email, String dob,
                   List<Phone> phones, List<Address> addresses) {
    super(id, firstName, lastName, username, password, email, dob, phones, addresses);
    this.developerKey = developerKey;
  }

  public String getDeveloperKey() {
    return developerKey;
  }

  public void setDeveloperKey(String developerKey) {
    this.developerKey = developerKey;
  }

  @Override
  public String toString() {
    return "id: " + this.getId() + ", username: " + this.getUsername() + ", password: "
            + this.getPassword() + ", firstname: " + this.getFirstName() + ", lastname: "
            + this.getLastName() + ", email: " + this.getEmail() + ", dob: " + this.getDob()
            + ", developerKey: " + this.getDeveloperKey();
  }
}
