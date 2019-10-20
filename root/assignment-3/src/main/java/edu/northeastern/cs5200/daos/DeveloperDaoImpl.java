package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Phone;

public class DeveloperDaoImpl implements DeveloperDao {
  private static DeveloperDaoImpl instance = null;

  private DeveloperDaoImpl() {
  }

  public static DeveloperDaoImpl getInstance() {
    if (instance == null) {
      instance = new DeveloperDaoImpl();
    }
    return instance;
  }

  private static final String INSERT_PERSON =
          "insert into person(id, firstname, lastname, username, password, email, dob) values"
                  + "(?, ?, ?, ?, ?, ?, ?)";
  private static final String INSERT_DEVELOPER =
          "insert into developer(id, developer_key) values (?, ?)";
  private static final String INSERT_PHONE =
          "insert into phone(phone, `primary`, person_id) values"
                  + "(?, ?, ?)";
  private static final String INSERT_ADDRESS =
          "insert into address(street, city, state, zip, `primary`, person_id) values "
                  + "(?, ?, ?, ?, ?, ?)";

  private static final String SELECT_PHONE_BY_PERSON_ID =
          "select * from phone where person_id=?";
  private static final String SELECT_ADDRESS_BY_PERSON_ID =
          "select * from address where person_id=?";

  private static final String FIND_ALL_DEVELOPER =
          "select * from person join developer on person.id=developer.id";

  private static final String FIND_BY_ID =
          "select * from person join developer on person.id=developer.id where person.id=?";

  private static final String FIND_BY_USERNAME =
          "select * from person join developer on person.id=developer.id where person.username=?";

  private static final String FIND_BY_CREDENTIALS =
          "select * from person join developer on person.id=developer.id "
                  + "where person.username=? and person.password=?";

  private static final String UPDATE_DEVELOPER =
          "update person join developer on person.id=developer.id "
                  + "set person.firstname=?, person.lastname=?, "
                  + "person.username=?, person.password=?, person.email=?, person.dob=?, "
                  + "developer.developer_key=? where person.id=?";

  private static final String DELETE_PHONE_BY_ID = "delete from phone where person_id=?";
  private static final String DELETE_ADDRESS_BY_ID = "delete from address where person_id=?";

  private static final String DELETE_PERSON_BY_ID = "delete from person where id=?";
  private static final String DELETE_DEVELOPER_BY_ID = "delete from developer where id=?";

  /**
   * Inserts properties in ​developer​ instance parameter in tables ​Developer​ and P​ erson
   * @param developer developer
   */
  public void createDeveloper(Developer developer) {
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement insertPerson = connection.prepareStatement(INSERT_PERSON,
              Statement.RETURN_GENERATED_KEYS);
      insertPerson.setInt(1, developer.getId());
      insertPerson.setString(2, developer.getFirstName());
      insertPerson.setString(3, developer.getLastName());
      insertPerson.setString(4, developer.getUsername());
      insertPerson.setString(5, developer.getPassword());
      insertPerson.setString(6, developer.getEmail());
      insertPerson.setString(7, developer.getDob());
      insertPerson.executeUpdate();
      Integer personId = null;
      ResultSet resultSet = insertPerson.getGeneratedKeys();
      while (resultSet.next()) {
        personId = resultSet.getInt(1);
      }
      if (personId == null) {
        throw new SQLException("No generated id");
      }
      PreparedStatement insertDeveloper = connection.prepareStatement(INSERT_DEVELOPER);
      insertDeveloper.setString(1, personId.toString());
      insertDeveloper.setString(2, developer.getDeveloperKey());
      insertDeveloper.executeUpdate();
      insertPerson.close();
      insertDeveloper.close();
      List<Phone> phoneList = developer.getPhones();
      if (phoneList != null && phoneList.size() > 0) {
        PreparedStatement insertPhone = connection.prepareStatement(INSERT_PHONE);
        for (Phone phone : phoneList) {
          insertPhone.setString(1, phone.getNumber());
          insertPhone.setBoolean(2, phone.getPrimary());
          insertPhone.setInt(3, personId);
          insertPhone.executeUpdate();
        }
        insertPhone.close();
      }
      List<Address> addressList = developer.getAddresses();
      if (addressList != null && addressList.size() > 0) {
        PreparedStatement insertAddress = connection.prepareStatement(INSERT_ADDRESS);
        for (Address address : addressList) {
          insertAddress.setString(1, address.getStreet());
          insertAddress.setString(2, address.getCity());
          insertAddress.setString(3, address.getState());
          insertAddress.setString(4, address.getZip());
          insertAddress.setBoolean(5, address.isPrimary());
          insertAddress.setInt(6, personId);
          insertAddress.executeUpdate();
        }
        insertAddress.close();
      }
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
  }

