package edu.northeastern.cs5200;

import java.sql.SQLException;
import java.sql.DriverManager;

public class Connection {
  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String URL = "jdbc:mysql://cs5200-fall2019-yumengwu.c9vyxq2ayf8q.us-east-1.rds.amazonaws.com/cs5200_wu_yumeng_assignment3";
  private static final String USER = "admin";
  private static final String PASSWORD = "Wym_123456";

  private static java.sql.Connection dbConnection = null;

  public static java.sql.Connection getConnection() throws ClassNotFoundException, SQLException {
    Class.forName(DRIVER);
    if (dbConnection == null) {
      dbConnection = DriverManager.getConnection(URL, USER, PASSWORD);
      return dbConnection;
    }
    else {
      return dbConnection;
    }
  }

  public static void closeConnection() {
    try {
      if (dbConnection != null) {
        dbConnection.close();
        dbConnection = null;
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
