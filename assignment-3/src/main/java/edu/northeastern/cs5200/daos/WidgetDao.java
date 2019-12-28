package edu.northeastern.cs5200.daos;

import java.util.Collection;

import edu.northeastern.cs5200.models.Widget;

public interface WidgetDao {
  /**
   * inserts properties in ​widget instance parameter into the ​Widget table. The widget's ​
   * pageId foreign key refer to ​Page​ ​table primary key ​id​ whose value is equal to the ​
   * pageId​ parameter
   * @param pageId page id
   * @param widget widget
   */
  void createWidgetForPage(int pageId, Widget widget);

  /**
   * returns all records from ​Widget​ table as a ​Collection​ of ​Widget​ instances
   * @return widget collection
   */
  Collection<Widget> findAllWidgets();

  /**
   * returns a record from ​Widget​ table whose ​id​ field is equal to the ​widgetId​ parameter
   * @param widgetId widget id
   * @return widget
   */
  Widget findWidgetById(int widgetId);

  /**
   * returns all records from ​Widget table as a ​Collection of ​Widget instances whose
   * ​pageId is equal to the ​pageId​ parameter
   * @param pageId page id
   * @return widget collection
   */
  Collection<Widget> findWidgetsForPage(int pageId);

  /**
   * updates record in ​Widget table whose ​id field is equal to ​widgetId parameter. New
   * record field values are set to the values in the ​widget​ instance parameter
   * @param widgetId widget id
   * @param widget widget
   * @return int
   */
  int updateWidget(int widgetId, Widget widget);

  /**
   * deletes record from ​Widget​ table whose ​id​ field is equal to ​widgetId​ parameter
   * @param widgetId widget id
   * @return int
   */
  int deleteWidget(int widgetId);
}
