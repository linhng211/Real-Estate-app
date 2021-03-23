import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {

    public static ArrayList<Property> loadProperties() {
        ArrayList<Property> propertyList = new ArrayList<Property>();

        try {
            FileReader reader = new FileReader(PROPERTIES_FILE_NAME);
            new JSONParser();
            JSONArray propertiesJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0; i < propertiesJSON.size(); i++) {
                JSONObject propertyJSON = (JSONObject)propertiesJSON.get(i);
                String type = (String)propertyJSON.get(PROPERTIES_TYPE);
                long _listingID = (long)propertyJSON.get(PROPERTIES_LISTING_ID);
                int listingID = (int)_listingID;
                String address = (String)propertyJSON.get(PROPERTIES_ADDRESS);
                double proximity = (double)propertyJSON.get(PROPERTIES_PROXIMITY);
                long _size = (long)propertyJSON.get(PROPERTIES_SIZE);
                int size = (int)_size;
                double price = (double)propertyJSON.get(PROPERTIES_PRICE);
                long _bed = (long)propertyJSON.get(PROPERTIES_BED);
                int bed = (int)_bed;
                double bath = (double)propertyJSON.get(PROPERTIES_BATH);
                long _propertyManagerID = (long)propertyJSON.get(PROPERTIES_PROPERTY_MANAGER_ID);
                int propertyManagerID = (int)_propertyManagerID;
                long _realEstateAgentID = (long)propertyJSON.get(PROPERTIES_REAL_ESTATE_AGENT_ID);
                int realEstateAgentID = (int)_realEstateAgentID;
                boolean isAvailable = (boolean)propertyJSON.get(PROPERTIES_IS_AVAILABLE);
                boolean airConditioning = (boolean)propertyJSON.get(PROPERTIES_AIR_CONDITIONING);
                boolean pool = (boolean)propertyJSON.get(PROPERTIES_POOL);
                boolean parking = (boolean)propertyJSON.get(PROPERTIES_PARKING);
                boolean handicapAccessible = (boolean)propertyJSON.get(PROPERTIES_HANDICAP_ACCESSIBLE);
                boolean petsAllowed = (boolean)propertyJSON.get(PROPERTIES_PETS_ALLOWED);
                boolean furnished = (boolean)propertyJSON.get(PROPERTIES_FURNISHED);
                boolean dishwasher = (boolean)propertyJSON.get(PROPERTIES_DISHWASHER);
                boolean microwave = (boolean)propertyJSON.get(PROPERTIES_MICROWAVE);
                boolean cable = (boolean)propertyJSON.get(PROPERTIES_CABLE);
                boolean garage = (boolean)propertyJSON.get(PROPERTIES_GARAGE);
                boolean internet = (boolean)propertyJSON.get(PROPERTIES_INTERNET);
                boolean gym = (boolean)propertyJSON.get(PROPERTIES_GYM);
                boolean laundry = (boolean)propertyJSON.get(PROPERTIES_LAUNDRY);
                JSONArray _reviews = (JSONArray)propertyJSON.get(PROPERTIES_REVIEWS);
                int[] reviews = new int[_reviews.size()];
                for(int j = 0; j < reviews.length; j++) {
                    long _var = (long)_reviews.get(j);
                    int var = (int)_var;
                    reviews[j] = var;
                }

                double lotSize;

                switch (type) {
                    case "HOUSE":
                        lotSize = (double)propertyJSON.get(PROPERTIES_LOT_SIZE);

                        House house = new House(listingID, address, proximity, size, price, bed, bath, propertyManagerID, realEstateAgentID, lotSize);
                        house.setAvailable(isAvailable);
                        if(airConditioning) house.setAmenities("airConditioning");
                        if(pool) house.setAmenities("pool");
                        if(parking) house.setAmenities("parking");
                        if(handicapAccessible) house.setAmenities("handicapAccessible");
                        if(petsAllowed) house.setAmenities("petsAllowed");
                        if(furnished) house.setAmenities("furnished");
                        if(dishwasher) house.setAmenities("dishwasher");
                        if(microwave) house.setAmenities("microwave");
                        if(cable) house.setAmenities("cable");
                        if(garage) house.setAmenities("garage");
                        if(internet) house.setAmenities("internet");
                        if(gym) house.setAmenities("gym");
                        if(laundry) house.setAmenities("laundry");
                        house.setReviews(getReviews(reviews, loadReviews()));

                        propertyList.add(house);

                        break;
                    case "TOWNHOUSE":
                        lotSize = (double)propertyJSON.get(PROPERTIES_LOT_SIZE);
                        long _sharedWalls = (long)propertyJSON.get(PROPERTIES_SHARED_WALLS);
                        int sharedWalls = (int)_sharedWalls;

                        Townhouse townhouse = new Townhouse(listingID, address, proximity, size, price, bed, bath, propertyManagerID, realEstateAgentID, lotSize, sharedWalls);
                        townhouse.setAvailable(isAvailable);
                        if(airConditioning) townhouse.setAmenities("airConditioning");
                        if(pool) townhouse.setAmenities("pool");
                        if(parking) townhouse.setAmenities("parking");
                        if(handicapAccessible) townhouse.setAmenities("handicapAccessible");
                        if(petsAllowed) townhouse.setAmenities("petsAllowed");
                        if(furnished) townhouse.setAmenities("furnished");
                        if(dishwasher) townhouse.setAmenities("dishwasher");
                        if(microwave) townhouse.setAmenities("microwave");
                        if(cable) townhouse.setAmenities("cable");
                        if(garage) townhouse.setAmenities("garage");
                        if(internet) townhouse.setAmenities("internet");
                        if(gym) townhouse.setAmenities("gym");
                        if(laundry) townhouse.setAmenities("laundry");
                        townhouse.setReviews(getReviews(reviews, loadReviews()));

                        propertyList.add(townhouse);

                        break;
                    case "APARTMENT":
                        boolean studio = (boolean)propertyJSON.get(PROPERTIES_STUDIO);

                        Apartment apartment = new Apartment(listingID, address, proximity, size, price, bed, bath, propertyManagerID, realEstateAgentID, studio);
                        apartment.setAvailable(isAvailable);
                        if(airConditioning) apartment.setAmenities("airConditioning");
                        if(pool) apartment.setAmenities("pool");
                        if(parking) apartment.setAmenities("parking");
                        if(handicapAccessible) apartment.setAmenities("handicapAccessible");
                        if(petsAllowed) apartment.setAmenities("petsAllowed");
                        if(furnished) apartment.setAmenities("furnished");
                        if(dishwasher) apartment.setAmenities("dishwasher");
                        if(microwave) apartment.setAmenities("microwave");
                        if(cable) apartment.setAmenities("cable");
                        if(garage) apartment.setAmenities("garage");
                        if(internet) apartment.setAmenities("internet");
                        if(gym) apartment.setAmenities("gym");
                        if(laundry) apartment.setAmenities("laundry");
                        apartment.setReviews(getReviews(reviews, loadReviews()));

                        propertyList.add(apartment);

                        break;
                }
            }

            return propertyList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Account> loadAccounts() {
        ArrayList<Account> accountList = new ArrayList<Account>();

        try {
            FileReader reader = new FileReader(ACCOUNT_FILE_NAME);
            new JSONParser();
            JSONArray accountsJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0; i < accountsJSON.size(); i++) {
                JSONObject accountJSON = (JSONObject)accountsJSON.get(i);
                String type = (String)accountJSON.get(ACCOUNT_TYPE);
                long _id = (long)accountJSON.get(ACCOUNT_ID);
                int id = (int)_id;
                String firstName = (String)accountJSON.get(ACCOUNT_FIRST_NAME);
                String lastName = (String)accountJSON.get(ACCOUNT_LAST_NAME);
                String birthDate = (String)accountJSON.get(ACCOUNT_BIRTH_DATE);
                String username = (String)accountJSON.get(ACCOUNT_USERNAME);
                String password = (String)accountJSON.get(ACCOUNT_PASSWORD);
                String email = (String)accountJSON.get(ACCOUNT_EMAIL);
                String phone = (String)accountJSON.get(ACCOUNT_PHONE);
                JSONArray _reviews = (JSONArray)accountJSON.get(ACCOUNT_REVIEWS);
                int[] reviews = new int[_reviews.size()];
                for(int j = 0; j < reviews.length; j++) {
                    long _var = (long)_reviews.get(j);
                    int var = (int)_var;
                    reviews[j] = var;
                }



                switch (type) {
                    case "STUDENT":
                        String studentID = (String)accountJSON.get(ACCOUNT_STUDENT_ID);
                        Student student = new Student(id, firstName, lastName, birthDate, username, password, email, phone, studentID);
                        JSONArray _favorites = (JSONArray)accountJSON.get(ACCOUNT_FAVORITES);
                        if(_favorites != null) {
                            int[] favorites = new int[_favorites.size()];
                            for (int j = 0; j < favorites.length; j++) {
                                long _var = (long) _favorites.get(j);
                                int var = (int) _var;
                                favorites[j] = var;
                            }
                            student.setFavorites(getProperties(favorites));
                        }
                        student.setReviews(getReviews(reviews, loadReviews()));
                        accountList.add(student);

                        break;

                    case "AGENT":
                        String company = (String)accountJSON.get(ACCOUNT_COMPANY);
                        Agent agent = new Agent(id, firstName, lastName, birthDate, username, password, email, phone, company);
                        JSONArray _properties = (JSONArray)accountJSON.get(ACCOUNT_PROPERTIES);
                        if(_properties != null) {
                            int[] properties = new int[_properties.size()];
                            for(int j = 0; j < properties.length; j++) {
                                long _var = (long)_properties.get(j);
                                int var = (int)_var;
                                properties[j] = var;
                            }
                            agent.setProperties(getProperties(properties));
                        }

                        agent.setReviews(getReviews(reviews, loadReviews()));


                        accountList.add(agent);

                        break;
                }
            }

            return accountList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Review> loadReviews() {
        ArrayList<Review> reviewList = new ArrayList<Review>();

        try {
            FileReader reader = new FileReader(REVIEW_FILE_NAME);
            new JSONParser();
            JSONArray reviewsJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0; i < reviewsJSON.size(); i++) {
                JSONObject reviewJSON = (JSONObject)reviewsJSON.get(i);
                long _reviewID = (long)reviewJSON.get(REVIEW_ID);
                int reviewID = (int)_reviewID;
                long _subjectID = (long)reviewJSON.get(REVIEW_SUBJECT_ID);
                int subjectID = (int)_subjectID;
                long _authorID = (long)reviewJSON.get(REVIEW_AUTHOR_ID);
                int authorID = (int)_authorID;
                long _rating = (long)reviewJSON.get(REVIEW_RATING);
                int rating = (int)_rating;
                String feedback = (String)reviewJSON.get(REVIEW_FEEDBACK);

                Review review = new Review(reviewID, subjectID, authorID, rating, feedback);

                reviewList.add(review);
            }

            return reviewList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Review> getReviews(int[] arrReviews, ArrayList<Review> reviews) {
        ArrayList<Review> reviewList = new ArrayList<Review>();
        for(int reviewID : arrReviews) {
            for(Review review : reviews) {
                if(review.getReviewID() == reviewID) {
                    reviewList.add(review);
                }
            }
        }
        return reviewList;
    }

    public static ArrayList<Property> getProperties(int[] arrProperties) {
        ArrayList<Property> propertyList = new ArrayList<Property>();
        for(int propertyID : arrProperties) {
            for(Property property : propertyList) {
                if(property.getListingID() == propertyID) {
                    propertyList.add(property);
                }
            }
        }
        return propertyList;
    }
}