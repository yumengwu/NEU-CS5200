package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.HeadingWidget;
import edu.northeastern.cs5200.models.HtmlWidget;
import edu.northeastern.cs5200.models.ImageWidget;
import edu.northeastern.cs5200.models.Widget;
import edu.northeastern.cs5200.models.WidgetType;
import edu.northeastern.cs5200.models.YouTubeWidget;

public class WidgetDaoImpl implements WidgetDao {
  private static WidgetDaoImpl instance = null;

  private WidgetDaoImpl() {
  }

  public static WidgetDaoImpl getInstance() {
    if (instance == null) {
      instance = new WidgetDaoImpl();
    }
    return instance;
  }

  private static final String INSERT_WIDGET =
          "insert into widget(" +
                  "`name`, `dtype`, `width`, `height`, `css_class`, `css_style`, "
                  + "`page_id`, `order`, `text`, `size`, `html`, `src`, `url`, "
                  + "`shareable`, `expandable`) values "
                  + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

  private static final String FIND_ALL_WIDGETS =
          "select * from widget";

  private static final String FIND_WIDGET_BY_ID =
          "select * from widget where id=?";

  private static final String FIND_WIDGET_BY_PAGE_ID =
          "select * from widget where page_id=?";

  private static final String UPDATE_WIDGET =
          "update widget set "
                  + "`name`=?, `width`=?, `height`=?, `css_class`=?, `css_style`=?, `page_id`=?, "
                  + "`order`=?, `text`=?, `size`=?, `html`=?, `src`=?, `url`=?, "
                  + "`shareable`=?, `expandable`=? "
                  + "where id=?";

  private static final String DELETE_BY_ID =
          "delete from widget where id=?";

  /**
   * inserts properties in ​widget instance parameter into the ​Widget table. The widget's ​
   * pageId foreign key refer to ​Page​ ​table primary key ​id​ whose value is equal to the ​
   * pageId​ parameter
   * @param pageId page id
   * @param widget widget
   */
  public void createWidgetForPage(int pageId, Widget widget) {
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement createWidget = connection.prepareStatement(INSERT_WIDGET);
      createWidget.setString(1, widget.getName());
      switch (widget.getDtype()) {
        case HEADING:
          createWidget.setString(2, "heading");
          break;
        case HTML:
          createWidget.setString(2, "html");
          break;
        case IMAGE:
          createWidget.setString(2, "image");
          break;
        case YOUTUBE:
          createWidget.setString(2, "youtube");
          break;
      }
      createWidget.setInt(3, widget.getWidth());
      createWidget.setInt(4, widget.getHeight());
      createWidget.setString(5, widget.getCssClass());
      createWidget.setString(6, widget.getCssStyle());
      createWidget.setInt(7, pageId);
      createWidget.setInt(8, widget.getOrder());
      createWidget.setString(9, widget.getText());
      createWidget.setInt(10, widget.getSize());
      createWidget.setString(11, widget.getHtml());
      createWidget.setString(12, widget.getSrc());
      createWidget.setString(13, widget.getUrl());
      createWidget.setBoolean(14, widget.getShareable());
      createWidget.setBoolean(15, widget.getExpandable());
      createWidget.executeUpdate();
      createWidget.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
  }

  private PreparedStatement updateBasicWidget(java.sql.Connection connection,
                                              int widgetId, Widget widget) throws SQLException {
    PreparedStatement basicWidget = connection.prepareStatement(UPDATE_WIDGET);
    basicWidget.setString(1, widget.getName());
    basicWidget.setInt(2, widget.getWidth());
    basicWidget.setInt(3, widget.getHeight());
    basicWidget.setString(4, widget.getCssClass());
    basicWidget.setString(5, widget.getCssStyle());
    basicWidget.setInt(6, widget.getPageId());
    basicWidget.setInt(7, widget.getOrder());
    basicWidget.setString(8, widget.getText());
    basicWidget.setInt(9, 0);
    basicWidget.setString(10, null);
    basicWidget.setString(11, null);
    basicWidget.setString(12, null);
    basicWidget.setBoolean(13, true);
    basicWidget.setBoolean(14, true);
    basicWidget.setInt(15, widgetId);
    return basicWidget;
  }

