package edu.northeastern.cs5200.daos;

import java.util.List;

public interface RoleDao {
  /**
   * inserts into table Role a record that assigns a developer whose id is developerId,
   * the role with roleId, to the website with websiteId
   * @param developerId developer id
   * @param websiteId website id
   * @param roleId role id
   */
  void assignWebsiteRole(int developerId, int websiteId, int roleId);

  /**
   * inserts into table Role a record that assigns a developer whose id is developerId,
   * the role with roleId, to the page with pageId
   * @param developerId developer id
   * @param pageId page id
   * @param roleId role id
   */
  void assignPageRole(int developerId, int pageId, int roleId);

  /**
   * deletes from table Role a record that removes roleId from developerId, on websiteId
   * @param developerId developer id
   * @param websiteId website id
   * @param roleId role id
   */
  void deleteWebsiteRole(int developerId, int websiteId, int roleId);

  /**
   * deletes from table Role a record that removes roleId from developerId, on pageId
   * @param developerId developer id
   * @param pageId page id
   * @param roleId role id
   */
  void deletePageRole(int developerId, int pageId, int roleId);

  int getWebsiteRoleByPersonId(int websiteId, int personId);
}