  private List<Phone> getPhoneListByPersonId(int personId) {
    List<Phone> phoneList = new ArrayList<>();
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement findPhones = connection.prepareStatement(SELECT_PHONE_BY_PERSON_ID);
      findPhones.setInt(1, personId);
      ResultSet resultSet = findPhones.executeQuery();
      while (resultSet.next()) {
        phoneList.add(new Phone(resultSet.getString("phone"),
                resultSet.getBoolean("primary")));
      }
      findPhones.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return phoneList;
  }

  private List<Address> getAddressListByPersonId(int personId) {
    List<Address> addressList = new ArrayList<>();
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement findAddresses = connection.prepareStatement(SELECT_ADDRESS_BY_PERSON_ID);
      findAddresses.setInt(1, personId);
      ResultSet resultSet = findAddresses.executeQuery();
      while (resultSet.next()) {
        addressList.add(new Address(resultSet.getString("street"),
                resultSet.getString("city"),
                resultSet.getString("state"),
                resultSet.getString("zip"),
                resultSet.getBoolean("primary")));
      }
      findAddresses.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return addressList;
  }

  /**
   * Returns all joined records from ​Developer and ​Person tables as a
   * ​Collection of ​Developer instances.
   * @return Collection of ​Developer instances
   */
  public Collection<Developer> findAllDevelopers() {
    Collection<Developer> result = new ArrayList<>();
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement findAllDeveloper = connection.prepareStatement(FIND_ALL_DEVELOPER);
      ResultSet resultSet = findAllDeveloper.executeQuery();
      while (resultSet.next()) {
        Developer developer = new Developer();
        developer.setId(resultSet.getInt("id"));
        developer.setFirstName(resultSet.getString("firstname"));
        developer.setLastName(resultSet.getString("lastname"));
        developer.setUsername(resultSet.getString("username"));
        developer.setPassword(resultSet.getString("password"));
        developer.setEmail(resultSet.getString("email"));
        developer.setDob(resultSet.getString("dob"));
        developer.setDeveloperKey(resultSet.getString("developer_key"));
        developer.setPhones(getPhoneListByPersonId(developer.getId()));
        developer.setAddresses(getAddressListByPersonId(developer.getId()));
        result.add(developer);
      }
      findAllDeveloper.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return result;
  }

  /**
   * returns a joined record from ​Developer and ​Person tables whose ​id
   * field is equal to the ​developerId parameter
   * @param id developer id
   * @return developer with given id
   */
  public Developer findDeveloperById(int id) {
    Developer developer = new Developer();
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement findById = connection.prepareStatement(FIND_BY_ID);
      findById.setString(1, Integer.toString(id));
      ResultSet resultSet = findById.executeQuery();
      if (resultSet.next()) {
        developer.setId(resultSet.getInt("id"));
        developer.setFirstName(resultSet.getString("firstname"));
        developer.setLastName(resultSet.getString("lastname"));
        developer.setUsername(resultSet.getString("username"));
        developer.setPassword(resultSet.getString("password"));
        developer.setEmail(resultSet.getString("email"));
        developer.setDob(resultSet.getString("dob"));
        developer.setDeveloperKey(resultSet.getString("developer_key"));
        developer.setPhones(getPhoneListByPersonId(developer.getId()));
        developer.setAddresses(getAddressListByPersonId(developer.getId()));
        findById.close();
        return developer;
      }
      else {
        throw new SQLException();
      }
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return developer;
  }

  /**
   * returns a joined record from ​Developer and ​Person tables whose ​username
   * field matches the parameter.
   * @param username username
   * @return developer with given username
   */
  public Developer findDeveloperByUsername(String username) {
    Developer developer = new Developer();
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement findByUsername = connection.prepareStatement(FIND_BY_USERNAME);
      findByUsername.setString(1, username);
      ResultSet resultSet = findByUsername.executeQuery();
      if (resultSet.next()) {
        developer.setId(resultSet.getInt("id"));
        developer.setFirstName(resultSet.getString("firstname"));
        developer.setLastName(resultSet.getString("lastname"));
        developer.setUsername(resultSet.getString("username"));
        developer.setPassword(resultSet.getString("password"));
        developer.setEmail(resultSet.getString("email"));
        developer.setDob(resultSet.getString("dob"));
        developer.setDeveloperKey(resultSet.getString("developer_key"));
        developer.setPhones(getPhoneListByPersonId(developer.getId()));
        developer.setAddresses(getAddressListByPersonId(developer.getId()));
        findByUsername.close();
        return developer;
      }
      else {
        throw new SQLException();
      }
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return developer;
  }

