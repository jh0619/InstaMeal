package instameal.dal;

import instameal.model.UserStocks;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserStocksDao {
    protected ConnectionManager connectionManager;
    private static UserStocksDao instance = null;

    protected UserStocksDao() {
        connectionManager = new ConnectionManager();
    }

    public static UserStocksDao getInstance() {
        if (instance == null) {
            instance = new UserStocksDao();
        }
        return instance;
    }

    public UserStocks create(UserStocks userStock) throws SQLException {
        String insertStock = "INSERT INTO UserStocks(UserId, IngredientName, QuantityGram, ExpirationDate) VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertStock, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setInt(1, userStock.getUserId());
            insertStmt.setString(2, userStock.getIngredientName());
            insertStmt.setBigDecimal(3, userStock.getQuantityGram());
            insertStmt.setDate(4, userStock.getExpirationDate());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int stockId = -1;
            if (resultKey.next()) {
                stockId = resultKey.getInt(1);
                userStock.setStockId(stockId);
            }
            return userStock;
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

    public List<UserStocks> getStocksByUserId(int userId) throws SQLException {
        List<UserStocks> userStocksList = new ArrayList<>();
        String selectStocks = "SELECT * FROM UserStocks WHERE UserId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectStocks);
            selectStmt.setInt(1, userId);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int stockId = results.getInt("StockId");
                String ingredientName = results.getString("IngredientName");
                BigDecimal quantityGram = results.getBigDecimal("QuantityGram");
                Date expirationDate = results.getDate("ExpirationDate");
                UserStocks userStock = new UserStocks(stockId, userId, ingredientName, quantityGram, expirationDate);
                userStocksList.add(userStock);
            }
            return userStocksList;
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

    public UserStocks updateQuantity(UserStocks userStock, BigDecimal newQuantity) throws SQLException {
        String updateQuantity = "UPDATE UserStocks SET QuantityGram=? WHERE StockId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateQuantity);
            updateStmt.setBigDecimal(1, newQuantity);
            updateStmt.setInt(2, userStock.getStockId());
            updateStmt.executeUpdate();

            userStock.setQuantityGram(newQuantity);
            return userStock;
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

    public UserStocks delete(UserStocks userStock) throws SQLException {
        String deleteStock = "DELETE FROM UserStocks WHERE StockId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteStock);
            deleteStmt.setInt(1, userStock.getStockId());
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
