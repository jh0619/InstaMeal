package instameal.model;

import java.sql.Date;
import java.math.BigDecimal;

public class Reviews {
    private int reviewId;
    private int userId;
    private int recipeId;
    private Date reviewDate;
    private BigDecimal rating;
    private String reviewText;

    public Reviews(int reviewId, int userId, int recipeId, Date reviewDate, BigDecimal rating, String reviewText) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.recipeId = recipeId;
        this.reviewDate = reviewDate;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public Reviews(int userId, int recipeId, Date reviewDate, BigDecimal rating, String reviewText) {
        this.userId = userId;
        this.recipeId = recipeId;
        this.reviewDate = reviewDate;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    @Override
    public String toString() {
        return "Reviews [reviewId=" + reviewId + ", userId=" + userId + ", recipeId=" + recipeId +
                ", reviewDate=" + reviewDate + ", rating=" + rating + ", reviewText=" + reviewText + "]";
    }
}
