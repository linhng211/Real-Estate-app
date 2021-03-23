public class Apartment extends Property {
    private boolean studio;
    private String type = "APARTMENT";

    public Apartment(int listingID, String address, double proximity, int size, double price, int bed, double bath, int propertyManagerID, int realEstateAgentID, boolean studio) {
        super(listingID, address, proximity, size, price, bed, bath, propertyManagerID, realEstateAgentID);
        this.studio = studio;
    }

    public double getLotSize() {
        return -1;
    }

    public int getSharedWalls() {
        return -1;
    }

    public boolean getStudio() {
        return studio;
    }

    public String getType() {
        return type;
    }

    public boolean doesMatch(double proximity, int size, double price, int bed, double bath, String type) {
        boolean ret = true;

        if(!this.getType().equals(type.toUpperCase())) ret = false;
        if(this.getProximity() > proximity) ret = false;
        if(this.getSize() < size) ret = false;
        if(this.getPrice() > price) ret = false;
        if(this.getBed() < bed) ret = false;
        if(this.getBath() < bath) ret = false;

        return ret;
    }

    public String toString() {
        String isStudio = "";
        String available = "";
        if(studio) isStudio = "Yes";
        if(!studio) isStudio = "No";
        if(isAvailable) available = "Yes";
        if(!isAvailable) available = "No";
        String ret = "Type: " + type +  '\n' +
                "ListingID: " + listingID + '\n' +
                "Address: " + address + '\n' +
                "Proximity: " + proximity + " miles from campus\n" +
                "Size: " + size + " sqft.\n" +
                "Price: $" + price + " per month\n" +
                "Beds: " + bed + '\n' +
                "Bath: " + bath +  '\n' +
                "Studio: " + isStudio + '\n' +
                "Property Manager: " + Accounts.getInstance().searchAccountID(propertyManagerID).getUsername() + '\n' +
                "Real Estate Agent: " + Accounts.getInstance().searchAccountID(realEstateAgentID).getUsername() + '\n' +
                "Available Now: " + available + '\n' + "Amenities:\n";
        for(Amenities amenity : amenities) {
            int space = 0;
            String amen = amenity.toString();
            for(int i = 0; i < amenity.toString().length(); i++) {
                if(amen.charAt(i) == '_') {
                    ret += (("\t* " + amen.substring(0,i) + ' ' + amen.substring(i + 1)).toLowerCase() + '\n');
                    space++;
                }
            }
            if(space == 0) ret += ("\t* " + amenity.toString().toLowerCase()  + '\n');
        }
        return ret + "\n";
    }
}