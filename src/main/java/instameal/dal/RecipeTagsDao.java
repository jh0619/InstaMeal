package instameal.dal;

import instameal.model.RecipeTags;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeTagsDao {
    protected ConnectionManager connectionManager;
    private static RecipeTagsDao instance = null;

    protected RecipeTagsDao() {
        connectionManager = new ConnectionManager();
    }

    public static RecipeTagsDao getInstance() {
        if (instance == null) {
            instance = new RecipeTagsDao();
        }
        return instance;
    }

    public RecipeTags create(RecipeTags recipeTag) throws SQLException {
        String insertTag = "INSERT INTO RecipeTags(RecipeId, Tag) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertTag);
            insertStmt.setInt(1, recipeTag.getRecipeId());
            insertStmt.setString(2, recipeTag.getTag());
            insertStmt.executeUpdate();
            return recipeTag;
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

    public List<RecipeTags> getTagsByRecipeId(int recipeId) throws SQLException {
        List<RecipeTags> recipeTags = new ArrayList<>();
        String selectTags = "SELECT * FROM RecipeTags WHERE RecipeId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTags);
            selectStmt.setInt(1, recipeId);
            results = selectStmt.executeQuery();
            while (results.next()) {
                String tag = results.getString("Tag");
                RecipeTags recipeTag = new RecipeTags(recipeId, tag);
                recipeTags.add(recipeTag);
            }
            return recipeTags;
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

    public RecipeTags updateTag(RecipeTags recipeTag, String newTag) throws SQLException {
        String updateTag = "UPDATE RecipeTags SET Tag=? WHERE RecipeId=? AND Tag=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateTag);
            updateStmt.setString(1, newTag);
            updateStmt.setInt(2, recipeTag.getRecipeId());
            updateStmt.setString(3, recipeTag.getTag());
            updateStmt.executeUpdate();

            // Update the tag in the recipeTag object
            recipeTag.setTag(newTag);
            return recipeTag;
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

    public RecipeTags delete(RecipeTags recipeTag) throws SQLException {
        String deleteTag = "DELETE FROM RecipeTags WHERE RecipeId=? AND Tag=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteTag);
            deleteStmt.setInt(1, recipeTag.getRecipeId());
            deleteStmt.setString(2, recipeTag.getTag());
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
