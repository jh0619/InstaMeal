package instameal.model;

public class CookingInstructions {
    private int cookingInstructionId;
    private int cookingTimeMins;
    private int cookingSteps;
    private String stepsDescriptions;
    private String cookingDifficulty;

    public CookingInstructions(int cookingInstructionId, int cookingTimeMins, int cookingSteps, String stepsDescriptions, String cookingDifficulty) {
        this.cookingInstructionId = cookingInstructionId;
        this.cookingTimeMins = cookingTimeMins;
        this.cookingSteps = cookingSteps;
        this.stepsDescriptions = stepsDescriptions;
        this.cookingDifficulty = cookingDifficulty;
    }

    public CookingInstructions(int cookingTimeMins, int cookingSteps, String stepsDescriptions, String cookingDifficulty) {
        this.cookingTimeMins = cookingTimeMins;
        this.cookingSteps = cookingSteps;
        this.stepsDescriptions = stepsDescriptions;
        this.cookingDifficulty = cookingDifficulty;
    }

    public int getCookingInstructionId() {
        return cookingInstructionId;
    }

    public void setCookingInstructionId(int cookingInstructionId) {
        this.cookingInstructionId = cookingInstructionId;
    }

    public int getCookingTimeMins() {
        return cookingTimeMins;
    }

    public void setCookingTimeMins(int cookingTimeMins) {
        this.cookingTimeMins = cookingTimeMins;
    }

    public int getCookingSteps() {
        return cookingSteps;
    }

    public void setCookingSteps(int cookingSteps) {
        this.cookingSteps = cookingSteps;
    }

    public String getStepsDescriptions() {
        return stepsDescriptions;
    }

    public void setStepsDescriptions(String stepsDescriptions) {
        this.stepsDescriptions = stepsDescriptions;
    }

    public String getCookingDifficulty() {
        return cookingDifficulty;
    }

    public void setCookingDifficulty(String cookingDifficulty) {
        this.cookingDifficulty = cookingDifficulty;
    }

    @Override
    public String toString() {
        return "CookingInstructions{" +
                "cookingInstructionId=" + cookingInstructionId +
                ", cookingTimeMins=" + cookingTimeMins +
                ", cookingSteps=" + cookingSteps +
                ", stepsDescriptions='" + stepsDescriptions + '\'' +
                ", cookingDifficulty='" + cookingDifficulty + '\'' +
                '}';
    }
}
