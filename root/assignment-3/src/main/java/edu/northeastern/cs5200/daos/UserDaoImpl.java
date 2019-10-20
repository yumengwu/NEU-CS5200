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
import edu.northeastern.cs5200.models.User;

public class UserDaoImpl implements UserDao {
  private static UserDaoImpl instance = null;

  private UserDaoImpl() {
  }

  public static UserDaoImpl getInstance() {
    if (instance == null) {
      instance = new UserDaoImpl();
    }
    return instance;
  }

  private static final String INSERT_PERSON =
          "insert into person(id, firstname, lastname, username, password, email, dob) values"
                  + "(?, ?, ?, ?, ?, ?, ?)";
  private static final String INSERT_USER =
          "insert into user(id, user_agreement, user_key) values (?, ?, ?)";
  private static final String INSERT_PHONE =
          "insert into phone(phone, `primary`, person_id) values"
                  + "(?, ?, ?)";
  private static final String INSERT_ADDRESS =
          "insert into address(street, city, state, zip, `primary`, person_id) values "
                  + "(?, ?, ?, ?, ?, ?)";

  private static final String FIND_ALL_USER =
          "select * from person join user on person.id=user.id";

  /**
   * Inserts properties in ​user​ instance parameter in tables ​user​ and person
   * @param user user
   */
  public void createUser(User user) {
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement insertPerson =
              connection.prepareStatement(INSERT_PERSON, Statement.RETURN_GENERATED_KEYS);
      insertPerson.setInt(1, user.getId());
      insertPerson.setString(2, user.getFirstName());
      insertPerson.setString(3, user.getLastName());
      insertPerson.setString(4, user.getUsername());
      insertPerson.setString(5, user.getPassword());
      insertPerson.setString(6, user.getEmail());
      insertPerson.setString(7, user.getDob());
      insertPerson.executeUpdate();
      Integer personId = null;
      ResultSet resultSet = insertPerson.getGeneratedKeys();
      while (resultSet.next()) {
        personId = resultSet.getInt(1);
      }
      if (personId == null) {
        throw new SQLException("No generated id");
      }
      insertPerson.close();
      PreparedStatement insertUser = connection.prepareStatement(INSERT_USER);
      insertUser.setInt(1, personId);
      insertUser.setBoolean(2, user.isUserAgreement());
      insertUser.setString(3, user.getUserKey());
      insertUser.executeUpdate();
      insertUser.close();
      List<Phone> phoneList = user.getPhones();
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
      List<Address> addressList = user.getAddresses();
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

  /**
   * Returns all joined records from ​Developer and ​Person tables as a
   * ​Collection of ​Developer instances.
   * @return Collection of ​Developer instances
   */
  public Collection<User> findAllUsers() {
    Collection<User> result = new ArrayList<>();
    java.sql.Connection connection = null;
    try {
      connection = Connection.getConnection();
      PreparedStatement findAllUser = connection.prepareStatement(FIND_ALL_USER);
      ResultSet resultSet = findAllUser.executeQuery();
      while (resultSet.next()) {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setFirstName(resultSet.getString("firstname"));
        user.setLastName(resultSet.getString("lastname"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setDob(resultSet.getString("dob"));
        user.setUserKey(resultSet.getString("user_key"));
        user.setUserAgreement(resultSet.getBoolean("user_agreement"));
        result.add(user);
      }
      findAllUser.close();
    }
    catch (ClassNotFoundException | SQLException err) {
      err.printStackTrace();
    }
    return result;
  }
}