  private Widget getWidgetFromResultSet(ResultSet resultSet) throws SQLException {
    Widget widget = new Widget();
    widget.setId(resultSet.getInt("id"));
    widget.setName(resultSet.getString("name"));
    widget.setWidth(resultSet.getInt("width"));
    widget.setHeight(resultSet.getInt("height"));
    widget.setCssClass(resultSet.getString("css_class"));
    widget.setCssStyle(resultSet.getString("css_style"));
    widget.setPageId(resultSet.getInt("page_id"));
    widget.setOrder(resultSet.getInt("order"));
    widget.setText(resultSet.getString("text"));
    switch (resultSet.getString("dtype")) {
      case "heading":
        widget.setDtype(WidgetType.HEADING);
        widget.setSize(resultSet.getInt("size"));
        break;
      case "html":
        widget.setDtype(WidgetType.HTML);
        widget.setHtml(resultSet.getString("html"));
        break;
      case "image":
        widget.setDtype(WidgetType.IMAGE);
        widget.setSrc(resultSet.getString("src"));
        break;
      case "youtube":
        widget.setDtype(WidgetType.YOUTUBE);
        widget.setUrl(resultSet.getString("url"));
        widget.setShareable(resultSet.getBoolean("shareable"));
        widget.setExpandable(resultSet.getBoolean("expandable"));
        break;
    }
    return widget;
  }

  /**
   * returns all records from ​Widget​ table as a ​Collection​ of ​Widget​ instances
   * @return widget collection
   */
  public Collection<Widget> findAllWidgets() {
    Collection<Widget> widgetCollection = new ArrayList<>();
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement findAll = connection.prepareStatement(FIND_ALL_WIDGETS);
      ResultSet resultSet = findAll.executeQuery();
      while (resultSet.next()) {
        widgetCollection.add(getWidgetFromResultSet(resultSet));
      }
      findAll.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return widgetCollection;
  }

  /**
   * returns a record from ​Widget​ table whose ​id​ field is equal to the ​widgetId​ parameter
   * @param widgetId widget id
   * @return widget
   */
  public Widget findWidgetById(int widgetId) {
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement findById = connection.prepareStatement(FIND_WIDGET_BY_ID);
      findById.setInt(1, widgetId);
      ResultSet resultSet = findById.executeQuery();
      Widget widget = null;
      if (resultSet.next()) {
        widget = getWidgetFromResultSet(resultSet);
      }
      else {
        throw new SQLException();
      }
      findById.close();
      return widget;
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return null;
  }

  /**
   * returns all records from ​Widget table as a ​Collection of ​Widget instances whose
   * ​pageId is equal to the ​pageId​ parameter
   * @param pageId page id
   * @return widget collection
   */
  public Collection<Widget> findWidgetsForPage(int pageId) {
    Collection<Widget> widgetCollection = new ArrayList<>();
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement findByPage = connection.prepareStatement(FIND_WIDGET_BY_PAGE_ID);
      findByPage.setInt(1, pageId);
      ResultSet resultSet = findByPage.executeQuery();
      while (resultSet.next()) {
        widgetCollection.add(getWidgetFromResultSet(resultSet));
      }
      findByPage.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return widgetCollection;
  }

  /**
   * updates record in ​Widget table whose ​id field is equal to ​widgetId parameter. New
   * record field values are set to the values in the ​widget​ instance parameter
   * @param widgetId widget id
   * @param widget widget
   * @return int
   */
  public int updateWidget(int widgetId, Widget widget) {
    int res = 0;
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement updateWidget = updateBasicWidget(connection, widgetId, widget);
      switch (widget.getDtype()) {
        case HEADING:
          updateWidget.setInt(9, widget.getSize());
          break;
        case HTML:
          updateWidget.setString(10, widget.getHtml());
          break;
        case IMAGE:
          updateWidget.setString(11, widget.getSrc());
          break;
        case YOUTUBE:
          updateWidget.setString(12, widget.getUrl());
          updateWidget.setBoolean(13, widget.getShareable());
          updateWidget.setBoolean(14, widget.getExpandable());
          break;
      }
      res = updateWidget.executeUpdate();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return res;
  }

  /**
   * deletes record from ​Widget​ table whose ​id​ field is equal to ​widgetId​ parameter
   * @param widgetId widget id
   * @return int
   */
  public int deleteWidget(int widgetId) {
    int res = 0;
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement deleteById = connection.prepareStatement(DELETE_BY_ID);
      deleteById.setInt(1, widgetId);
      res = deleteById.executeUpdate();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return res;
  }
}
