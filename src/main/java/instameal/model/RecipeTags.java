package instameal.model;

public class RecipeTags {
    private int recipeId;
    private String tag;

    public RecipeTags(int recipeId, String tag) {
        this.recipeId = recipeId;
        this.tag = tag;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "RecipeTags [recipeId=" + recipeId + ", tag=" + tag + "]";
    }
}
