import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Student extends Account {
    private String studentID;
    private ArrayList<Property> favorites;

    public Student(int id, String firstName, String lastName, String birthDate, String username, String password, String email, String phone, String studentID) {
        super(id, firstName, lastName, birthDate, username, password, email, phone);
        this.studentID = studentID;
        favorites = new ArrayList<Property>();
    }

    public String getType() {
        return "STUDENT";
    }

    public String getCompany() {
        return "";
    }

    public String getStudentID() {
        return studentID;
    }

    public ArrayList<Property> getProperties() {
        return this.getFavorites();
    }

    public void addFavorites(Property property) {
        favorites.add(property);
    }

    public void removeFavorites(int listingID) {
        favorites.removeIf(property -> property.getListingID() == listingID);
    }

    public ArrayList<Property> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Property> properties) {
        this.favorites = properties;
    }

    public String toString() {
        return "Username: " + username + '\n' +
                "Student at the University of South Carolina" + '\n' +
                "Name: " + firstName + ' ' + lastname + '\n' +
                "Email: " + email + '\n' +
                "Phone: " + phone + '\n';
    }
}