package edu.northeastern.cs5200.daos;

public interface PrivilegeDao {
  /**
   * inserts into table Privilege a record that assigns a developer whose id is
   * developerId, the privilege with privilege name, to the website with websiteId
   * @param developerId developer id
   * @param websiteId website id
   * @param privilege privilege
   */
  void assignWebsitePrivilege(int developerId, int websiteId, String privilege);

  /**
   * inserts into table Privilege a record that assigns a developer whose id is
   * developerId, the privilege with privilege name, to the page with pageId
   * @param developerId developer id
   * @param pageId page id
   * @param privilege privilege
   */
  void assignPagePrivilege(int developerId, int pageId, String privilege);

  /**
   * deletes from table Privilege a record that removes privilege name from
   * developerId, on websiteId
   * @param developerId developer id
   * @param websiteId website id
   * @param privilege privilege
   */
  void deleteWebsitePrivilege(int developerId, int websiteId, String privilege);

  /**
   * deletes from table priviledge a record that removes priviledge name from
   * developerId, on pageId
   * @param developerId developer id
   * @param pageId page id
   * @param privilege privilege
   */
  void deletePagePriviledge(int developerId, int pageId, String privilege);
}