  /**
   * returns a joined record from ​Developer and ​Person tables whose ​username
   * and ​password fields match the parameters
   * @param username username
   * @param password password
   * @return developer with given username and password
   */
  public Developer findDeveloperByCredentials(String username, String password) {
    Developer developer = new Developer();
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement findByCredentials = connection.prepareStatement(FIND_BY_CREDENTIALS);
      findByCredentials.setString(1, username);
      findByCredentials.setString(2, password);
      ResultSet resultSet = findByCredentials.executeQuery();
      if (resultSet.next()) {
        developer.setId(resultSet.getInt("id"));
        developer.setFirstName(resultSet.getString("firstname"));
        developer.setLastName(resultSet.getString("lastname"));
        developer.setUsername(resultSet.getString("username"));
        developer.setPassword(resultSet.getString("password"));
        developer.setEmail(resultSet.getString("email"));
        developer.setDob(resultSet.getString("dob"));
        developer.setDeveloperKey(resultSet.getString("developer_key"));
        developer.setPhones(getPhoneListByPersonId(developer.getId()));
        developer.setAddresses(getAddressListByPersonId(developer.getId()));
        findByCredentials.close();
        return developer;
      }
      else {
        throw new SQLException();
      }
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return developer;
  }

  /**
   * updates records in ​Developer and ​Person tables whose ​id field is equal
   * to ​developerId parameter. New record field values are set to the values
   * in the ​developer​ instance parameter.
   * @param developerId developer id
   * @param developer developer
   * @return int
   */
  public int updateDeveloper(int developerId, Developer developer) {
    int res = 0;
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement updateById = connection.prepareStatement(UPDATE_DEVELOPER);
      updateById.setString(1, developer.getFirstName());
      updateById.setString(2, developer.getLastName());
      updateById.setString(3, developer.getUsername());
      updateById.setString(4, developer.getPassword());
      updateById.setString(5, developer.getEmail());
      updateById.setString(6, developer.getDob());
      updateById.setString(7, developer.getDeveloperKey());
      updateById.setString(8, Integer.toString(developerId));
      res = updateById.executeUpdate();
      updateById.close();
      PreparedStatement deletePhone = connection.prepareStatement(DELETE_PHONE_BY_ID);
      deletePhone.setInt(1, developerId);
      deletePhone.executeUpdate();
      deletePhone.close();
      PreparedStatement deleteAddress = connection.prepareStatement(DELETE_ADDRESS_BY_ID);
      deleteAddress.setInt(1, developerId);
      deleteAddress.executeUpdate();
      deleteAddress.close();
      List<Phone> phoneList = developer.getPhones();
      if (phoneList != null || phoneList.size() > 0) {
        PreparedStatement insertPhone = connection.prepareStatement(INSERT_PHONE);
        for (Phone phone : phoneList) {
          insertPhone.setString(1, phone.getNumber());
          insertPhone.setBoolean(2, phone.getPrimary());
          insertPhone.setInt(3, developerId);
          insertPhone.executeUpdate();
        }
        insertPhone.close();
      }
      List<Address> addressList = developer.getAddresses();
      if (addressList != null || addressList.size() > 0) {
        PreparedStatement insertAddress = connection.prepareStatement(INSERT_ADDRESS);
        for (Address address : addressList) {
          insertAddress.setString(1, address.getStreet());
          insertAddress.setString(2, address.getCity());
          insertAddress.setString(3, address.getState());
          insertAddress.setString(4, address.getZip());
          insertAddress.setBoolean(5, address.isPrimary());
          insertAddress.setInt(6, developerId);
          insertAddress.executeUpdate();
        }
        insertAddress.close();
      }
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return res;
  }

  /**
   * deletes records from ​Developer and ​Person tables whose ​id field is equal
   * to ​developerId parameter. Do not make any modifications to the instance
   * provided.
   * @param developerId developer id
   * @return int
   */
  public int deleteDeveloper(int developerId) {
    int res = 0;
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement deletePhone = connection.prepareStatement(DELETE_PHONE_BY_ID);
      deletePhone.setInt(1, developerId);
      deletePhone.executeUpdate();
      deletePhone.close();
      PreparedStatement deleteAddress = connection.prepareStatement(DELETE_ADDRESS_BY_ID);
      deleteAddress.setInt(1, developerId);
      deleteAddress.executeUpdate();
      deleteAddress.close();
      PreparedStatement deleteDeveloper = connection.prepareStatement(DELETE_DEVELOPER_BY_ID);
      deleteDeveloper.setInt(1, developerId);
      res = deleteDeveloper.executeUpdate();
      PreparedStatement deletePerson = connection.prepareStatement(DELETE_PERSON_BY_ID);
      deletePerson.setInt(1, developerId);
      res = deletePerson.executeUpdate();
      deleteDeveloper.close();
      deletePerson.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return res;
  }
}
