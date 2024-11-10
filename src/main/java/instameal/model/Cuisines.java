package instameal.model;

public class Cuisines {
    protected String cuisineName;
    protected String description;

    public Cuisines(String cuisineName, String description) {
        this.cuisineName = cuisineName;
        this.description = description;
    }

    public Cuisines(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Cuisines [cuisineName=" + cuisineName + ", description=" + description + "]";
    }
}
