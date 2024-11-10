package instameal.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Recipes {
    private int recipeId;
    private String recipeName;
    private String recipeDescription;
    private int cookingInstructionId;
    private Timestamp created;
    private String cuisineName;
    private BigDecimal calories;
    private BigDecimal totalFatPDV;
    private BigDecimal sugarPDV;
    private BigDecimal sodiumPDV;
    private BigDecimal proteinPDV;
    private BigDecimal saturatedFatPDV;
    private BigDecimal carbohydratesPDV;

    public Recipes(int recipeId, String recipeName, String recipeDescription, int cookingInstructionId, Timestamp created,
                   String cuisineName, BigDecimal calories, BigDecimal totalFatPDV, BigDecimal sugarPDV, BigDecimal sodiumPDV,
                   BigDecimal proteinPDV, BigDecimal saturatedFatPDV, BigDecimal carbohydratesPDV) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.cookingInstructionId = cookingInstructionId;
        this.created = created;
        this.cuisineName = cuisineName;
        this.calories = calories;
        this.totalFatPDV = totalFatPDV;
        this.sugarPDV = sugarPDV;
        this.sodiumPDV = sodiumPDV;
        this.proteinPDV = proteinPDV;
        this.saturatedFatPDV = saturatedFatPDV;
        this.carbohydratesPDV = carbohydratesPDV;
    }

    public Recipes(String recipeName, String recipeDescription, int cookingInstructionId, Timestamp created,
                   String cuisineName, BigDecimal calories, BigDecimal totalFatPDV, BigDecimal sugarPDV, BigDecimal sodiumPDV,
                   BigDecimal proteinPDV, BigDecimal saturatedFatPDV, BigDecimal carbohydratesPDV) {
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.cookingInstructionId = cookingInstructionId;
        this.created = created;
        this.cuisineName = cuisineName;
        this.calories = calories;
        this.totalFatPDV = totalFatPDV;
        this.sugarPDV = sugarPDV;
        this.sodiumPDV = sodiumPDV;
        this.proteinPDV = proteinPDV;
        this.saturatedFatPDV = saturatedFatPDV;
        this.carbohydratesPDV = carbohydratesPDV;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public int getCookingInstructionId() {
        return cookingInstructionId;
    }

    public void setCookingInstructionId(int cookingInstructionId) {
        this.cookingInstructionId = cookingInstructionId;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    public BigDecimal getCalories() {
        return calories;
    }

    public void setCalories(BigDecimal calories) {
        this.calories = calories;
    }

    public BigDecimal getTotalFatPDV() {
        return totalFatPDV;
    }

    public void setTotalFatPDV(BigDecimal totalFatPDV) {
        this.totalFatPDV = totalFatPDV;
    }

    public BigDecimal getSugarPDV() {
        return sugarPDV;
    }

    public void setSugarPDV(BigDecimal sugarPDV) {
        this.sugarPDV = sugarPDV;
    }

    public BigDecimal getSodiumPDV() {
        return sodiumPDV;
    }

    public void setSodiumPDV(BigDecimal sodiumPDV) {
        this.sodiumPDV = sodiumPDV;
    }

    public BigDecimal getProteinPDV() {
        return proteinPDV;
    }

    public void setProteinPDV(BigDecimal proteinPDV) {
        this.proteinPDV = proteinPDV;
    }

    public BigDecimal getSaturatedFatPDV() {
        return saturatedFatPDV;
    }

    public void setSaturatedFatPDV(BigDecimal saturatedFatPDV) {
        this.saturatedFatPDV = saturatedFatPDV;
    }

    public BigDecimal getCarbohydratesPDV() {
        return carbohydratesPDV;
    }

    public void setCarbohydratesPDV(BigDecimal carbohydratesPDV) {
        this.carbohydratesPDV = carbohydratesPDV;
    }

    @Override
    public String toString() {
        return "Recipes{" +
                "recipeId=" + recipeId +
                ", recipeName='" + recipeName + '\'' +
                ", recipeDescription='" + recipeDescription + '\'' +
                ", cookingInstructionId=" + cookingInstructionId +
                ", created=" + created +
                ", cuisineName='" + cuisineName + '\'' +
                ", calories=" + calories +
                ", totalFatPDV=" + totalFatPDV +
                ", sugarPDV=" + sugarPDV +
                ", sodiumPDV=" + sodiumPDV +
                ", proteinPDV=" + proteinPDV +
                ", saturatedFatPDV=" + saturatedFatPDV +
                ", carbohydratesPDV=" + carbohydratesPDV +
                '}';
    }
}
