package edu.northeastern.cs5200.maps;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import edu.northeastern.cs5200.Connection;

public class RoleIdMap {
  private static RoleIdMap instance = null;

  private Map<String, Integer> map;

  private RoleIdMap() {
    createMap();
  }

  public static RoleIdMap getInstance() {
    if (instance == null) {
      instance = new RoleIdMap();
    }
    return instance;
  }

  private static final String SELECT_ALL_ROLE = "select * from role";

  private void createMap() {
    map = new HashMap<>();
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement selectAllRole = connection.prepareStatement(SELECT_ALL_ROLE);
      ResultSet resultSet = selectAllRole.executeQuery();
      while (resultSet.next()) {
        map.put(resultSet.getString("role"), resultSet.getInt("id"));
      }
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
  }

  public int getRoleId(String role) {
    return map.get(role);
  }
}
