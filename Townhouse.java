public class Townhouse extends Property {
    private double lotSize;
    private int sharedWalls;
    private String type = "TOWNHOUSE";

    public Townhouse(int listingID, String address, double proximity, int size, double price, int bed, double bath, int propertyManagerID, int realEstateAgentID, double lotSize, int sharedWalls) {
        super(listingID, address, proximity, size, price, bed, bath, propertyManagerID, realEstateAgentID);
        this.lotSize = lotSize;
        this.sharedWalls = sharedWalls;
    }

    public double getLotSize() {
        return lotSize;
    }

    public void setLotSize(double lotSize) {
        this.lotSize = lotSize;
    }

    public int getSharedWalls() {
        return sharedWalls;
    }

    public boolean getStudio() {
        return false;
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
        String available = "";
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
                "Lot Size: " + lotSize +  " acres\n" +
                "Shared Walls: " + sharedWalls +  '\n' +
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