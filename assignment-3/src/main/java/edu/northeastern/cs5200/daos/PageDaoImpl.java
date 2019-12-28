package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Page;

public class PageDaoImpl implements PageDao {
  private static PageDaoImpl instance = null;

  private PageDaoImpl() {
  }

  public static PageDaoImpl getInstance() {
    if (instance == null) {
      instance = new PageDaoImpl();
    }
    return instance;
  }

  private static final String INSERT_PAGE =
          "insert into page(id, title, description, created, updated, views, website_id) values "
                  + "(?, ?, ?, ?, ?, ?, ?)";

  private static final String FIND_ALL_PAGE =
          "select * from page";

  private static final String FIND_BY_ID =
          "select * from page where id=?";

  private static final String FIND_BY_WEBSITE =
          "select * from page where website_id=?";

  private static final String UPDATE_PAGE =
          "update page set title=?, description=?, updated=?, views=?, website_id=? where id=?";

  private static final String DELETE_PAGE_PRIVILEGE =
          "delete from page_privilege where page_id=?";
  private static final String DELETE_PAGE_ROLE =
          "delete from page_role where page_id=?";
  private static final String DELETE_WIDGET =
          "delete from widget where page_id=?";
  private static final String DELETE_PAGE =
          "delete from page where id=?";

  /**
   * inserts properties in ​page instance parameter into the ​Page table. The page's
   * ​websiteId foreign key refer to ​Website ​table primary key ​id​ whose value is equal
   * to the ​websiteId​ parameter
   * @param websiteId website id
   * @param page page
   */
  public void createPageForWebsite(int websiteId, Page page) {
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement insertPage = connection.prepareStatement(INSERT_PAGE);
      insertPage.setInt(1, page.getId());
      insertPage.setString(2, page.getTitle());
      insertPage.setString(3, page.getDescription());
      insertPage.setString(4, page.getCreated());
      insertPage.setString(5, page.getUpdated());
      insertPage.setInt(6, page.getViews());
      insertPage.setInt(7, websiteId);
      insertPage.executeUpdate();
      insertPage.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
  }

  /**
   * returns all records from ​Page​ table as a ​Collection​ of ​Page​ instances
   * @return page collection
   */
  public Collection<Page> findAllPages() {
    Collection<Page> pageCollection = new ArrayList<>();
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement findAll = connection.prepareStatement(FIND_ALL_PAGE);
      ResultSet resultSet = findAll.executeQuery();
      while (resultSet.next()) {
        Page page = new Page();
        page.setId(resultSet.getInt("id"));
        page.setTitle(resultSet.getString("title"));
        page.setDescription(resultSet.getString("description"));
        page.setCreated(resultSet.getString("created"));
        page.setUpdated(resultSet.getString("updated"));
        page.setViews(resultSet.getInt("views"));
        page.setWebsiteId(resultSet.getInt("website_id"));
        pageCollection.add(page);
      }
      findAll.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return pageCollection;
  }

  /**
   * returns a record from ​Page​ table whose ​id​ field is equal to the ​pageId​ parameter
   * @param pageId page id
   * @return page
   */
  public Page findPageById(int pageId) {
    Page page = new Page();
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement findById = connection.prepareStatement(FIND_BY_ID);
      findById.setInt(1, pageId);
      ResultSet resultSet = findById.executeQuery();
      if (resultSet.next()) {
        page.setId(resultSet.getInt("id"));
        page.setTitle(resultSet.getString("title"));
        page.setDescription(resultSet.getString("description"));
        page.setCreated(resultSet.getString("created"));
        page.setUpdated(resultSet.getString("updated"));
        page.setViews(resultSet.getInt("views"));
        page.setWebsiteId(resultSet.getInt("website_id"));
        findById.close();
        return page;
      }
      else {
        throw new SQLException();
      }
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return page;
  }

  /**
   * returns all records from ​Page table as a ​Collection of ​Page instances whose ​websiteId
   * is equal to the websiteId​ parameter
   * @param websiteId website id
   * @return page collection
   */
  public Collection<Page> findPagesForWebsite(int websiteId) {
    Collection<Page> pageCollection = new ArrayList<>();
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement findByWebsite = connection.prepareStatement(FIND_BY_WEBSITE);
      findByWebsite.setInt(1, websiteId);
      ResultSet resultSet = findByWebsite.executeQuery();
      while (resultSet.next()) {
        Page page = new Page();
        page.setId(resultSet.getInt("id"));
        page.setTitle(resultSet.getString("title"));
        page.setDescription(resultSet.getString("description"));
        page.setCreated(resultSet.getString("created"));
        page.setUpdated(resultSet.getString("updated"));
        page.setViews(resultSet.getInt("views"));
        page.setWebsiteId(resultSet.getInt("website_id"));
        pageCollection.add(page);
      }
      findByWebsite.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return pageCollection;
  }

  /**
   * updates record in ​Page table whose ​id field is equal to ​pageId parameter. New record
   * field values are set to the values in the ​page​ instance parameter
   * @param pageId page id
   * @param page page
   * @return int
   */
  public int updatePage(int pageId, Page page) {
    int res = 0;
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement updateById = connection.prepareStatement(UPDATE_PAGE);
      updateById.setString(1, page.getTitle());
      updateById.setString(2, page.getDescription());
      updateById.setString(3, page.getUpdated());
      updateById.setInt(4, page.getViews());
      updateById.setInt(5, page.getWebsiteId());
      updateById.setInt(6, pageId);
      res = updateById.executeUpdate();
      updateById.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return res;
  }

  /**
   * deletes record from ​Page​ table whose ​id​ field is equal to ​pageId​ parameter
   * @param pageId page id
   * @return int
   */
  public int deletePage(int pageId) {
    int res = 0;
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement deletePageById = connection.prepareStatement(DELETE_PAGE_PRIVILEGE);
      deletePageById.setInt(1, pageId);
      res = deletePageById.executeUpdate();
      deletePageById = connection.prepareStatement(DELETE_PAGE_ROLE);
      deletePageById.setInt(1, pageId);
      res = deletePageById.executeUpdate();
      deletePageById = connection.prepareStatement(DELETE_WIDGET);
      deletePageById.setInt(1, pageId);
      res = deletePageById.executeUpdate();
      deletePageById = connection.prepareStatement(DELETE_PAGE);
      deletePageById.setInt(1, pageId);
      res = deletePageById.executeUpdate();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return res;
  }
}
