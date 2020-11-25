// --== CS400 File Header Information ==--
// Name: Luis Cazarin Quiroga
// Email: cazarinquiro@wisc.edu
// Team: BC
// TA: Brianna Cochran
// Lecturer: Florian Heimerl
// Notes to Grader:

import java.util.ArrayList;
import java.util.Random;

public class BackEnd {

    // TODO: insert methods for data retrieval from csv file
    // temporarily making a data object to act as data for now
    public static ArrayList<String[]> data = new ArrayList<String[]>(); // place holder
    public static HashTableMap<Airbnb, Airbnb> airbnbDatabase;
    public static HashTableMap<String, Airbnb[]> cityDatabase;
    public static ArrayList<String> cityList = new ArrayList<String>();


    private class Airbnb { // Class for objects we will store in hashtable
        String name;
        String location; // city
        int price; // cost of rental for one night
        boolean residentialStatus; // whether it is reserved
        int rating; // 1-5, where 5 makes it a top hit

        private Airbnb(String name, String location, int price, boolean resStatus, int rating) {
            this.name = name;
            this.location = location;
            this.price = price;
            this.residentialStatus = resStatus; // might reconsider this to be a string rather than boolean
            this.rating = rating;
        }

        private String getLocation() {
            return this.location;
        }

        private int getPrice() {
            return this.price;
        }

        private boolean getResidentialStatus() {
            return this.residentialStatus;
        }

        private int getRating() {
            return this.rating;
        }

        private String getName() {
            return this.name;
        }

    }

    // mainly used to load data 
    public static void main(String[] args) {
        // args will be used to take in user input from web page

        airbnbDatabase = new HashTableMap<Airbnb, Airbnb>();

        // Still need data and loading mechanism to do this properly and make airbnb objects
        for (int i = 0; i < data.size(); i++) { // each element in arrlist data is a line in CSV
            // Airbnb airbnb = new Airbnb();

            // if (!cityList.contains(airbnb.getLocation)) { // add city to list for list() method
            // cityList.add(airbnb.getLocation);
            // }

            // airbnbDatabase.put(airbnb, airbnb); // get airbnb into hash table along with
            // location and other details
        }

        

    }

    public String get(Airbnb airbnb) {
        if (airbnb == null || !airbnbDatabase.containsKey(airbnb)) {
            return null; // airbnb not in database
        }

        return airbnb.getName() + ", " + airbnb.getLocation() + ", " + airbnb.getPrice() + ", "
            + airbnb.getResidentialStatus() + ", " + airbnb.getRating() + ".";             
    }

    public Airbnb[] find(String city) {
        if (city == null || !cityList.contains(city)) {
            return null; // city not in city list
        }
        // need data to finish this - need a hashtable of cities mapped to airbnb arrays
        return cityDatabase.get(city); // returns an array of Airbnbs in this city
    }

    public String list() { // returns a list of available cities
        String cities = "";

        for (String city : cityList) {
            cities += city + " "; // can change this for better formatting
        }

        return cities;
    }

    public String randomCity() { // chooses a random city for the user
        Random rand = new Random();
        int randInt = rand.nextInt(cityList.size()) + 1; // generates a random number

        return cityList.get(randInt);
    }

}
