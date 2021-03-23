import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class DataWriter extends DataConstants {

    public static void saveProperties() {
        Properties properties = Properties.getInstance();
        ArrayList<Property> propertyList = properties.getProperties();
        JSONArray jsonListings = new JSONArray();

        //creating all the json objects
        for (Property property : propertyList) {
            if(property.getType().equalsIgnoreCase("HOUSE")) {
                jsonListings.add(getHouseJSON(property));
            } else if(property.getType().equalsIgnoreCase("TOWNHOUSE")) {
                jsonListings.add(getTownhouseJSON(property));
            } else if(property.getType().equalsIgnoreCase("APARTMENT")) {
                jsonListings.add(getApartmentJSON(property));
            }
        }

        //Write JSON file
        try (FileWriter file = new FileWriter(PROPERTIES_FILE_NAME)) {

            file.write(jsonListings.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveAccounts() {
        Accounts accounts = Accounts.getInstance();
        ArrayList<Account> accountList = accounts.getAccounts();
        JSONArray jsonAccounts = new JSONArray();

        //creating all the json objects
        for (Account account : accountList) {
            if(account.getType().equalsIgnoreCase("STUDENT")) {
                jsonAccounts.add(getStudentJSON(account));
            } else if(account.getType().equalsIgnoreCase("AGENT")) {
                jsonAccounts.add(getAgentJSON(account));
            }
        }

        //Write JSON file
        try (FileWriter file = new FileWriter(ACCOUNT_FILE_NAME)) {

            file.write(jsonAccounts.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveReviews() {
        Reviews reviews = Reviews.getInstance();
        ArrayList<Review> reviewList = reviews.getReviews();
        JSONArray jsonReviews = new JSONArray();

        //creating all the json objects
        for (Review review : reviewList) {
            jsonReviews.add(getReviewJSON(review));
        }

        //Write JSON file
        try (FileWriter file = new FileWriter(REVIEW_FILE_NAME)) {

            file.write(jsonReviews.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getHouseJSON(Property property) {
        JSONObject propertyDetails = new JSONObject();
        propertyDetails.put(PROPERTIES_TYPE, property.getType());
        propertyDetails.put(PROPERTIES_LISTING_ID, property.getListingID());
        propertyDetails.put(PROPERTIES_ADDRESS, property.getAddress());
        propertyDetails.put(PROPERTIES_PROXIMITY, property.getProximity());
        propertyDetails.put(PROPERTIES_SIZE, property.getSize());
        propertyDetails.put(PROPERTIES_PRICE, property.getPrice());
        propertyDetails.put(PROPERTIES_BED, property.getBed());
        propertyDetails.put(PROPERTIES_BATH, property.getBath());
        propertyDetails.put(PROPERTIES_PROPERTY_MANAGER_ID, property.getPropertyManagerID());
        propertyDetails.put(PROPERTIES_REAL_ESTATE_AGENT_ID, property.getRealEstateAgentID());
        propertyDetails.put(PROPERTIES_IS_AVAILABLE, property.isAvailable());
        propertyDetails.put(PROPERTIES_LOT_SIZE, property.getLotSize());
        propertyDetails.put(PROPERTIES_SHARED_WALLS, 0);
        propertyDetails.put(PROPERTIES_STUDIO, false);
        propertyDetails.put(PROPERTIES_AIR_CONDITIONING, property.hasAmenity("AIR_CONDITIONING"));
        propertyDetails.put(PROPERTIES_POOL, property.hasAmenity("POOL"));
        propertyDetails.put(PROPERTIES_PARKING, property.hasAmenity("PARKING"));
        propertyDetails.put(PROPERTIES_HANDICAP_ACCESSIBLE, property.hasAmenity("HANDICAP_ACCESSIBLE"));
        propertyDetails.put(PROPERTIES_PETS_ALLOWED, property.hasAmenity("PETS_ALLOWED"));
        propertyDetails.put(PROPERTIES_FURNISHED, property.hasAmenity("FURNISHED"));
        propertyDetails.put(PROPERTIES_DISHWASHER, property.hasAmenity("DISHWASHER"));
        propertyDetails.put(PROPERTIES_MICROWAVE, property.hasAmenity("MICROWAVE"));
        propertyDetails.put(PROPERTIES_CABLE, property.hasAmenity("CABLE"));
        propertyDetails.put(PROPERTIES_GARAGE, property.hasAmenity("GARAGE"));
        propertyDetails.put(PROPERTIES_INTERNET, property.hasAmenity("INTERNET"));
        propertyDetails.put(PROPERTIES_GYM, property.hasAmenity("GYM"));
        propertyDetails.put(PROPERTIES_LAUNDRY, property.hasAmenity("LAUNDRY"));

        int[] reviewIDs = getReviewIDs(property.getReviews());
        JSONArray arr = new JSONArray();
        for(int i = 0; i < reviewIDs.length; i++) {
            arr.add(i,reviewIDs[i]);
        }
        propertyDetails.put(PROPERTIES_REVIEWS, arr);

        return propertyDetails;
    }

    public static JSONObject getTownhouseJSON(Property property) {
        JSONObject propertyDetails = new JSONObject();
        propertyDetails.put(PROPERTIES_TYPE, property.getType());
        propertyDetails.put(PROPERTIES_LISTING_ID, property.getListingID());
        propertyDetails.put(PROPERTIES_ADDRESS, property.getAddress());
        propertyDetails.put(PROPERTIES_PROXIMITY, property.getProximity());
        propertyDetails.put(PROPERTIES_SIZE, property.getSize());
        propertyDetails.put(PROPERTIES_PRICE, property.getPrice());
        propertyDetails.put(PROPERTIES_BED, property.getBed());
        propertyDetails.put(PROPERTIES_BATH, property.getBath());
        propertyDetails.put(PROPERTIES_PROPERTY_MANAGER_ID, property.getPropertyManagerID());
        propertyDetails.put(PROPERTIES_REAL_ESTATE_AGENT_ID, property.getRealEstateAgentID());
        propertyDetails.put(PROPERTIES_IS_AVAILABLE, property.isAvailable());
        propertyDetails.put(PROPERTIES_LOT_SIZE, property.getLotSize());
        propertyDetails.put(PROPERTIES_SHARED_WALLS, property.getSharedWalls());
        propertyDetails.put(PROPERTIES_STUDIO, false);
        propertyDetails.put(PROPERTIES_AIR_CONDITIONING, property.hasAmenity("AIR_CONDITIONING"));
        propertyDetails.put(PROPERTIES_POOL, property.hasAmenity("POOL"));
        propertyDetails.put(PROPERTIES_PARKING, property.hasAmenity("PARKING"));
        propertyDetails.put(PROPERTIES_HANDICAP_ACCESSIBLE, property.hasAmenity("HANDICAP_ACCESSIBLE"));
        propertyDetails.put(PROPERTIES_PETS_ALLOWED, property.hasAmenity("PETS_ALLOWED"));
        propertyDetails.put(PROPERTIES_FURNISHED, property.hasAmenity("FURNISHED"));
        propertyDetails.put(PROPERTIES_DISHWASHER, property.hasAmenity("DISHWASHER"));
        propertyDetails.put(PROPERTIES_MICROWAVE, property.hasAmenity("MICROWAVE"));
        propertyDetails.put(PROPERTIES_CABLE, property.hasAmenity("CABLE"));
        propertyDetails.put(PROPERTIES_GARAGE, property.hasAmenity("GARAGE"));
        propertyDetails.put(PROPERTIES_INTERNET, property.hasAmenity("INTERNET"));
        propertyDetails.put(PROPERTIES_GYM, property.hasAmenity("GYM"));
        propertyDetails.put(PROPERTIES_LAUNDRY, property.hasAmenity("LAUNDRY"));

        int[] reviewIDs = getReviewIDs(property.getReviews());
        JSONArray arr = new JSONArray();
        for(int i = 0; i < reviewIDs.length; i++) {
            arr.add(i,reviewIDs[i]);
        }
        propertyDetails.put(PROPERTIES_REVIEWS, arr);

        return propertyDetails;
    }

    public static JSONObject getApartmentJSON(Property property) {
        JSONObject propertyDetails = new JSONObject();
        propertyDetails.put(PROPERTIES_TYPE, property.getType());
        propertyDetails.put(PROPERTIES_LISTING_ID, property.getListingID());
        propertyDetails.put(PROPERTIES_ADDRESS, property.getAddress());
        propertyDetails.put(PROPERTIES_PROXIMITY, property.getProximity());
        propertyDetails.put(PROPERTIES_SIZE, property.getSize());
        propertyDetails.put(PROPERTIES_PRICE, property.getPrice());
        propertyDetails.put(PROPERTIES_BED, property.getBed());
        propertyDetails.put(PROPERTIES_BATH, property.getBath());
        propertyDetails.put(PROPERTIES_PROPERTY_MANAGER_ID, property.getPropertyManagerID());
        propertyDetails.put(PROPERTIES_REAL_ESTATE_AGENT_ID, property.getRealEstateAgentID());
        propertyDetails.put(PROPERTIES_IS_AVAILABLE, property.isAvailable());
        propertyDetails.put(PROPERTIES_LOT_SIZE, 0);
        propertyDetails.put(PROPERTIES_SHARED_WALLS, 0);
        propertyDetails.put(PROPERTIES_STUDIO, property.getStudio());
        propertyDetails.put(PROPERTIES_AIR_CONDITIONING, property.hasAmenity("AIR_CONDITIONING"));
        propertyDetails.put(PROPERTIES_POOL, property.hasAmenity("POOL"));
        propertyDetails.put(PROPERTIES_PARKING, property.hasAmenity("PARKING"));
        propertyDetails.put(PROPERTIES_HANDICAP_ACCESSIBLE, property.hasAmenity("HANDICAP_ACCESSIBLE"));
        propertyDetails.put(PROPERTIES_PETS_ALLOWED, property.hasAmenity("PETS_ALLOWED"));
        propertyDetails.put(PROPERTIES_FURNISHED, property.hasAmenity("FURNISHED"));
        propertyDetails.put(PROPERTIES_DISHWASHER, property.hasAmenity("DISHWASHER"));
        propertyDetails.put(PROPERTIES_MICROWAVE, property.hasAmenity("MICROWAVE"));
        propertyDetails.put(PROPERTIES_CABLE, property.hasAmenity("CABLE"));
        propertyDetails.put(PROPERTIES_GARAGE, property.hasAmenity("GARAGE"));
        propertyDetails.put(PROPERTIES_INTERNET, property.hasAmenity("INTERNET"));
        propertyDetails.put(PROPERTIES_GYM, property.hasAmenity("GYM"));
        propertyDetails.put(PROPERTIES_LAUNDRY, property.hasAmenity("LAUNDRY"));

        int[] reviewIDs = getReviewIDs(property.getReviews());
        JSONArray arr = new JSONArray();
        for(int i = 0; i < reviewIDs.length; i++) {
            arr.add(i,reviewIDs[i]);
        }
        propertyDetails.put(PROPERTIES_REVIEWS, arr);

        return propertyDetails;
    }

    public static JSONObject getStudentJSON(Account account) {
        JSONObject accountDetails = new JSONObject();
        accountDetails.put(ACCOUNT_TYPE, account.getType());
        accountDetails.put(ACCOUNT_ID, account.getAccountID());
        accountDetails.put(ACCOUNT_FIRST_NAME, account.getFirstName());
        accountDetails.put(ACCOUNT_LAST_NAME, account.getLastName());
        accountDetails.put(ACCOUNT_BIRTH_DATE, account.getBirthDate());
        accountDetails.put(ACCOUNT_USERNAME, account.getUsername());
        accountDetails.put(ACCOUNT_PASSWORD, account.getPassword());
        accountDetails.put(ACCOUNT_EMAIL, account.getEmail());
        accountDetails.put(ACCOUNT_PHONE, account.getPhone());
        accountDetails.put(ACCOUNT_STUDENT_ID, account.getStudentID());
        //accountDetails.put(ACCOUNT_FAVORITES, getPropertyIDs(account.getFavorites()));
        int[] favoritesIDs = getPropertyIDs(account.getFavorites());
        JSONArray arrFavorites = new JSONArray();
        for(int i = 0; i < favoritesIDs.length; i++) {
            arrFavorites.add(i,favoritesIDs[i]);
        }
        accountDetails.put(ACCOUNT_FAVORITES, arrFavorites);


        //accountDetails.put(ACCOUNT_REVIEWS, getReviewIDs(account.getReviews()));
        int[] reviewIDs = getReviewIDs(account.getReviews());
        JSONArray arrReviews = new JSONArray();
        for(int i = 0; i < reviewIDs.length; i++) {
            arrReviews.add(i,reviewIDs[i]);
        }
        accountDetails.put(ACCOUNT_REVIEWS, arrReviews);

        return accountDetails;
    }

    public static JSONObject getAgentJSON(Account account) {
        JSONObject accountDetails = new JSONObject();
        accountDetails.put(ACCOUNT_TYPE, account.getType());
        accountDetails.put(ACCOUNT_ID, account.getAccountID());
        accountDetails.put(ACCOUNT_FIRST_NAME, account.getFirstName());
        accountDetails.put(ACCOUNT_LAST_NAME, account.getLastName());
        accountDetails.put(ACCOUNT_BIRTH_DATE, account.getBirthDate());
        accountDetails.put(ACCOUNT_USERNAME, account.getUsername());
        accountDetails.put(ACCOUNT_PASSWORD, account.getPassword());
        accountDetails.put(ACCOUNT_EMAIL, account.getEmail());
        accountDetails.put(ACCOUNT_PHONE, account.getPhone());
        accountDetails.put(ACCOUNT_COMPANY, account.getCompany());
        //accountDetails.put(ACCOUNT_PROPERTIES, getPropertyIDs(account.getProperties()));
        int[] propertiesIDs = getPropertyIDs(account.getProperties());
        JSONArray arrProperties = new JSONArray();
        for(int i = 0; i < propertiesIDs.length; i++) {
            arrProperties.add(i,propertiesIDs[i]);
        }
        accountDetails.put(ACCOUNT_FAVORITES, arrProperties);


        //accountDetails.put(ACCOUNT_REVIEWS, getReviewIDs(account.getReviews()));
        int[] reviewIDs = getReviewIDs(account.getReviews());
        JSONArray arrReviews = new JSONArray();
        for(int i = 0; i < reviewIDs.length; i++) {
            arrReviews.add(i,reviewIDs[i]);
        }
        accountDetails.put(ACCOUNT_REVIEWS, arrReviews);

        return accountDetails;
    }

    public static JSONObject getReviewJSON(Review review) {
        JSONObject reviewDetails = new JSONObject();
        reviewDetails.put(REVIEW_ID, review.getReviewID());
        reviewDetails.put(REVIEW_SUBJECT_ID, review.getSubjectID());
        reviewDetails.put(REVIEW_AUTHOR_ID, review.getAuthorID());
        reviewDetails.put(REVIEW_RATING, review.getRating());
        reviewDetails.put(REVIEW_FEEDBACK, review.getFeedback());

        return reviewDetails;
    }

    public static int[] getReviewIDs(ArrayList<Review> reviews) {
        int[] reviewIDs = new int[reviews.size()];
        int i = 0;
        for(Review review : reviews) {
            reviewIDs[i] = review.getReviewID();
            i++;
        }
        return reviewIDs;
    }

    public static int[] getPropertyIDs(ArrayList<Property> properties) {
        int[] propertyIDs = new int[properties.size()];
        int i = 0;
        for(Property property : properties) {
            propertyIDs[i] = property.getListingID();
            i++;
        }
        return propertyIDs;
    }
}