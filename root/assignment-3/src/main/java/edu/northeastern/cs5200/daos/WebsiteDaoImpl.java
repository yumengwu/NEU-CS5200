package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Website;

public class WebsiteDaoImpl implements WebsiteDao {
  private static WebsiteDaoImpl instance = null;

  private WebsiteDaoImpl() {
  }

  public static WebsiteDaoImpl getInstance() {
    if (instance == null) {
      instance = new WebsiteDaoImpl();
    }
    return instance;
  }

  private static final String INSERT_WEBSITE =
          "insert into website(id, name, description, created, updated, visits, developer_id) values "
                  + "(?, ?, ?, ?, ?, ?, ?)";

  private static final String FIND_ALL_WEBSITE =
          "select * from website";

  private static final String FIND_BY_DEVELOPER =
          "select * from website where developer_id=?";

  private static final String FIND_BY_ID =
          "select * from website where id=?";

  private static final String UPDATE_BY_ID =
          "update website set name=?, description=?, updated=?, visits=? where id=?";

  private static final String FIND_ALL_PAGE_ID =
          "select id from page where website_id=?";
  private static final String DELETE_PAGE_PRIVILEGE =
          "delete from page_privilege where page_id=?";
  private static final String DELETE_PAGE_ROLE =
          "delete from page_role where page_id=?";
  private static final String DELETE_WIDGET =
          "delete from widget where page_id=?";
  private static final String DELETE_PAGE =
          "delete from page where website_id=?";
  private static final String DELETE_WEB_PRIVILEGE =
          "delete from website_privilege where website_id=?";
  private static final String DELETE_WEB_ROLE =
          "delete from website_role where website_id=?";
  private static final String DELETE_WEBSITE =
          "delete from website where id=?";

  /**
   * inserts properties in ​website instance parameter into the ​Website table.
   * The website's ​developerId foreign key refer to ​Developer ​table primary
   * key ​id whose value is equal to the ​developerId parameter. You can use the
   * owner's user id as the foreign key
   * @param developerId developer id
   * @param website website
   */
  public void createWebsiteForDeveloper(int developerId, Website website) {
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement insertWebsite = connection.prepareStatement(INSERT_WEBSITE);
      insertWebsite.setInt(1, website.getId());
      insertWebsite.setString(2, website.getName());
      insertWebsite.setString(3, website.getDescription());
      insertWebsite.setString(4, website.getCreated());
      insertWebsite.setString(5, website.getUpdated());
      insertWebsite.setInt(6, website.getVisits());
      insertWebsite.setInt(7, developerId);
      insertWebsite.executeUpdate();
      insertWebsite.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
  }

  /**
   * returns all records from ​Website​ table as a ​Collection​ of ​Website​ instances
   * @return website collection
   */
  public Collection<Website> findAllWebsites() {
    Collection<Website> websiteCollection = new ArrayList<>();
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement findAllWebsite = connection.prepareStatement(FIND_ALL_WEBSITE);
      ResultSet resultSet = findAllWebsite.executeQuery();
      while (resultSet.next()) {
        Website website = new Website();
        website.setId(resultSet.getInt("id"));
        website.setName(resultSet.getString("name"));
        website.setDescription(resultSet.getString("description"));
        website.setCreated(resultSet.getString("created"));
        website.setUpdated(resultSet.getString("updated"));
        website.setVisits(resultSet.getInt("visits"));
        website.setDeveloperId(resultSet.getInt("developer_id"));
        websiteCollection.add(website);
      }
      findAllWebsite.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return websiteCollection;
  }

  /**
   * returns all records from ​Website table as a ​Collection of ​Website instances
   * whose ​developerId is equal to the ​developerId​ parameter
   * @param developerId developer id
   * @return website collection
   */
  public Collection<Website> findWebsitesForDeveloper(int developerId) {
    Collection<Website> websiteCollection = new ArrayList<>();
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement findByDeveloper = connection.prepareStatement(FIND_BY_DEVELOPER);
      findByDeveloper.setInt(1, developerId);
      ResultSet resultSet = findByDeveloper.executeQuery();
      while (resultSet.next()) {
        Website website = new Website();
        website.setId(resultSet.getInt("id"));
        website.setName(resultSet.getString("name"));
        website.setDescription(resultSet.getString("description"));
        website.setCreated(resultSet.getString("created"));
        website.setUpdated(resultSet.getString("updated"));
        website.setVisits(resultSet.getInt("visits"));
        website.setDeveloperId(resultSet.getInt("developer_id"));
        websiteCollection.add(website);
      }
      findByDeveloper.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return websiteCollection;
  }

