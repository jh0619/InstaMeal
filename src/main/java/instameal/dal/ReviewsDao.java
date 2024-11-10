package instameal.dal;

import instameal.model.Reviews;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewsDao {
    protected ConnectionManager connectionManager;
    private static ReviewsDao instance = null;

    protected ReviewsDao() {
        connectionManager = new ConnectionManager();
    }

    public static ReviewsDao getInstance() {
        if (instance == null) {
            instance = new ReviewsDao();
        }
        return instance;
    }

    public Reviews create(Reviews review) throws SQLException {
        String insertReview = "INSERT INTO Reviews(UserId, RecipeId, ReviewDate, Rating, ReviewText) VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertReview, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setInt(1, review.getUserId());
            insertStmt.setInt(2, review.getRecipeId());
            insertStmt.setDate(3, review.getReviewDate());
            insertStmt.setBigDecimal(4, review.getRating());
            insertStmt.setString(5, review.getReviewText());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int reviewId = -1;
            if (resultKey.next()) {
                reviewId = resultKey.getInt(1);
                review.setReviewId(reviewId);
            }
            return review;
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

    public List<Reviews> getReviewsByRecipeId(int recipeId) throws SQLException {
        List<Reviews> reviewsList = new ArrayList<>();
        String selectReviews = "SELECT * FROM Reviews WHERE RecipeId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReviews);
            selectStmt.setInt(1, recipeId);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int reviewId = results.getInt("ReviewId");
                int userId = results.getInt("UserId");
                Date reviewDate = results.getDate("ReviewDate");
                BigDecimal rating = results.getBigDecimal("Rating");
                String reviewText = results.getString("ReviewText");
                Reviews review = new Reviews(reviewId, userId, recipeId, reviewDate, rating, reviewText);
                reviewsList.add(review);
            }
            return reviewsList;
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

    public Reviews updateReviewText(Reviews review, String newText) throws SQLException {
        String updateText = "UPDATE Reviews SET ReviewText=? WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateText);
            updateStmt.setString(1, newText);
            updateStmt.setInt(2, review.getReviewId());
            updateStmt.executeUpdate();

            review.setReviewText(newText);
            return review;
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

    public Reviews delete(Reviews review) throws SQLException {
        String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteReview);
            deleteStmt.setInt(1, review.getReviewId());
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
