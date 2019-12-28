package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.maps.RolePrivilegeMap;

public class RoleDaoImpl implements RoleDao {
  private static RoleDaoImpl instance = null;

  private PrivilegeDaoImpl privilegeDao = null;

  private RolePrivilegeMap rolePrivilegeMap = null;

  private RoleDaoImpl() {
    privilegeDao = PrivilegeDaoImpl.getInstance();
    rolePrivilegeMap = RolePrivilegeMap.getInstance();
  }

  public static RoleDaoImpl getInstance() {
    if (instance == null) {
      instance = new RoleDaoImpl();
    }
    return instance;
  }

  private static final String SELECT_ROLE_STRING =
          "select role from role where id=?";

  private static final String ASSIGN_WEB_ROLE =
          "insert into website_role(role_id, website_id, developer_id) values (?, ?, ?)";

  private static final String ASSIGB_PAGE_ROLE =
          "insert into page_role(role_id, page_id, developer_id) values (?, ?, ?)";

  private static final String DELETE_WEB_ROLE =
          "delete from website_role where role_id=? and website_id=? and developer_id=?";

  private static final String DELETE_PAGE_ROLE =
          "delete from page_role where role_id=? and page_id=? and developer_id=?";

  private static final String FIND_WEB_ROLE_BY_WEB_DEV_ID =
          "select role_id from website_role where website_id=? and developer_id=?";

  /**
   * inserts into table Role a record that assigns a developer whose id is developerId,
   * the role with roleId, to the website with websiteId
   * @param developerId developer id
   * @param websiteId website id
   * @param roleId role id
   */
  public void assignWebsiteRole(int developerId, int websiteId, int roleId) {
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement assignWeb = connection.prepareStatement(ASSIGN_WEB_ROLE);
      assignWeb.setInt(1, roleId);
      assignWeb.setInt(2, websiteId);
      assignWeb.setInt(3, developerId);
      assignWeb.executeUpdate();
      assignWeb.close();
      String roleString = "";
      PreparedStatement getRoleString = connection.prepareStatement(SELECT_ROLE_STRING);
      getRoleString.setInt(1, roleId);
      ResultSet resultSet = getRoleString.executeQuery();
      if (resultSet.next()) {
        roleString = resultSet.getString("role");
      }
      else {
        throw new SQLException();
      }
      List<String> privileges = rolePrivilegeMap.getPrivilege(roleString);
      if (privileges == null || privileges.size() == 0) {
        throw new SQLException();
      }
      for (String privilege : privileges) {
        privilegeDao.assignWebsitePrivilege(developerId, websiteId, privilege);
      }
      getRoleString.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
  }

  /**
   * inserts into table Role a record that assigns a developer whose id is developerId,
   * the role with roleId, to the page with pageId
   * @param developerId developer id
   * @param pageId page id
   * @param roleId role id
   */
  public void assignPageRole(int developerId, int pageId, int roleId) {
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement assignPage = connection.prepareStatement(ASSIGB_PAGE_ROLE);
      assignPage.setInt(1, roleId);
      assignPage.setInt(2, pageId);
      assignPage.setInt(3, developerId);
      assignPage.executeUpdate();
      assignPage.close();
      String roleString = "";
      PreparedStatement getRoleString = connection.prepareStatement(SELECT_ROLE_STRING);
      getRoleString.setInt(1, roleId);
      ResultSet resultSet = getRoleString.executeQuery();
      if (resultSet.next()) {
        roleString = resultSet.getString("role");
      }
      else {
        throw new SQLException();
      }
      List<String> privileges = rolePrivilegeMap.getPrivilege(roleString);
      if (privileges == null || privileges.size() == 0) {
        throw new SQLException();
      }
      for (String privilege : privileges) {
        privilegeDao.assignPagePrivilege(developerId, pageId, privilege);
      }
      getRoleString.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
  }

  /**
   * deletes from table Role a record that removes roleId from developerId, on websiteId
   * @param developerId developer id
   * @param websiteId website id
   * @param roleId role id
   */
  public void deleteWebsiteRole(int developerId, int websiteId, int roleId) {
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      String roleString = "";
      PreparedStatement getRoleString = connection.prepareStatement(SELECT_ROLE_STRING);
      getRoleString.setInt(1, roleId);
      ResultSet resultSet = getRoleString.executeQuery();
      if (resultSet.next()) {
        roleString = resultSet.getString("role");
      }
      else {
        throw new SQLException();
      }
      List<String> privileges = rolePrivilegeMap.getPrivilege(roleString);
      if (privileges == null || privileges.size() == 0) {
        throw new SQLException();
      }
      for (String privilege : privileges) {
        privilegeDao.deleteWebsitePrivilege(developerId, websiteId, privilege);
      }
      getRoleString.close();
      PreparedStatement deleteWeb = connection.prepareStatement(DELETE_WEB_ROLE);
      deleteWeb.setInt(1, roleId);
      deleteWeb.setInt(2, websiteId);
      deleteWeb.setInt(3, developerId);
      deleteWeb.executeUpdate();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
  }

  /**
   * deletes from table Role a record that removes roleId from developerId, on pageId
   * @param developerId developer id
   * @param pageId page id
   * @param roleId role id
   */
  public void deletePageRole(int developerId, int pageId, int roleId) {
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      String roleString = "";
      PreparedStatement getRoleString = connection.prepareStatement(SELECT_ROLE_STRING);
      getRoleString.setInt(1, roleId);
      ResultSet resultSet = getRoleString.executeQuery();
      if (resultSet.next()) {
        roleString = resultSet.getString("role");
      }
      else {
        throw new SQLException();
      }
      List<String> privileges = rolePrivilegeMap.getPrivilege(roleString);
      if (privileges == null || privileges.size() == 0) {
        throw new SQLException();
      }
      for (String privilege : privileges) {
        privilegeDao.deletePagePriviledge(developerId, pageId, privilege);
      }
      getRoleString.close();
      PreparedStatement deleteWeb = connection.prepareStatement(DELETE_PAGE_ROLE);
      deleteWeb.setInt(1, roleId);
      deleteWeb.setInt(2, pageId);
      deleteWeb.setInt(3, developerId);
      deleteWeb.executeUpdate();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
  }

  public int getWebsiteRoleByPersonId(int websiteId, int personId) {
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement getWebRole = connection.prepareStatement(FIND_WEB_ROLE_BY_WEB_DEV_ID);
      getWebRole.setInt(1, websiteId);
      getWebRole.setInt(2, personId);
      ResultSet resultSet = getWebRole.executeQuery();
      if (resultSet.next()) {
        return resultSet.getInt("role_id");
      }
      else {
        throw new SQLException();
      }
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return -1;
  }
}
