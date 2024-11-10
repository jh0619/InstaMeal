package instameal.model;

import java.math.BigDecimal;

public class RecipeIngredients {
    private int recipeId;
    private String ingredientName;
    private BigDecimal quantityGram;

    public RecipeIngredients(int recipeId, String ingredientName, BigDecimal quantityGram) {
        this.recipeId = recipeId;
        this.ingredientName = ingredientName;
        this.quantityGram = quantityGram;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
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

    @Override
    public String toString() {
        return "RecipeIngredients [recipeId=" + recipeId + ", ingredientName=" + ingredientName + ", quantityGram=" + quantityGram + "]";
    }
}
