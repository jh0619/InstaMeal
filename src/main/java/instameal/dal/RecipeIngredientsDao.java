package instameal.dal;

import instameal.model.RecipeIngredients;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeIngredientsDao {
    protected ConnectionManager connectionManager;
    private static RecipeIngredientsDao instance = null;

    protected RecipeIngredientsDao() {
        connectionManager = new ConnectionManager();
    }

    public static RecipeIngredientsDao getInstance() {
        if (instance == null) {
            instance = new RecipeIngredientsDao();
        }
        return instance;
    }

    public RecipeIngredients create(RecipeIngredients recipeIngredient) throws SQLException {
        String insertIngredient = "INSERT INTO RecipeIngredients(RecipeId, IngredientName, QuantityGram) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertIngredient);
            insertStmt.setInt(1, recipeIngredient.getRecipeId());
            insertStmt.setString(2, recipeIngredient.getIngredientName());
            insertStmt.setBigDecimal(3, recipeIngredient.getQuantityGram());
            insertStmt.executeUpdate();
            return recipeIngredient;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    public List<RecipeIngredients> getIngredientsByRecipeId(int recipeId) throws SQLException {
        List<RecipeIngredients> recipeIngredientsList = new ArrayList<>();
        String selectIngredients = "SELECT * FROM RecipeIngredients WHERE RecipeId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectIngredients);
            selectStmt.setInt(1, recipeId);
            results = selectStmt.executeQuery();
            while (results.next()) {
                String ingredientName = results.getString("IngredientName");
                BigDecimal quantityGram = results.getBigDecimal("QuantityGram");
                RecipeIngredients recipeIngredient = new RecipeIngredients(recipeId, ingredientName, quantityGram);
                recipeIngredientsList.add(recipeIngredient);
            }
            return recipeIngredientsList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
    }

    public List<RecipeIngredients> getIngredientsByName(String ingredientName) throws SQLException {
        List<RecipeIngredients> recipeIngredientsList = new ArrayList<>();
        String selectIngredientsByName = "SELECT * FROM RecipeIngredients WHERE IngredientName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectIngredientsByName);
            selectStmt.setString(1, ingredientName);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int recipeId = results.getInt("RecipeId");
                BigDecimal quantityGram = results.getBigDecimal("QuantityGram");
                RecipeIngredients recipeIngredient = new RecipeIngredients(recipeId, ingredientName, quantityGram);
                recipeIngredientsList.add(recipeIngredient);
            }
            return recipeIngredientsList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
    }

    public RecipeIngredients updateQuantity(RecipeIngredients recipeIngredient, BigDecimal newQuantity) throws SQLException {
        String updateQuantity = "UPDATE RecipeIngredients SET QuantityGram=? WHERE RecipeId=? AND IngredientName=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateQuantity);
            updateStmt.setBigDecimal(1, newQuantity);
            updateStmt.setInt(2, recipeIngredient.getRecipeId());
            updateStmt.setString(3, recipeIngredient.getIngredientName());
            updateStmt.executeUpdate();

            // Update the quantity in the recipeIngredient object
            recipeIngredient.setQuantityGram(newQuantity);
            return recipeIngredient;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
    }

    public RecipeIngredients delete(RecipeIngredients recipeIngredient) throws SQLException {
        String deleteIngredient = "DELETE FROM RecipeIngredients WHERE RecipeId=? AND IngredientName=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteIngredient);
            deleteStmt.setInt(1, recipeIngredient.getRecipeId());
            deleteStmt.setString(2, recipeIngredient.getIngredientName());
            deleteStmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }
}
