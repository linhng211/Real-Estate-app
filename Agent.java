import java.util.ArrayList;

public class Agent extends Account {
    private String company;
    private ArrayList<Property> properties;

    public Agent(int id, String firstName, String lastName, String birthDate, String username, String password, String email, String phone, String company) {
        super(id, firstName, lastName, birthDate, username, password, email, phone);
        this.company = company;
        properties = new ArrayList<Property>();
    }

    public String getType() {
        return "AGENT";
    }

    public String getStudentID() {
        return "";
    }

    public String getCompany() {
        return company;
    }

    public ArrayList<Property> getFavorites() {
        return this.getProperties();
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Apartment listApartment(int listingID, String address, double proximity, int size, double price, int bed, double bath, int propertyManagerID, int realEstateAgentID, boolean studio) {
        return new Apartment(listingID, address, proximity, size, price, bed, bath, propertyManagerID, realEstateAgentID, studio);
    }

    public Townhouse listTownhouse(int listingID, String address, double proximity, int size, double price, int bed, double bath, int propertyManagerID, int realEstateAgentID, double lotSize, int sharedWalls) {
        return new Townhouse(listingID, address, proximity, size, price, bed, bath, propertyManagerID, realEstateAgentID, lotSize, sharedWalls);
    }

    public House listHouse(int listingID, String address, double proximity, int size, double price, int bed, double bath, int propertyManagerID, int realEstateAgentID, double lotSize) {
        return new House(listingID, address, proximity, size, price, bed, bath, propertyManagerID, realEstateAgentID, lotSize);
    }

    public void removeListing(int listingID) {
        properties.removeIf(property -> property.getListingID() == listingID);
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public String viewProperties() {
        String ret = "";
        for(Property property : properties) {
            ret += property.toString();
        }
        return ret;
    }
    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public String toString() {
        return "Username: " + super.getUsername() + '\n' +
                "Company: " + company + '\n' +
                "Name: " + firstName + ' ' + lastname + '\n' +
                "Email: " + email + '\n' +
                "Phone: " + phone + '\n';
    }
}