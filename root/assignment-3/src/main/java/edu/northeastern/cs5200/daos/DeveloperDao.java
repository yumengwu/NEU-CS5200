package edu.northeastern.cs5200.daos;

import java.util.Collection;

import edu.northeastern.cs5200.models.Developer;

public interface DeveloperDao {
  /**
   * Inserts properties in ​developer​ instance parameter in tables ​Developer​ and P​ erson
   * @param developer developer
   */
  void createDeveloper(Developer developer);

  /**
   * Returns all joined records from ​Developer and ​Person tables as a
   * ​Collection of ​Developer instances.
   * @return Collection of ​Developer instances
   */
  Collection<Developer> findAllDevelopers();

  /**
   * returns a joined record from ​Developer and ​Person tables whose ​id
   * field is equal to the ​developerId parameter
   * @param id developer id
   * @return developer with given id
   */
  Developer findDeveloperById(int id);

  /**
   * returns a joined record from ​Developer and ​Person tables whose ​username
   * field matches the parameter.
   * @param username username
   * @return developer with given username
   */
  Developer findDeveloperByUsername(String username);

  /**
   * returns a joined record from ​Developer and ​Person tables whose ​username
   * and ​password fields match the parameters
   * @param username username
   * @param password password
   * @return developer with given username and password
   */
  Developer findDeveloperByCredentials(String username, String password);

  /**
   * updates records in ​Developer and ​Person tables whose ​id field is equal
   * to ​developerId parameter. New record field values are set to the values
   * in the ​developer​ instance parameter.
   * @param developerId developer id
   * @param developer developer
   * @return int
   */
  int updateDeveloper(int developerId, Developer developer);

  /**
   * deletes records from ​Developer and ​Person tables whose ​id field is equal
   * to ​developerId parameter. Do not make any modifications to the instance
   * provided.
   * @param developerId developer id
   * @return int
   */
  int deleteDeveloper(int developerId);
}
