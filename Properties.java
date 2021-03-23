import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class Properties {
    private static Properties properties;
    private static ArrayList<Property> propertyList = new ArrayList<Property>();

    private Properties() {
        propertyList = DataLoader.loadProperties();
    }

    public static Properties getInstance() {
        if (properties == null) {
            properties = new Properties();
        }
        return properties;
    }

    public ArrayList<Property> getProperties() {
        return propertyList;
    }

    public void addProperty(Property property) {
        propertyList.add(property);
        DataWriter.saveProperties();
    }

    public ArrayList<Property> searchProperties(double proximity, int size, double price, int bed, double bath, String type, ArrayList<String> amenities) {
        ArrayList<Property> foundProperties = new ArrayList<Property>();
        for(Property property : propertyList) {
            if(property.getProximity() <= proximity
                    && property.getSize() >= size
                    && property.getPrice() <= price
                    && property.getBed() >= bed
                    && property.getBath() >= bath
                    && property.getType().equalsIgnoreCase(type)) {
                foundProperties.add(property);
            }
        }
        if(foundProperties.size() == 0) {
            System.out.println("No results matched your search.");
            return null;
        }
        return foundProperties;
    }

    public Property findPropertyByID(int listingID) {
        for(Property property : propertyList) {
            if(property.getListingID() == listingID) return property;
        }
        return null;
    }

    public void removeProperty(int listingID) {
        propertyList.removeIf(property -> property.getListingID() == listingID);
    }

    public static void lease(Account account, Property property, String start, String end, String paymentAddress, Account roommate) {
        File originalFile = new File("leaseAgreement.txt");
        File newFile = new File(originalFile.getParent(), "Test.txt");
        SimpleDateFormat getDate = new SimpleDateFormat("MM/dd/yyyy");
        String currentDate = getDate.format(new Date());
        try (BufferedReader read = new BufferedReader(new FileReader(originalFile));
             BufferedWriter writeNew = new BufferedWriter(new FileWriter(newFile))) {
            String line;
            while ((line = read.readLine()) != null) {
                if (line.contains("<LANDLORD>")) {
                    line = line.replace("<LANDLORD>", Accounts.getInstance().searchAccountID(property.getPropertyManagerID()).getCompany());
                }
                if (line.contains("<LANDLORD>")) {
                    line = line.replace("<LANDLORD>", Accounts.getInstance().searchAccountID(property.getPropertyManagerID()).getCompany());
                }
                if (line.contains("<TENANT(s)>") && roommate != null) {
                    line = line.replace("<TENANT(s)>", account.getFirstName() + " " + account.getLastName() + " and " + roommate.getFirstName() + " " + roommate.getLastName());
                }
                if (line.contains("<TENANT(s)>") && roommate == null) {
                    line = line.replace("<TENANT(s)>", account.getFirstName() + " " + account.getLastName());
                }
                if (line.contains("<DATE>")) {
                    line = line.replace("<DATE>", currentDate);
                }
                if (line.contains("<TENANT 1>")) {
                    line = line.replace("<TENANT 1>", account.getFirstName() + " " + account.getLastName());
                }
                if (line.contains("<TENANT 1 SIGNATURE>")) {
                    line = line.replace("<TENANT 1 SIGNATURE>", "--------------------------");
                }
                if (line.contains("<TENANT X>") && roommate != null) {
                    line = line.replace("<TENANT X>", roommate.getFirstName() + " " + roommate.getLastName());
                }
                if (line.contains("<TENANT X SIGNATURE>") && roommate != null) {
                    line = line.replace("<TENANT X SIGNATURE>", "--------------------------");
                }
                if (line.contains("<TENANT X>") && roommate == null) {
                    line = line.replace("<TENANT X>", Accounts.getInstance().searchAccountID(property.getPropertyManagerID()).getFirstName() +
                            " " + Accounts.getInstance().searchAccountID(property.getPropertyManagerID()).getLastName()) +
                            "\n" + Accounts.getInstance().searchAccountID(property.getPropertyManagerID()).getCompany();
                }
                if (line.contains("<TENANT X SIGNATURE>") && roommate == null) {
                    line = line.replace("<TENANT X SIGNATURE>", "--------------------------");
                }
                if (line.contains("<LANDLORD SIGNATURE>") && roommate != null) {
                    line = line.replace("<LANDLORD SIGNATURE>", Accounts.getInstance().searchAccountID(property.getPropertyManagerID()).getFirstName() +
                            " " + Accounts.getInstance().searchAccountID(property.getPropertyManagerID()).getLastName()) +
                            "\n" + Accounts.getInstance().searchAccountID(property.getPropertyManagerID()).getCompany();
                }
                if (line.contains("<LANDLORD SIGNATURE LINE>") && roommate != null) {
                    line = line.replace("<LANDLORD SIGNATURE LINE>", "--------------------------");
                }
                if (line.contains("<LANDLORD SIGNATURE>") && roommate == null) {
                    line = "";
                }
                if (line.contains("<LANDLORD SIGNATURE LINE>") && roommate == null) {
                    line = "";
                }
                if (line.contains("<NUM_BED>")) {
                    line = line.replace("<NUM_BED>", String.valueOf(property.getBed()));
                }
                if (line.contains("<NUM_BATH>")) {
                    line = line.replace("<NUM_BATH>", String.valueOf(property.getBath()));
                }
                if (line.contains("<PROPERTY_ADDRESS>")) {
                    line = line.replace("<PROPERTY_ADDRESS>", property.getAddress());
                }
                if (line.contains("<START DATE>")) {
                    line = line.replace("<START DATE>", start);
                }
                if (line.contains("<END DATE>")) {
                    line = line.replace("<END DATE>", end);
                }
                if (line.contains("<RENT>")) {
                    line = line.replace("<RENT>", "$" + property.getPrice());
                }
                if (line.contains("<PAYMENT ADDRESS>")) {
                    line = line.replace("<PAYMENT ADDRESS>", paymentAddress);
                }
                writeNew.write(line);
                writeNew.newLine();
                System.out.println(line);
            }
        }
        catch (IOException e) {
            System.err.println("Error" + e.getMessage());
        }
    }
}