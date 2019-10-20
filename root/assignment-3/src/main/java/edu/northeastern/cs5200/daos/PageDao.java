package edu.northeastern.cs5200.daos;

import java.util.Collection;

import edu.northeastern.cs5200.models.Page;

public interface PageDao {
  /**
   * inserts properties in ​page instance parameter into the ​Page table. The page's
   * ​websiteId foreign key refer to ​Website ​table primary key ​id​ whose value is equal
   * to the ​websiteId​ parameter
   * @param websiteId website id
   * @param page page
   */
  void createPageForWebsite(int websiteId, Page page);

  /**
   * returns all records from ​Page​ table as a ​Collection​ of ​Page​ instances
   * @return page collection
   */
  Collection<Page> findAllPages();

  /**
   * returns a record from ​Page​ table whose ​id​ field is equal to the ​pageId​ parameter
   * @param pageId page id
   * @return page
   */
  Page findPageById(int pageId);

  /**
   * returns all records from ​Page table as a ​Collection of ​Page instances whose ​websiteId
   * is equal to the websiteId​ parameter
   * @param websiteId website id
   * @return page collection
   */
  Collection<Page> findPagesForWebsite(int websiteId);

  /**
   * updates record in ​Page table whose ​id field is equal to ​pageId parameter. New record
   * field values are set to the values in the ​page​ instance parameter
   * @param pageId page id
   * @param page page
   * @return int
   */
  int updatePage(int pageId, Page page);

  /**
   * deletes record from ​Page​ table whose ​id​ field is equal to ​pageId​ parameter
   * @param pageId page id
   * @return int
   */
  int deletePage(int pageId);
}
