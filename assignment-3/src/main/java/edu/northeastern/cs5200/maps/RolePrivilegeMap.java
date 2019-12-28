package edu.northeastern.cs5200.maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RolePrivilegeMap {
  private static RolePrivilegeMap instance = null;

  private Map<String, List<String>> map;

  private RolePrivilegeMap() {
    map = new HashMap<>();
    List<String> list = new ArrayList<>();
    list.add("create");
    list.add("read");
    list.add("update");
    list.add("delete");
    map.put("owner", list);
    map.put("admin", list);
    list = new ArrayList<>();
    list.add("create");
    list.add("read");
    list.add("update");
    map.put("writer", list);
    list = new ArrayList<>();
    list.add("read");
    list.add("update");
    map.put("editor", list);
    list = new ArrayList<>();
    list.add("read");
    map.put("reviewer", list);
  }

  public static RolePrivilegeMap getInstance() {
    if (instance == null) {
      instance = new RolePrivilegeMap();
    }
    return instance;
  }

  public List<String> getPrivilege(String role) {
    return map.get(role);
  }
}
