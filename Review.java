public class Review {
    private int reviewID;
    private int subjectID;
    private int authorID;
    private int rating;
    private String feedback;

    public Review(int reviewID, int subjectID, int authorID, int rating, String feedback) {
        this.reviewID = reviewID;
        this.subjectID = subjectID;
        this.authorID = authorID;
        this.rating = rating;
        this.feedback = feedback;
    }

    public int getReviewID() {
        return reviewID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public int getAuthorID() {
        return authorID;
    }

    public int getRating() {
        return rating;
    }

    public String getFeedback() {
        return feedback;
    }

    @Override
    public String toString() {
        String subject = "" + reviewID;
        String ratingStars = "";
        for(int i = 0; i < rating; i++) {
            ratingStars += ("* ");
        }
        System.out.println();
        if(Accounts.getInstance().searchAccountID(subjectID) != null) {
             subject = Accounts.getInstance().searchAccountID(subjectID).getUsername();
        } else if(Properties.getInstance().findPropertyByID(subjectID) != null) {
            subject = Properties.getInstance().findPropertyByID(subjectID).getAddress();
        }
        return ratingStars + '\n' +
                feedback +  '\n' +
                "Review of " + subject +
                "\n\twritten by " + Accounts.getInstance().searchAccountID(authorID).getUsername() + '\n';

    }
}