  /**
   * returns a record from ​Website​ table whose ​id​ field is equal to the ​websiteId​
   * parameter
   * @param websiteId website id
   * @return website
   */
  public Website findWebsiteById(int websiteId) {
    Website website = new Website();
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement findById = connection.prepareStatement(FIND_BY_ID);
      findById.setInt(1, websiteId);
      ResultSet resultSet = findById.executeQuery();
      if (resultSet.next()) {
        website.setId(resultSet.getInt("id"));
        website.setName(resultSet.getString("name"));
        website.setDescription(resultSet.getString("description"));
        website.setCreated(resultSet.getString("created"));
        website.setUpdated(resultSet.getString("updated"));
        website.setVisits(resultSet.getInt("visits"));
        website.setDeveloperId(resultSet.getInt("developer_id"));
        findById.close();
        return website;
      }
      else {
        throw new SQLException();
      }
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return website;
  }

  /**
   * updates record in ​Website table whose ​id field is equal to ​websiteId parameter.
   * New record field values are set to the values in the ​website​ instance parameter
   * @param websiteId website id
   * @param website website
   * @return int
   */
  public int updateWebsite(int websiteId, Website website) {
    int res = 0;
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement updateWebsiteById = connection.prepareStatement(UPDATE_BY_ID);
      updateWebsiteById.setString(1, website.getName());
      updateWebsiteById.setString(2, website.getDescription());
      updateWebsiteById.setString(3, website.getUpdated());
      updateWebsiteById.setInt(4, website.getVisits());
      updateWebsiteById.setInt(5, websiteId);
      res = updateWebsiteById.executeUpdate();
      updateWebsiteById.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return res;
  }

  /**
   * deletes record from ​Website​ table whose ​id​ field is equal to ​websiteId​ parameter
   * @param websiteId website id
   * @return int
   */
  public int deleteWebsite(int websiteId) {
    int res = 0;
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement findPageId = connection.prepareStatement(FIND_ALL_PAGE_ID);
      findPageId.setInt(1, websiteId);
      Set<Integer> pageIdSet = new HashSet<>();
      ResultSet resultSet = findPageId.executeQuery();
      while (resultSet.next()) {
        pageIdSet.add(resultSet.getInt("id"));
      }
      findPageId.close();
      if (!pageIdSet.isEmpty()) {
        PreparedStatement deleteByPageId = null;
        for (Integer pageId : pageIdSet) {
          deleteByPageId = connection.prepareStatement(DELETE_PAGE_PRIVILEGE);
          deleteByPageId.setInt(1, pageId);
          res = deleteByPageId.executeUpdate();
          deleteByPageId = connection.prepareStatement(DELETE_PAGE_ROLE);
          deleteByPageId.setInt(1, pageId);
          res = deleteByPageId.executeUpdate();
          deleteByPageId = connection.prepareStatement(DELETE_WIDGET);
          deleteByPageId.setInt(1, pageId);
          res = deleteByPageId.executeUpdate();
        }
        deleteByPageId.close();
      }
      PreparedStatement deleteByWebsiteId = connection.prepareStatement(DELETE_PAGE);
      deleteByWebsiteId.setInt(1, websiteId);
      res = deleteByWebsiteId.executeUpdate();
      deleteByWebsiteId = connection.prepareStatement(DELETE_WEB_PRIVILEGE);
      deleteByWebsiteId.setInt(1, websiteId);
      res = deleteByWebsiteId.executeUpdate();
      deleteByWebsiteId = connection.prepareStatement(DELETE_WEB_ROLE);
      deleteByWebsiteId.setInt(1, websiteId);
      res = deleteByWebsiteId.executeUpdate();
      PreparedStatement deleteWeb = connection.prepareStatement(DELETE_WEBSITE);
      deleteWeb.setInt(1, websiteId);
      res = deleteWeb.executeUpdate();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return res;
  }
}
