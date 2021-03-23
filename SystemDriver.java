import java.util.ArrayList;
import java.util.Scanner;

public class SystemDriver {
    public static Properties allListings = Properties.getInstance();
    public static Accounts accounts = Accounts.getInstance();
    public static Reviews reviews = Reviews.getInstance();

    public static void main(String[] args) {

        Account current = null;
        Scanner scan = new Scanner(System.in);

        String response = "";

        System.out.println("* * * * * Welcome to Omniscient * * * * *\n");

        while(current == null) {
            System.out.print("Do you already have an account? (Y/N) : ");
            String registered = scan.nextLine();
            if(registered.equalsIgnoreCase("y")) {
                System.out.println("\n * * * * Login * * * * \n");
                System.out.print("Please enter your username : ");
                String username = scan.nextLine();
                System.out.print("Please enter your password : ");
                String password = scan.nextLine();
                current = Accounts.login(username, password);
            } else if(registered.equalsIgnoreCase("n")) {
                System.out.print("Do you want to create an account? (Y/N) : ");
                String wantNew = scan.nextLine();
                if (wantNew.equalsIgnoreCase("y")) {
                    System.out.println("\n * * * * Create an Account * * * * \n");
                    System.out.print("Are you a student? (Y/N): ");
                    String type = scan.nextLine();
                    int id = Accounts.getInstance().getAccounts().get(Accounts.getInstance().getAccounts().size() - 1).getAccountID() + 1;
                    System.out.print("Create a Username: ");
                    String username = scan.nextLine();
                    System.out.print("Create a Password: ");
                    String password = scan.nextLine();
                    System.out.print("What is your email?: ");
                    String email = scan.nextLine();
                    System.out.print("What is your phone number?: ");
                    String phone = scan.nextLine();
                    System.out.print("What is your first name?: ");
                    String firstName = scan.nextLine();
                    System.out.print("What is your last name?: ");
                    String lastName = scan.nextLine();
                    System.out.print("What is your birth date?: ");
                    String birthDate = scan.nextLine();

                    if(type.equalsIgnoreCase("y")) {
                        System.out.print("Please enter your Student ID to verify: ");
                        String studentID = scan.nextLine();
                        current = new Student(id, firstName, lastName, birthDate, username, password, email, phone, studentID);
                    } else if(!type.equalsIgnoreCase("y")) {
                        System.out.print("What's the name of your company?: ");
                        String company = scan.nextLine();
                        current = new Agent(id, firstName, lastName, birthDate, username, password, email, phone, company);
                    }

                    Accounts.addAccount(email, current);
                    System.out.println("Welcome, " + current.getFirstName() + ' ' + current.getLastName());
                } else {
                    break;
                }
            }
        }

        if(current == null) System.out.println("\nYou are not logged in.\n");


        System.out.println("What would you like to do?");
        while(!response.equals("9")) {
            System.out.println("1. Browse Properties");
            System.out.println("2. Search Properties");
            System.out.println("3. Rent a Property");
            if(current != null && current.getType().equals("AGENT")) {
                System.out.println("4. List a Property");
                System.out.println("5. Remove a Property");
            }
            System.out.println("9. Quit");
            response = scan.nextLine();
            switch(response) {
                case ("1"):
                    System.out.println("\n * * * * Property Browser * * * * \n");
                    for (Property property : Properties.getProperties()) {
                        System.out.println(property.toString());
                    }
                    break;

                case ("2"):
                    System.out.println("\n * * * * Property Search * * * * \n");
                    System.out.print("Are you looking for a HOUSE, a TOWNHOUSE, or an APARTMENT?: ");
                    String type = scan.nextLine();
                    System.out.print("How many miles away from campus are you willing to live?: ");
                    String _proximity = scan.nextLine();
                    double proximity = Double.parseDouble(_proximity);
                    System.out.print("What is the minimum square footage that you are looking for?: ");
                    String _size = scan.nextLine();
                    int size = Integer.parseInt(_size);
                    System.out.print("What is your monthly budget?: $");
                    String _price = scan.nextLine();
                    int price = Integer.parseInt(_price);
                    System.out.print("How many bedrooms do you want?: ");
                    String _bed = scan.nextLine();
                    int bed = Integer.parseInt(_bed);
                    System.out.print("How many bathrooms do you want?: ");
                    String _bath = scan.nextLine();
                    double bath = Double.parseDouble(_bath);
                    ArrayList<String> searchAmenities = getAmenities("search");
                    for (Property property : Properties.getInstance().searchProperties(proximity, size, price, bed, bath, type, searchAmenities)) {
                        if (Properties.getInstance().searchProperties(proximity, size, price, bed, bath, type, searchAmenities).size() == 0) {
                            break;
                        } else {
                            System.out.println(property.toString());
                        }
                    }
                    break;

                case ("3"):
                    if (current == null) {
                        System.out.println("You must be logged in to apply for a lease.");
                        break;
                    } else {
                        System.out.println("\n * * * * Lease a Property * * * * \n");
                        System.out.print("What is the Listing ID of the property that you would like to lease?: ");

                        String _listingID = scan.nextLine();
                        int listingID = Integer.parseInt(_listingID);
                        System.out.println(Properties.getInstance().findPropertyByID(listingID).toString());

                        System.out.print("When you would like to start the lease? (mm/dd/yyyy): ");
                        String startDate = scan.nextLine();
                        String endDate = (startDate.substring(0, startDate.length() - 4) + ((Integer.parseInt(startDate.substring(startDate.length() - 4)) + 1)));
                        System.out.println("Lease term: " + startDate + "-" + endDate);

                        System.out.println("Would you like to put someone else's name on the lease as well? (Y/N): ");
                        String _roommate = scan.nextLine();
                        Account roommate;
                        if (_roommate.equals("y")) {
                            System.out.println("Please enter the username on the account of the person that you would like to live with: ");
                            String roommateUser = scan.nextLine();
                            roommate = Accounts.getInstance().searchAccountUsername(roommateUser);
                        } else {
                            roommate = null;
                        }

                        System.out.print("What is your billing address?: ");
                        String billingAddress = scan.nextLine();

                        Properties.lease(current, Properties.getInstance().findPropertyByID(listingID), startDate, endDate, billingAddress, roommate);
                        break;
                    }
                case ("4"):
                    System.out.println("\n * * * * Create a Listing * * * * \n");
                    System.out.println("What kind of property are you listing? (HOUSE / TOWNHOUSE / APARTMENT): ");
                    String listingType = scan.nextLine();
                    switch(listingType) {
                        case ("HOUSE"):
                            listHouse();
                            break;
                        case ("TOWNHOUSE"):
                            listTownhouse();
                            break;
                        case ("APARTMENT"):
                            System.out.println("What is the name of the apartment complex?: ");
                            String complex = scan.nextLine();
                            ArrayList<String> amenities = getAmenities("list");
                            listApartment(complex, amenities);
                            break;
                    }
                    break;

                case ("5"):
                    System.out.println("What is the ListingID of the property that you would like to remove?");
                    int removeID = scan.nextInt();
                    Properties.getInstance().removeProperty(removeID);
                    break;
            }
        }
//        ArrayList<Account> list = Accounts.getInstance().getAccounts();
//        for(Account account : list) {
//            System.out.println(account.toString());
//        }
//
//        ArrayList<Property> propertyList = Properties.getInstance().getProperties();
//        for(Property property : propertyList) {
//            System.out.println(property.toString());
//        }
//
//        ArrayList<Review> reviewList = Reviews.getInstance().getReviews();
//        for(Review review : reviewList) {
//            System.out.println(review.toString());
//        }

    }

