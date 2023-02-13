package myapp;

public class ApplicationStatHandler {
    
    private String category; 
    private int count; 
    private float maxRating; 
    private float minRating;
    private float totalRating; 
    private String bestRatedApp; 
    private String worstRatedApp; 

    public ApplicationStatHandler(String category) {
        this.category = category;
        this.maxRating = 0f; // initialize max to lowest rating
        this.minRating = 5f; // initialize min to highest rating
    }

    public void updateStats(Application application) {
        if (!category.equalsIgnoreCase(application.getCategory()) || application.getRating() == -1) {
            return;
        }

        count++;
        totalRating += application.getRating();

        if (application.getRating() > maxRating) {
            bestRatedApp = application.getName();
            maxRating = application.getRating();
        }

        if (application.getRating() < minRating) {
            worstRatedApp = application.getName();
            minRating = application.getRating();
        }
    }

    public void printStats() {
        System.out.printf("\n-------------------Category %s-------------------\n", category);
        System.out.printf("Total number of apps in category %s: %d\n", category, count);
        System.out.printf("Best app: %s with the highest rating of %.2f\n", bestRatedApp, maxRating);
        System.out.printf("Poorest rated app: %s with the lowest rating of %.2f\n", worstRatedApp, minRating);
        System.out.printf("Average rating of apps across the category: %.2f\n", totalRating/count);
    }

    @Override
    public String toString() {
        return "ApplicationStatHandler [category=" + category + ", count=" + count + ", maxRating=" + maxRating
                + ", minRating=" + minRating + ", totalRating=" + totalRating + ", bestRatedApp=" + bestRatedApp
                + ", worstRatedApp=" + worstRatedApp + "]";
    }

    
}
