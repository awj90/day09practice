package myapp;

public class Application {

    private String name;
    private String category;
    private float rating;

    
    public Application(String name, String category, float rating) {
        this.name = name;
        this.category = category;
        if (!Float.isNaN(rating)) {
            this.rating = rating;
        } else {
            this.rating = -1;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Application [name=" + name + ", category=" + category + ", rating=" + rating + "]";
    }
    
}