    public static void listHouse() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What is the address of the property?: ");
        String address = scan.nextLine();
        System.out.println("How miles from campus is the property?: ");
        String _prox = scan.nextLine();
        double prox = Double.parseDouble(_prox);
        System.out.println("How many square feet is this unit?: ");
        String _sqft = scan.nextLine();
        int sqft = Integer.parseInt(_sqft);
        System.out.println("What will the monthly rent for this unit be?: ");
        String _cost = scan.nextLine();
        int cost = Integer.parseInt(_cost);
        System.out.println("How many bedrooms are there in this unit?: ");
        String _beds = scan.nextLine();
        int beds = Integer.parseInt(_beds);
        System.out.println("How many bathrooms are there in this unit?: ");
        String _baths = scan.nextLine();
        int baths = Integer.parseInt(_baths);
        System.out.println("How many acres is this lot?: ");
        String _lotSize = scan.nextLine();
        int lotSize = Integer.parseInt(_lotSize);
        System.out.println("What is the username of the property manager?: ");
        String propertyManagerUsername = scan.nextLine();
        int propertyManagerID = Accounts.getInstance().searchAccountUsername(propertyManagerUsername).getAccountID();
        System.out.println("What is the username of the real estate agent?: ");
        String realEstateAgentUsername = scan.nextLine();
        int realEstateAgentID = Accounts.getInstance().searchAccountUsername(realEstateAgentUsername).getAccountID();
        int listingID = Properties.getInstance().getProperties().get(Properties.getInstance().getProperties().size() - 1).getListingID() + 1;
        House house = new House(listingID, address, prox, sqft, cost, beds, baths, propertyManagerID, realEstateAgentID, lotSize);
        ArrayList<String> amenities = getAmenities("list");
        for(String amenity : amenities) {
            house.setAmenities(amenity);
        }
        Properties.getInstance().addProperty(house);
        System.out.println("You have successfully added a new HOUSE listing.");
        System.out.println(house.toString());
    }

    public static void listTownhouse() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What is the address of the property?: ");
        String address = scan.nextLine();
        System.out.println("How miles from campus is the property?: ");
        String _prox = scan.nextLine();
        double prox = Double.parseDouble(_prox);
        System.out.println("How many square feet is this unit?: ");
        String _sqft = scan.nextLine();
        int sqft = Integer.parseInt(_sqft);
        System.out.println("What will the monthly rent for this unit be?: ");
        String _cost = scan.nextLine();
        int cost = Integer.parseInt(_cost);
        System.out.println("How many bedrooms are there in this unit?: ");
        String _beds = scan.nextLine();
        int beds = Integer.parseInt(_beds);
        System.out.println("How many bathrooms are there in this unit?: ");
        String _baths = scan.nextLine();
        int baths = Integer.parseInt(_baths);
        System.out.println("How many walls does this unit share with other units?: ");
        String _sharedWalls = scan.nextLine();
        int sharedWalls = Integer.parseInt(_sharedWalls);
        System.out.println("How many acres is this lot?: ");
        String _lotSize = scan.nextLine();
        int lotSize = Integer.parseInt(_lotSize);
        System.out.println("What is the username of the property manager?: ");
        String propertyManagerUsername = scan.nextLine();
        int propertyManagerID = Accounts.getInstance().searchAccountUsername(propertyManagerUsername).getAccountID();
        System.out.println("What is the username of the real estate agent?: ");
        String realEstateAgentUsername = scan.nextLine();
        int realEstateAgentID = Accounts.getInstance().searchAccountUsername(realEstateAgentUsername).getAccountID();
        int listingID = Properties.getInstance().getProperties().get(Properties.getInstance().getProperties().size() - 1).getListingID() + 1;
        Townhouse townhouse = new Townhouse(listingID, address, prox, sqft, cost, beds, baths, propertyManagerID, realEstateAgentID, sharedWalls, lotSize);
        ArrayList<String> amenities = getAmenities("list");
        for(String amenity : amenities) {
            townhouse.setAmenities(amenity);
        }
        Properties.getInstance().addProperty(townhouse);
        System.out.println("You have successfully added a new HOUSE listing.");
        System.out.println(townhouse.toString());
    }

    public static void listApartment(String complex, ArrayList<String> amenities) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Apartment> newListings = new ArrayList<Apartment>();

        System.out.println("What is the address of " + complex + "?: ");
        String address = scan.nextLine();
        System.out.println("How miles from campus is the property?: ");
        String _prox = scan.nextLine();
        double prox = Double.parseDouble(_prox);
        System.out.print("How many kinds of suites would you like to add for " + complex + "?: ");
        String _number = scan.nextLine();
        int number = Integer.parseInt(_number);
        for(int i = 1; i <= number; i++) {
            System.out.println("You are now entering the information for suite type #" + i);
            System.out.println("How many square feet is this unit?: ");
            String _sqft = scan.nextLine();
            int sqft = Integer.parseInt(_sqft);
            System.out.println("What will the monthly rent for this unit be?: ");
            String _cost = scan.nextLine();
            int cost = Integer.parseInt(_cost);
            System.out.println("Is this a studio apartment?: (Y/N)");
            String _studio = scan.nextLine();
            boolean studio;
            int beds = 0;
            if(_studio.equals("Y")) {
                studio = true;
            } else {
                studio = false;
                System.out.println("How many bedrooms are there in this unit?: ");
                String _beds = scan.nextLine();
                beds = Integer.parseInt(_beds);
            }
            System.out.println("How many bathrooms are there in this unit?: ");
            String _baths = scan.nextLine();
            int baths = Integer.parseInt(_baths);

            System.out.println("What is the username of the property manager?: ");
            String propertyManagerUsername = scan.nextLine();
            int propertyManagerID = Accounts.getInstance().searchAccountUsername(propertyManagerUsername).getAccountID();
            System.out.println("What is the username of the real estate agent?: ");
            String realEstateAgentUsername = scan.nextLine();
            int realEstateAgentID = Accounts.getInstance().searchAccountUsername(realEstateAgentUsername).getAccountID();

            System.out.println("How many units do you have like this?: ");
            String _units = scan.nextLine();
            int units = Integer.parseInt(_units);
            for(int j = 0; j < units; j++) {
                int listingID = Properties.getInstance().getProperties().get(Properties.getInstance().getProperties().size() - 1).getListingID() + 1;
                Apartment apartment = new Apartment(listingID, (complex + ", " + address), prox, sqft, cost, beds, baths, propertyManagerID, realEstateAgentID, studio);
                for(String amenity : amenities) {
                    apartment.setAmenities(amenity);
                }
                Properties.getInstance().addProperty(apartment);
                newListings.add(apartment);
            }
            System.out.println("You have successfully added the " + complex + " apartment complex with " + newListings.size() + " new units.");
        }
        for(Apartment apartment : newListings) {
            System.out.println(apartment.toString());
        }
    }

    public static ArrayList<String> getAmenities(String purpose) {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> amenitiesList = new ArrayList<String>();
        if(purpose.equalsIgnoreCase("search")) {
            System.out.println("Please indicate all of the amenities that you would like to have. (ex. 135 -> Air Co)");
        } else if(purpose.equalsIgnoreCase("list")) {
            System.out.println("Please indicate all of the amenities included with the property. (ex. 135 -> Air Co)");
        }
        System.out.println("(ex. acf -> (Air Conditioning, Parking, Furnished)");
        //AIR_CONDITIONING, POOL, PARKING, HANDICAP_ACCESSIBLE, PETS_ALLOWED, FURNISHED, DISHWASHER, MICROWAVE, CABLE, GARAGE, INTERNET, GYM
        System.out.println("a. Air Conditioning\nb. Pool\nc. Parking\nd. Handicap Accessible\ne. Pets Allowed\nf. Furnished\ng. Dishwasher\nh. Microwave\ni. Cable\nj. Garage\nk. Internet\nl. Gym\nm. Laundry");
        String amenities = scan.nextLine();
        for(int i = 0; i < amenities.length(); i++) {
            if(amenities.charAt(i) == 'a') {
                amenitiesList.add("AIRCONDITIONING");
            } else if(amenities.charAt(i) == 'b') {
                amenitiesList.add("POOL");
            } else if(amenities.charAt(i) == 'c') {
                amenitiesList.add("PARKING");
            } else if(amenities.charAt(i) == 'd') {
                amenitiesList.add("HANDICAPACCESSIBLE");
            } else if(amenities.charAt(i) == 'e') {
                amenitiesList.add("PETSALLOWED");
            } else if(amenities.charAt(i) == 'f') {
                amenitiesList.add("FURNISHED");
            } else if(amenities.charAt(i) == 'g') {
                amenitiesList.add("DISHWASHER");
            } else if(amenities.charAt(i) == 'h') {
                amenitiesList.add("MICROWAVE");
            } else if(amenities.charAt(i) == 'i') {
                amenitiesList.add("CABLE");
            } else if(amenities.charAt(i) == 'j') {
                amenitiesList.add("GARAGE");
            } else if(amenities.charAt(i) == 'k') {
                amenitiesList.add("INTERNET");
            } else if(amenities.charAt(i) == 'l') {
                amenitiesList.add("GYM");
            } else if(amenities.charAt(i) == 'm') {
                amenitiesList.add("LAUNDRY");
            }
        }
        return amenitiesList;
    }
}