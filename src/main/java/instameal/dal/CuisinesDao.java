package instameal.dal;

import instameal.model.Cuisines;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuisinesDao {
    protected ConnectionManager connectionManager;
    private static CuisinesDao instance = null;

    protected CuisinesDao() {
        connectionManager = new ConnectionManager();
    }

    public static CuisinesDao getInstance() {
        if (instance == null) {
            instance = new CuisinesDao();
        }
        return instance;
    }

    public Cuisines create(Cuisines cuisine) throws SQLException {
        String insertCuisine = "INSERT INTO Cuisines(CuisineName, Description) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCuisine);
            insertStmt.setString(1, cuisine.getCuisineName());
            insertStmt.setString(2, cuisine.getDescription());
            insertStmt.executeUpdate();
            return cuisine;
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

    public Cuisines getCuisineByName(String cuisineName) throws SQLException {
        String selectCuisine = "SELECT CuisineName, Description FROM Cuisines WHERE CuisineName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCuisine);
            selectStmt.setString(1, cuisineName);
            results = selectStmt.executeQuery();
            if (results.next()) {
                String description = results.getString("Description");
                Cuisines cuisine = new Cuisines(cuisineName, description);
                return cuisine;
            }
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
        return null;
    }

    public List<Cuisines> getAllCuisines() throws SQLException {
        List<Cuisines> cuisinesList = new ArrayList<>();
        String selectAll = "SELECT CuisineName, Description FROM Cuisines;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAll);
            results = selectStmt.executeQuery();
            while (results.next()) {
                String cuisineName = results.getString("CuisineName");
                String description = results.getString("Description");
                Cuisines cuisine = new Cuisines(cuisineName, description);
                cuisinesList.add(cuisine);
            }
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
        return cuisinesList;
    }

    public Cuisines updateCuisineDescription(Cuisines cuisine, String newDescription) throws SQLException {
        String updateCuisine = "UPDATE Cuisines SET Description=? WHERE CuisineName=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateCuisine);
            updateStmt.setString(1, newDescription);
            updateStmt.setString(2, cuisine.getCuisineName());
            updateStmt.executeUpdate();
            cuisine.setDescription(newDescription);
            return cuisine;
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

    public Cuisines delete(Cuisines cuisine) throws SQLException {
        String deleteCuisine = "DELETE FROM Cuisines WHERE CuisineName=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteCuisine);
            deleteStmt.setString(1, cuisine.getCuisineName());
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
