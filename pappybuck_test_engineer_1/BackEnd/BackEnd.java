// --== CS400 File Header Information ==--
// Name: Luis Cazarin Quiroga
// Email: cazarinquiro@wisc.edu
// Team: BC
// TA: Brianna Cochran
// Lecturer: Florian Heimerl
// Notes to Grader:

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BackEnd {

    public static HashTableMap<Airbnb, Airbnb> airbnbDatabase;
    public static HashTableMap<String, Airbnb[]> cityDatabase;
    public static ArrayList<String> cityList = new ArrayList<String>();

    private class Airbnb { // Class for objects we will store in hashtable
        String name;
        String type;
        String location; // city
        String state;
        int price;
        int minNights; // whether it is reserved
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

            file = new File("Cities.csv");
            scnr = new Scanner(file);

            while (scnr.hasNextLine()) {
                scnr.next(); // skip over id
                cityList.add(scnr.next()); // add city to city list
            }

            Airbnb cityAirbnbList[]; // list of airbnbs in a city

            for (int i = 0; i < cityList.size(); i++) {
                cityAirbnbList = loadCity(cityList.get(i));
                cityDatabase.put(cityList.get(i), cityAirbnbList);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } finally {
            if (scnr != null) {
                scnr.close();
            }
        }
    }

    /**
     * Loads a specific city CSV file
     */
    public static Airbnb[] loadCity(String city) throws FileNotFoundException {
        File file = new File(city + ".csv");
        Scanner scnr = new Scanner(file);
        // TODO: Use scnr or stream to load city data
        
        return null;
    }


    /*
     * Returns the string representation of an Airbnb
     */
    public String get(Airbnb airbnb) {
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
    public Airbnb[] find(String city) {
        if (city == null || !cityList.contains(city)) {
            return null; // city not in city list
        }
        // need data to finish this - need a hashtable of cities mapped to airbnb arrays
        return cityDatabase.get(city); // returns an array of Airbnbs in this city
    }

    /*
     * Returns a list of all available cities
     */
    public String listCities() {
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
    public String randomCity() {
        Random rand = new Random();
        int randInt = rand.nextInt(cityList.size()) + 1; // generates a random number

        return cityList.get(randInt);
    }

}

