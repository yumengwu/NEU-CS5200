package edu.northeastern.cs5200.daos;

import java.util.Collection;

import edu.northeastern.cs5200.models.Website;

public interface WebsiteDao {
  /**
   * inserts properties in ​website instance parameter into the ​Website table.
   * The website's ​developerId foreign key refer to ​Developer ​table primary
   * key ​id whose value is equal to the ​developerId parameter. You can use the
   * owner's user id as the foreign key
   * @param developerId developer id
   * @param website website
   */
  void createWebsiteForDeveloper(int developerId, Website website);

  /**
   * returns all records from ​Website​ table as a ​Collection​ of ​Website​ instances
   * @return website collection
   */
  Collection<Website> findAllWebsites();

  /**
   * returns all records from ​Website table as a ​Collection of ​Website instances
   * whose ​developerId is equal to the ​developerId​ parameter
   * @param developerId developer id
   * @return website collection
   */
  Collection<Website> findWebsitesForDeveloper(int developerId);

  /**
   * returns a record from ​Website​ table whose ​id​ field is equal to the ​websiteId​
   * parameter
   * @param websiteId website id
   * @return website
   */
  Website findWebsiteById(int websiteId);

  /**
   * updates record in ​Website table whose ​id field is equal to ​websiteId parameter.
   * New record field values are set to the values in the ​website​ instance parameter
   * @param websiteId website id
   * @param website website
   * @return int
   */
  int updateWebsite(int websiteId, Website website);

  /**
   * deletes record from ​Website​ table whose ​id​ field is equal to ​websiteId​ parameter
   * @param websiteId website id
   * @return int
   */
  int deleteWebsite(int websiteId);
}
