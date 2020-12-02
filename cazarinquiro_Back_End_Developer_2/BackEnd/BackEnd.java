// --== CS400 File Header Information ==--
// Name: Luis Cazarin Quiroga
// Email: cazarinquiro@wisc.edu
// Team: BC
// TA: Brianna Cochran
// Lecturer: Florian Heimerl
// Notes to Grader:


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import com.opencsv.*; // used to read CSV - with a scanner or stream, can't read elements w/ double quotes




// All methods are static so that front end does not need to initialize a BackEnd object, just call static methods

public class BackEnd {

    public static void main(String[] args) throws IOException { // testing
        loadCities();
        System.out.println(listCities());
        

    }

    public static HashTableMap<Airbnb, Airbnb> airbnbDatabase = new HashTableMap<Airbnb, Airbnb>();;
    public static HashTableMap<String, ArrayList<Airbnb>> cityDatabase;
    public static ArrayList<String> cityList = new ArrayList<String>();
    public static ArrayList<String> stateList = new ArrayList<String>();

    private static class Airbnb { // Class for objects we will store in hashtable
        String name;
        String type;
        String location; // city
        String state;
        int price;
        int minNights;
        int reviews; // 1-5, where 5 makes it a top hit

        private Airbnb(String name, String location, String type, String state, int price,
            int minNights, int rating) {
            this.name = name;
            this.type = type;
            this.state = state;
            this.location = location;
            this.price = price;
            this.minNights = minNights;
            this.reviews = rating;
        }

        private String getLocation() {
            return this.location;
        }

        private String getType() {
            return this.type;
        }

        private int getPrice() {
            return this.price;
        }

        private int getMinNights() {
            return this.minNights;
        }

        private int getReviews() {
            return this.reviews;
        }

        private String getName() {
            return this.name;
        }

        private String getState() {
            return this.state;
        }
    }

    /*
     * Loads data from CSV files
     */
    public static void loadCities() {
        File file = null;
        Scanner scnr = null;

        try {

            file = new File("src/Data/Cities/Cities.csv");
            scnr = new Scanner(file);
            scnr.nextLine();
            CSVParser parser = new CSVParser();
            String[] cityInfo;
            
            while (scnr.hasNextLine()) {
                cityInfo = parser.parseLine(scnr.nextLine());
                
                cityList.add(cityInfo[1]); // add city to city list
                stateList.add(cityInfo[2]); // add corresponding state
            }

            ArrayList<Airbnb> cityAirbnbList; // list of airbnbs in a city

            for (int i = 0; i < cityList.size(); i++) {
                cityAirbnbList = loadCity(cityList.get(i), stateList.get(i)); // currently an exception thrown here
                // TODO: fix Nullptr being thrown here
                cityDatabase.put(cityList.get(i), cityAirbnbList);
            }

        } catch (FileNotFoundException e) { 
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        } finally {
            if (scnr != null) {
                scnr.close();
            }
        }
    }

    /**
     * Loads a specific city CSV file
     * @throws IOException 
     */
    public static ArrayList<Airbnb> loadCity(String city, String state)
        throws IOException {
        File file = new File("src/Data/" + city + ".csv");
        Scanner scnr = new Scanner(file);
        scnr.nextLine(); // skip over titles
        scnr.useDelimiter(",");
        String[] airbnbInfo;
        
        String name;
        String state2 = state;
        String type;
        String location = city;
        int minNights;
        int price;
        int reviews;

        ArrayList<Airbnb> listOfAirbnbs = new ArrayList<Airbnb>();
        
        CSVParser parser = new CSVParser();
       


        while (scnr.hasNextLine()) {
            airbnbInfo = parser.parseLine(scnr.nextLine()); // parse one line at a time

            name = airbnbInfo[1]; // get name
            type = airbnbInfo[8]; // get room type
            price = Integer.parseInt(airbnbInfo[9]); // get price
            minNights = Integer.parseInt(airbnbInfo[10]); // get minimum nights
            reviews = Integer.parseInt(airbnbInfo[11]); // get number of reviews
            
            Airbnb airbnb = new Airbnb(name, location, type, state2, price, minNights, reviews);
            listOfAirbnbs.add(airbnb);
            airbnbDatabase.put(airbnb, airbnb); // for hashtable, airbnb object is key & value
        } 

        if (scnr != null) {
            scnr.close();
        }

        return listOfAirbnbs;
    }


    /*
     * Returns the string representation of an Airbnb
     */
    public static String get(Airbnb airbnb) {
        if (airbnb == null || !airbnbDatabase.containsKey(airbnb)) {
            return null; // airbnb not in database
        }

        return airbnb.getName() + ", " + airbnb.getType() + ", " + airbnb.getLocation() + ", "
            + airbnb.getState() + ", " + airbnb.getPrice() + ", " + airbnb.getMinNights() + ", "
            + airbnb.getReviews() + ".";
    }

    /*
     * Returns an Airbnb array of all airbnbs in a city
     */
    public static ArrayList<Airbnb> find(String city) {
        if (city == null || !cityList.contains(city)) {
            return null; // city not in city list
        }
        // need data to finish this - need a hashtable of cities mapped to airbnb arrays
        return cityDatabase.get(city); // returns an array of Airbnbs in this city
    }

    /*
     * Returns a list of all available cities
     */
    public static String listCities() {
        String cities = "";


        for (int i = 0; i < cityList.size(); i++) {
            cities += cityList.get(i);

            if (i != cityList.size() - 1) {
                cities += ", "; // adds comma separation except for last element added
            }
        }


        return cities;
    }

    /*
     * Returns the string representation of a random city
     */
    public static String randomCity() {
        Random rand = new Random();
        int randInt = rand.nextInt(cityList.size()) + 1; // generates a random number

        return cityList.get(randInt);
    }

}

