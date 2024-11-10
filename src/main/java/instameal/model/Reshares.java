package instameal.model;

import java.sql.Timestamp;

public class Reshares {
    private int reshareId;
    private int userId;
    private int recipeId;
    private Timestamp created;

    public Reshares(int reshareId, int userId, int recipeId, Timestamp created) {
        this.reshareId = reshareId;
        this.userId = userId;
        this.recipeId = recipeId;
        this.created = created;
    }

    public Reshares(int userId, int recipeId, Timestamp created) {
        this.userId = userId;
        this.recipeId = recipeId;
        this.created = created;
    }

    public int getReshareId() {
        return reshareId;
    }

    public void setReshareId(int reshareId) {
        this.reshareId = reshareId;
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

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Reshares [reshareId=" + reshareId + ", userId=" + userId + ", recipeId=" + recipeId +
                ", created=" + created + "]";
    }
}
