import java.util.ArrayList;

public abstract class Property {
    protected int listingID;
    protected String address;
    protected double proximity;
    protected int size;
    protected double price;
    protected int bed;
    protected double bath;
    protected int propertyManagerID;
    protected int realEstateAgentID;
    protected boolean isAvailable;

    protected ArrayList<Amenities> amenities;
    protected ArrayList<Review> reviews;

    protected abstract boolean doesMatch(double proximity, int size, double price, int bed, double bath, String type);
    protected abstract String getType();
    protected abstract double getLotSize();
    protected abstract int getSharedWalls();
    protected abstract boolean getStudio();


    public Property(int listingID, String address, double proximity, int size, double price, int bed, double bath, int propertyManagerID, int realEstateAgentID) {
        this.listingID = listingID;
        this.address = address;
        this.proximity = proximity;
        this.size = size;
        this.price = price;
        this.bed = bed;
        this.bath = bath;
        this.propertyManagerID = propertyManagerID;
        this.realEstateAgentID = realEstateAgentID;
        isAvailable = true;
        amenities = new ArrayList<Amenities>();
        reviews = new ArrayList<Review>();
    }

    public int getListingID() {
        return listingID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getProximity() {
        return proximity;
    }

    public void setProximity(double proximity) {
        this.proximity = proximity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBed() {
        return bed;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }

    public double getBath() {
        return bath;
    }

    public void setBath(double bath) {
        this.bath = bath;
    }

    public int getPropertyManagerID() {
        return propertyManagerID;
    }

    public void setPropertyManager(int propertyManagerID) {
        this.propertyManagerID = propertyManagerID;
    }

    public int getRealEstateAgentID() {
        return realEstateAgentID;
    }

    public void setRealEstateAgent(int realEstateAgentID) {
        this.realEstateAgentID = realEstateAgentID;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public ArrayList<Amenities> getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenity) {
        if(amenity.equalsIgnoreCase("airConditioning")) {
            amenities.add(Amenities.AIR_CONDITIONING);
        } else if(amenity.equalsIgnoreCase("pool")) {
            amenities.add(Amenities.POOL);
        } else if(amenity.equalsIgnoreCase("parking")) {
            amenities.add(Amenities.PARKING);
        } else if(amenity.equalsIgnoreCase("handicapAccessible")) {
            amenities.add(Amenities.HANDICAP_ACCESSIBLE);
        } else if(amenity.equalsIgnoreCase("petsAllowed")) {
            amenities.add(Amenities.PETS_ALLOWED);
        } else if(amenity.equalsIgnoreCase("furnished")) {
            amenities.add(Amenities.FURNISHED);
        } else if(amenity.equalsIgnoreCase("dishwasher")) {
            amenities.add(Amenities.DISHWASHER);
        } else if(amenity.equalsIgnoreCase("microwave")) {
            amenities.add(Amenities.MICROWAVE);
        } else if(amenity.equalsIgnoreCase("cable")) {
            amenities.add(Amenities.CABLE);
        } else if(amenity.equalsIgnoreCase("garage")) {
            amenities.add(Amenities.GARAGE);
        } else if(amenity.equalsIgnoreCase("internet")) {
            amenities.add(Amenities.INTERNET);
        } else if(amenity.equalsIgnoreCase("gym")) {
            amenities.add(Amenities.GYM);
        } else if(amenity.equalsIgnoreCase("laundry")) {
            amenities.add(Amenities.LAUNDRY);
        }
    }

    public boolean hasAmenity(String checkAmenity) {
        for(Amenities amenity : amenities) {
            if(amenity.toString().equalsIgnoreCase(checkAmenity)) return true;
        }
        return false;
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
        return "Property{" +
                "listingID=" + listingID +
                ", address='" + address + '\'' +
                ", proximity=" + proximity +
                ", size=" + size +
                ", price=" + price +
                ", bed=" + bed +
                ", bath=" + bath +
                ", propertyManager=" + propertyManagerID +
                ", realEstateAgent=" + realEstateAgentID +
                ", isAvailable=" + isAvailable +
                ", amenities=" + amenities +
                ", reviews=" + reviews +
                '}';
    }
}