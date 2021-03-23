import java.util.ArrayList;

public class Reviews {
    private static Reviews reviews;
    private ArrayList<Review> reviewList = new ArrayList<Review>();

    private Reviews() {
        reviewList = DataLoader.loadReviews();
    }

    public static Reviews getInstance() {
        if (reviews == null) {
            reviews = new Reviews();
        }
        return reviews;
    }

    public ArrayList<Review> getReviews() {
        return reviewList;
    }

    public void addReview(Review review) {
        reviewList.add(review);
        DataWriter.saveReviews();
    }

    public Review searchReview(int reviewID) {
        for(Review review : reviewList) {
            if(review.getReviewID() == reviewID) {
                return review;
            }
        }
        System.out.println("No review found matching that ID.");
        return null;
    }

    public void removeReview(int reviewID) {
        reviewList.removeIf(review -> review.getReviewID() == reviewID);
    }
}