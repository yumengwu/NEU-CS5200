package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.northeastern.cs5200.Connection;

public class PrivilegeDaoImpl implements PrivilegeDao {
  private static PrivilegeDaoImpl instance;

  private PrivilegeDaoImpl() {
  }

  public static PrivilegeDaoImpl getInstance() {
    if (instance == null) {
      instance = new PrivilegeDaoImpl();
    }
    return instance;
  }

  private static final String ASSIGN_WEBSITE_PRIVILEGE =
          "insert into website_privilege(website_id, developer_id, privilege_id) values (?, ?, ?)";

  private static final String ASSIGN_PAGE_PRIVILEGE =
          "insert into page_privilege(page_id, developer_id, privilege_id) values (?, ?, ?)";

  private static final String DELETE_WEBSITE_PRIVILEGE =
          "delete from website_privilege where website_id=? and developer_id=? and privilege_id=?";

  private static final String DELETE_PAGE_PRIVILEGE =
          "delete from page_privilege where page_id=? and developer_id=? and privilege_id=?";

  /**
   * inserts into table Priviledge a record that assigns a developer whose id is
   * developerId, the priviledge with priviledge name, to the website with websiteId
   * @param developerId developer id
   * @param websiteId website id
   * @param privilege privilege
   */
  public void assignWebsitePrivilege(int developerId, int websiteId, String privilege) {
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement assignWebPrivilege = connection.prepareStatement(ASSIGN_WEBSITE_PRIVILEGE);
      assignWebPrivilege.setInt(1, websiteId);
      assignWebPrivilege.setInt(2, developerId);
      assignWebPrivilege.setString(3, privilege);
      assignWebPrivilege.executeUpdate();
      assignWebPrivilege.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
  }

  /**
   * inserts into table Priviledge a record that assigns a developer whose id is
   * developerId, the priviledge with priviledge name, to the page with pageId
   * @param developerId developer id
   * @param pageId page id
   * @param privilege privilege
   */
  public void assignPagePrivilege(int developerId, int pageId, String privilege) {
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement assignPage = connection.prepareStatement(ASSIGN_PAGE_PRIVILEGE);
      assignPage.setInt(1, pageId);
      assignPage.setInt(2, developerId);
      assignPage.setString(3, privilege);
      assignPage.executeUpdate();
      assignPage.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
  }

  /**
   * deletes from table Privilege a record that removes privilege name from
   * developerId, on websiteId
   * @param developerId developer id
   * @param websiteId website id
   * @param privilege privilege
   */
  public void deleteWebsitePrivilege(int developerId, int websiteId, String privilege) {
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement deleteWeb = connection.prepareStatement(DELETE_WEBSITE_PRIVILEGE);
      deleteWeb.setInt(1, websiteId);
      deleteWeb.setInt(2, developerId);
      deleteWeb.setString(3, privilege);
      deleteWeb.executeUpdate();
      deleteWeb.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
  }

  /**
   * deletes from table priviledge a record that removes priviledge name from
   * developerId, on pageId
   * @param developerId developer id
   * @param pageId page id
   * @param privilege privilege
   */
  public void deletePagePriviledge(int developerId, int pageId, String privilege) {
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement deletePage = connection.prepareStatement(DELETE_PAGE_PRIVILEGE);
      deletePage.setInt(1, pageId);
      deletePage.setInt(2, developerId);
      deletePage.setString(3, privilege);
      deletePage.executeUpdate();
      deletePage.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
  }
}
