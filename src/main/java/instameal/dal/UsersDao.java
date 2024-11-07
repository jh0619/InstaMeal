/**
 * Created by Shiang Jin, Chin
 */
package instameal.dal;

import instameal.dal.ConnectionManager;
import instameal.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {
  protected instameal.dal.ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static UsersDao instance = null;
  protected UsersDao() {
    connectionManager = new ConnectionManager();
  }
  public static UsersDao getInstance() {
    if(instance == null) {
      instance = new UsersDao();
    }
    return instance;
  }

  /**
   * Save the Users instance by storing it in MySQL instance.
   * This runs a INSERT statement.
   */
  public Users create(Users user) throws SQLException {
    String insertStr = "INSERT INTO Users(UserName,PassWord,FirstName,LastName, Email) VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertStr, Statement.RETURN_GENERATED_KEYS);
      // PreparedStatement allows us to substitute specific types into the query template.
      // For an overview, see:
      // http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
      // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
      // For nullable fields, you can check the property first and then call setNull()
      // as applicable.
      insertStmt.setString(1, user.getUserName());
      insertStmt.setString(2, user.getPassWord());
      insertStmt.setString(3, user.getFirstName());
      insertStmt.setString(4, user.getLastName());
      insertStmt.setString(5, user.getEmail());

      // Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
      // statements, and it returns an int for the row counts affected (or 0 if the
      // statement returns nothing). For more information, see:
      // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
      // I'll leave it as an exercise for you to write UPDATE/DELETE methods.
      insertStmt.executeUpdate();
   // Retrieve the auto-generated key and set it, so it can be used by the caller.
      resultKey = insertStmt.getGeneratedKeys();
      int userId = -1;
      if(resultKey.next()) {
        userId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      user.setUserId(userId);
      return user;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(insertStmt != null) {
        insertStmt.close();
      }
    }
  }
  
  /**
   * Get the Users record by fetching it from the MySQL instance.
   * This runs a SELECT statement and returns a Users instance.
   */
  public Users getUserByUserId(int userId) throws SQLException {
    String selectUser = "SELECT UserId, UserName,Password,FirstName,LastName, Email FROM Users WHERE UserId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectUser);
      selectStmt.setInt(1, userId);
      results = selectStmt.executeQuery();
      // You can iterate the result set (although the example below only retrieves
      // the first record). The cursor is initially positioned before the row.
      // Furthermore, you can retrieve fields by name and by type.
      if(results.next()) {
    	int retrieveUserId = results.getInt("UserId");
        String resultUserName = results.getString("UserName");
        String password = results.getString("Password");
        String firstName = results.getString("FirstName");
        String lastName = results.getString("LastName");
        String email = results.getString("Email");
        Users user = new Users(retrieveUserId, resultUserName, password, firstName, lastName, email);
        return user;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return null;
  }

  /**
   * Get the Users record by fetching it from the MySQL instance.
   * This runs a SELECT statement and returns a list of Users instance.
   */
  public List<Users> getUserByUserName(String userName) throws SQLException {
    String selectUser = "SELECT UserId, UserName,Password,FirstName,LastName, Email FROM Users WHERE UserName=?;";
    List<Users> userList = new ArrayList<>();
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectUser);
      selectStmt.setString(1, userName);
      results = selectStmt.executeQuery();
      // You can iterate the result set (although the example below only retrieves
      // the first record). The cursor is initially positioned before the row.
      // Furthermore, you can retrieve fields by name and by type.
      while(results.next()) {
    	int userId = results.getInt("UserId");
        String resultUserName = results.getString("UserName");
        String password = results.getString("Password");
        String firstName = results.getString("FirstName");
        String lastName = results.getString("LastName");
        String email = results.getString("Email");
        Users user = new Users(userId, resultUserName, password, firstName, lastName, email);
        userList.add(user);
      }
      return userList;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
  }

  /**
   * Update the User record
   * This runs an UPDATE statement
   */
  public Users updateUser(Users user) throws SQLException {
    String updateStr= "UPDATE Users SET UserName=?, PassWord=?, FirstName=?, LastName=?, EMAIL=?"
    		+ " WHERE UserId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateStr);
      updateStmt.setString(1, user.getUserName());
      updateStmt.setString(2, user.getPassWord());
      updateStmt.setString(3, user.getFirstName());
      updateStmt.setString(4, user.getLastName());
      updateStmt.setString(5, user.getEmail());
      updateStmt.setInt(6, user.getUserId());
      updateStmt.executeUpdate();
      // return the user
      return user;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(updateStmt != null) {
        updateStmt.close();
      }
    }
  }
  
  /**
   * Delete the Users instance.
   * This runs a DELETE statement.
   */
  public Users delete(Users user) throws SQLException {
    String deleteUser = "DELETE FROM Users WHERE UserId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteUser);
      // userId is unique(primary key in Users table), so delete by UserId
      deleteStmt.setInt(1, user.getUserId());
      deleteStmt.executeUpdate();

      // Return null so the caller can no longer operate on the Users instance.
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }
}
