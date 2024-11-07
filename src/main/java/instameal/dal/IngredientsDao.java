/**
 * Created by Shiang Jin, Chin
 */
package instameal.dal;

import instameal.dal.ConnectionManager;
import instameal.model.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IngredientsDao {
  protected instameal.dal.ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static IngredientsDao instance = null;
  protected IngredientsDao() {
    connectionManager = new ConnectionManager();
  }
  public static IngredientsDao getInstance() {
    if(instance == null) {
      instance = new IngredientsDao();
    }
    return instance;
  }
  
  /**
   * Save the Ingredients instance by storing it in MySQL instance.
   * This runs a INSERT statement.
   */
  public Ingredients create(Ingredients ingredient) throws SQLException {
    String insertStr = "INSERT INTO Ingredients(IngredientName, AliasName, FatPerG, "
    		+ "SugarPerG, SodiumPerG, ProteinPerG, SaturatedFatPerG) VALUES(?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertStr);
      // PreparedStatement allows us to substitute specific types into the query template.
      // For an overview, see:
      // http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
      // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
      // For nullable fields, you can check the property first and then call setNull()
      // as applicable.
      insertStmt.setString(1, ingredient.getIngredientName());
      insertStmt.setString(2, ingredient.getAliasName());
      insertStmt.setBigDecimal(3, ingredient.getFatPerG());
      insertStmt.setBigDecimal(4, ingredient.getSugarPerG());
      insertStmt.setBigDecimal(5, ingredient.getSodiumPerG());
      insertStmt.setBigDecimal(6, ingredient.getProteinPerG());
      insertStmt.setBigDecimal(7, ingredient.getSaturatedFatPerG());

      // Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
      // statements, and it returns an int for the row counts affected (or 0 if the
      // statement returns nothing). For more information, see:
      // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
      // I'll leave it as an exercise for you to write UPDATE/DELETE methods.
      insertStmt.executeUpdate();
      return ingredient;
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
   * Get the Ingredients record by fetching it from the MySQL instance.
   * This runs a SELECT statement and returns a Ingredient instance.
   */
  public Ingredients getIngredientByIngredientName(String ingredientName) throws SQLException {
    String selectUser = "SELECT IngredientName, IFNULL(AliasName, IngredientName) AS AliasName,"
    		+ "IFNULL(FatPerG, 0.0) AS FatPerG, IFNULL(SugarPerG, 0.0) AS SugarPerG, "
    		+ "IFNULL(SodiumPerG, 0.0) AS SodiumPerG, IFNULL(ProteinPerG, 0.0) AS ProteinPerG, "
    		+ "IFNULL(SaturatedFatPerG, 0.0) AS SaturatedFatPerG "
    		+ "FROM Ingredients WHERE IngredientName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectUser);
      selectStmt.setString(1, ingredientName);
      results = selectStmt.executeQuery();
      // You can iterate the result set (although the example below only retrieves
      // the first record). The cursor is initially positioned before the row.
      // Furthermore, you can retrieve fields by name and by type.
      if(results.next()) {
    	String retrieveIngredientName = results.getString("IngredientName");
    	String aliasName = results.getString("AliasName");
        BigDecimal fatPerG = results.getBigDecimal("FatPerG");
        BigDecimal sugarPerG = results.getBigDecimal("SugarPerG");
        BigDecimal sodiumPerG = results.getBigDecimal("SodiumPerG");
        BigDecimal proteinPerG = results.getBigDecimal("ProteinPerG");
        BigDecimal saturatedFatPerG = results.getBigDecimal("SaturatedFatPerG");
        Ingredients ingredient = new Ingredients(retrieveIngredientName, aliasName, fatPerG, 
        		sugarPerG, sodiumPerG, proteinPerG, saturatedFatPerG
        		);
        return ingredient;
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
   * Get the Ingredients record by fetching it from the MySQL instance.
   * This runs a SELECT statement and returns a list of Ingredient instances.
   */
  public List<Ingredients> getIngredientByAliasName(String aliasName) throws SQLException {
    String selectUser = "SELECT IngredientName, IFNULL(AliasName, IngredientName) AS AliasName,"
    		+ "IFNULL(FatPerG, 0.0) AS FatPerG, IFNULL(SugarPerG, 0.0) AS SugarPerG, "
    		+ "IFNULL(SodiumPerG, 0.0) AS SodiumPerG, IFNULL(ProteinPerG, 0.0) AS ProteinPerG, "
    		+ "IFNULL(SaturatedFatPerG, 0.0) AS SaturatedFatPerG "
    		+ "FROM Ingredients WHERE AliasName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    List<Ingredients> ingredientList = new ArrayList<>();
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectUser);
      selectStmt.setString(1, aliasName);
      results = selectStmt.executeQuery();
      // You can iterate the result set (although the example below only retrieves
      // the first record). The cursor is initially positioned before the row.
      // Furthermore, you can retrieve fields by name and by type.
      while(results.next()) {
    	String retrieveIngredientName = results.getString("IngredientName");
    	String retrieveAliasName = results.getString("AliasName");
        BigDecimal fatPerG = results.getBigDecimal("FatPerG");
        BigDecimal sugarPerG = results.getBigDecimal("SugarPerG");
        BigDecimal sodiumPerG = results.getBigDecimal("SodiumPerG");
        BigDecimal proteinPerG = results.getBigDecimal("ProteinPerG");
        BigDecimal saturatedFatPerG = results.getBigDecimal("SaturatedFatPerG");
        Ingredients ingredient = new Ingredients(retrieveIngredientName, retrieveAliasName, fatPerG, 
        		sugarPerG, sodiumPerG, proteinPerG, saturatedFatPerG
        		);
        ingredientList.add(ingredient);
      }
      return ingredientList;
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
   * Update the Ingredient record
   * This runs an UPDATE statement
   */
  public Ingredients updateIngredient(Ingredients ingredient) throws SQLException {
    String updateStr= "UPDATE Ingredients SET aliasName=?, FatPerG=?, SugarPerG=?, SodiumPerG=?, ProteinPerG=?, "
    		+ "SaturatedFatPerG=? WHERE IngredientName=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateStr);
      updateStmt.setString(1, ingredient.getAliasName());
      updateStmt.setBigDecimal(2, ingredient.getFatPerG());
      updateStmt.setBigDecimal(3, ingredient.getSugarPerG());
      updateStmt.setBigDecimal(4, ingredient.getSodiumPerG());
      updateStmt.setBigDecimal(5, ingredient.getProteinPerG());
      updateStmt.setBigDecimal(6, ingredient.getSaturatedFatPerG());
      updateStmt.setString(7, ingredient.getIngredientName());
      updateStmt.executeUpdate();
      // return the user
      return ingredient;
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
   * Delete the Ingredients instance.
   * This runs a DELETE statement.
   */
  public Ingredients delete(Ingredients ingredient) throws SQLException {
    String deleteStr = "DELETE FROM Ingredients WHERE ingredientName=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteStr);
      // ingredientName is unique(primary key in Ingredients table
      deleteStmt.setString(1, ingredient.getIngredientName());
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