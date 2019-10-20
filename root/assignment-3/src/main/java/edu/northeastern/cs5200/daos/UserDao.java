package edu.northeastern.cs5200.daos;

import java.util.Collection;

import edu.northeastern.cs5200.models.User;

public interface UserDao {
  /**
   * Inserts properties in ​user​ instance parameter in tables ​user​ and P​erson
   * @param user user
   */
  void createUser(User user);

  /**
   * Returns all joined records from User and ​Person tables as a
   * ​Collection of User instances.
   * @return Collection of ​User instances
   */
  Collection<User> findAllUsers();
}
