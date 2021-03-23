import java.util.ArrayList;

public abstract class Account {
    protected int id;
    protected String firstName;
    protected String lastname;
    protected String birthDate;
    protected String username;
    protected String password;
    protected String email;
    protected String phone;
    protected ArrayList<Review> reviews;

    protected abstract String getType();
    protected abstract String getStudentID();
    protected abstract String getCompany();
    protected abstract ArrayList<Property> getFavorites();
    protected abstract ArrayList<Property> getProperties();

    public Account(int id, String firstName, String lastName, String birthDate, String username, String password, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastname = lastName;
        this.birthDate = birthDate;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        reviews = new ArrayList<Review>();
    }

    public int getAccountID() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public String readReviews() {
        String ret = "Reviews:\n";
        for(Review review : reviews) {
            ret += ("\t" + review.toString() + "\n");
        }
        return ret;
    }

    public int[] getReviewIDs() {
        int[] ret = new int[reviews.size()];
        for(int i = 0; i < ret.length; i++) {
            ret[i] = reviews.get(i).getReviewID();
        }
        return ret;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public void leaveReview(Review review) {
        reviews.add(review);
    }

    public double getRating() {
        double sum = 0.0;
        double count = 0.0;

        for(Review review : reviews) {
            count++;
            sum += review.getRating();
        }

        return sum / count;
    }

    public String toString() {
        return "Type: " + this.getType() + '\n' +
                "ID: " + id + '\n' +
                "First Name: " + firstName + '\n' +
                "Last Name: " + lastname + '\n' +
                "Birth Date: " + birthDate + '\n' +
                "Username: " + username + '\n' +
                "Password: " + password + '\n' +
                "Email: " + email + '\n' +
                "Phone: " + phone + '\n';
    }
}