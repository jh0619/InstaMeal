package instameal.dal;

import instameal.model.Reshares;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResharesDao {
    protected ConnectionManager connectionManager;
    private static ResharesDao instance = null;

    protected ResharesDao() {
        connectionManager = new ConnectionManager();
    }

    public static ResharesDao getInstance() {
        if (instance == null) {
            instance = new ResharesDao();
        }
        return instance;
    }

    public Reshares create(Reshares reshare) throws SQLException {
        String insertReshare = "INSERT INTO Reshares(UserId, RecipeId, Created) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertReshare, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setInt(1, reshare.getUserId());
            insertStmt.setInt(2, reshare.getRecipeId());
            insertStmt.setTimestamp(3, reshare.getCreated());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int reshareId = -1;
            if (resultKey.next()) {
                reshareId = resultKey.getInt(1);
                reshare.setReshareId(reshareId);
            }
            return reshare;
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

    public List<Reshares> getResharesByUserId(int userId) throws SQLException {
        List<Reshares> reshareList = new ArrayList<>();
        String selectReshares = "SELECT * FROM Reshares WHERE UserId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReshares);
            selectStmt.setInt(1, userId);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int reshareId = results.getInt("ReshareId");
                int recipeId = results.getInt("RecipeId");
                Timestamp created = results.getTimestamp("Created");
                Reshares reshare = new Reshares(reshareId, userId, recipeId, created);
                reshareList.add(reshare);
            }
            return reshareList;
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

    public Reshares delete(Reshares reshare) throws SQLException {
        String deleteReshare = "DELETE FROM Reshares WHERE ReshareId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteReshare);
            deleteStmt.setInt(1, reshare.getReshareId());
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
