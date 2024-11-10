package instameal.model;

import java.math.BigDecimal;
import java.sql.Date;

public class UserStocks {
    private int stockId;
    private int userId;
    private String ingredientName;
    private BigDecimal quantityGram;
    private Date expirationDate;

    public UserStocks(int stockId, int userId, String ingredientName, BigDecimal quantityGram, Date expirationDate) {
        this.stockId = stockId;
        this.userId = userId;
        this.ingredientName = ingredientName;
        this.quantityGram = quantityGram;
        this.expirationDate = expirationDate;
    }

    public UserStocks(int userId, String ingredientName, BigDecimal quantityGram, Date expirationDate) {
        this.userId = userId;
        this.ingredientName = ingredientName;
        this.quantityGram = quantityGram;
        this.expirationDate = expirationDate;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public BigDecimal getQuantityGram() {
        return quantityGram;
    }

    public void setQuantityGram(BigDecimal quantityGram) {
        this.quantityGram = quantityGram;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "UserStocks [stockId=" + stockId + ", userId=" + userId + ", ingredientName=" + ingredientName +
                ", quantityGram=" + quantityGram + ", expirationDate=" + expirationDate + "]";
    }
}
