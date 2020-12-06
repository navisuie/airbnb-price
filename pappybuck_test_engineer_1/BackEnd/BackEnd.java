// --== CS400 File Header Information ==--
// Name: Luis Cazarin Quiroga
// Email: cazarinquiro@wisc.edu
// Team: BC
// TA: Brianna Cochran
// Lecturer: Florian Heimerl
// Notes to Grader:

package pappybuck_test_engineer_1.BackEnd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BackEnd {

    public static HashTableMap<String, Airbnb> airbnbDatabase = new HashTableMap<String, Airbnb>();;
    public static HashTableMap<String, ArrayList<Airbnb>> cityDatabase = new HashTableMap<String, ArrayList<Airbnb>>();
    public static ArrayList<String> cityList = new ArrayList<String>();

    public static class Airbnb { // Class for objects we will store in hashtable
        String name;
        String type;
        String location; // city
        double price; 
        int minNights;
        int reviews; // total reviews

        public Airbnb(String name, String location, String type, int price,
            int minNights, int rating) {
            this.name = name;
            this.type = type;
            this.location = location;
            this.price = price;
            this.minNights = minNights;
            this.reviews = rating;
        }

        public String getLocation() {
            return this.location;
        }

        public String getType() {
            return this.type;
        }

        public double getPrice() {
            return this.price;
        }

        public int getMinNights() {
            return this.minNights;
        }

        public int getReviews() {
            return this.reviews;
        }

        public String getName() {
            return this.name;
        }
        @Override
        public String toString() {
            return this.getName() + ", " + this.getType() + ", " + this.getLocation() + ", "
            + this.getPrice() + ", " + this.getMinNights() + ", "
            + this.getReviews() + ".";
        }
    }

    /*
     * Loads data from CSV files
     */
    public static void loadCities() {

        try { // helped along by Jake L
            BufferedReader csvReader =
                new BufferedReader(new FileReader("pappybuck_test_engineer_1/Data/Cities/Cities_dummy.csv"));

            String currentLine = "";
            String firstLine = csvReader.readLine(); // skip over first line
            while ((currentLine = csvReader.readLine()) != null) {
                String[] data = currentLine.split(",");

                cityList.add(data[1]); // add city to city list
            }
            csvReader.close();

        } catch (IOException d) {
            d.printStackTrace();
        }

        for (int i = 0; i < cityList.size(); i++) {
            cityDatabase.put(cityList.get(i), loadCity(cityList.get(i)));
        } // fill hashtable with arraylist of airbnbs for each city

    }

    /**
     * Loads a specific city CSV file
     * @throws IOException 
     */
    public static ArrayList<Airbnb> loadCity(String city) {

        String name;
        String type;
        String location = city;
        int minNights;
        int price;
        int reviews;

        ArrayList<Airbnb> listOfAirbnbs = new ArrayList<Airbnb>();

        try { // helped along by Jake L
            BufferedReader csvReader =
                new BufferedReader(new FileReader("pappybuck_test_engineer_1/Data/" + city + ".csv"));

            String currentLine = "";
            String firstLine = csvReader.readLine(); // skip over first line
            while ((currentLine = csvReader.readLine()) != null) {
                String[] data = null;
                String[] secondaryData = null;
                if (!currentLine.contains("\",")) { // default case

                    data = currentLine.split(",");
                    name = data[1];
                    type = data[8];
                    price = Integer.parseInt(data[9]);
                    minNights = Integer.parseInt(data[10]);
                    reviews = Integer.parseInt(data[11]);
                } else {
                    data = currentLine.split(",", 2); // skip over id
                    data = data[1].split("\",");
                    if (data.length == 2) { // name OR host name have double quotes
                        secondaryData = currentLine.split(",", 2); // skip over id, get rid of comma
                        secondaryData = secondaryData[1].split(",\"");

                        if (secondaryData.length == 1) { // name has double quotes
                            
                            secondaryData = secondaryData[0].split("\",\\d");
                            name = secondaryData[0];
                            data = secondaryData[1].split(",");
                            type = data[6];
                            price = Integer.parseInt(data[7]);
                            minNights = Integer.parseInt(data[8]);
                            reviews = Integer.parseInt(data[9]);
                        } else { // if length 2 (host name has double quotes)

                            secondaryData = data[1].split(",");
                            data = data[0].split(",");
                            name = data[0];
                            type = secondaryData[4];
                            try {
                            price = Integer.parseInt(secondaryData[5]);
                            minNights = Integer.parseInt(secondaryData[6]);
                            reviews = Integer.parseInt(secondaryData[7]);
                            } catch (NumberFormatException e) {
                                System.out.println(name);
                                return null;
                            }
                        }
                    } else  { // name AND host name have double quotes
                        name = data[0];
                        data = data[2].split(",");
                        type = data[4];
                        price = Integer.parseInt(data[5]);
                        minNights = Integer.parseInt(data[6]);
                        reviews = Integer.parseInt(data[7]);
                    }
                }



                Airbnb airbnb = new Airbnb(name, location, type, price, minNights, reviews);
                listOfAirbnbs.add(airbnb);
                airbnbDatabase.put(airbnb.toString(), airbnb); // for hashtable, airbnb object is key & value
                System.out.println(name);
            }
            csvReader.close();

        } catch (IOException d) {
            d.printStackTrace();
        }

        return listOfAirbnbs;
    }


    /*
     * Returns the string representation of an Airbnb
     */
    public String get(Airbnb airbnb) {
        if (airbnb == null || !airbnbDatabase.containsKey(airbnb.toString())) {
            return null; // airbnb not in database
        }

        return airbnb.toString();
    }

    /*
     * Returns an Airbnb array of all airbnbs in a city
     */
    public ArrayList<Airbnb> find(String city) {
        if (city == null || !cityList.contains(city)) {
            return null; // city not in city list
        }
        return cityDatabase.get(city); // returns an array of Airbnbs in this city
    }

    /*
     * Returns a list of all available cities
     */
    public String[] listCities() {
        String[] cities = new String[cityList.size()]; 

        for (int i = 0; i < cityList.size(); i++) {
            cities[i] = cityList.get(i);           
            
        }
        return cities;
    }

    /*
     * Returns the string representation of a random city
     */
    public  String randomCity() {
        Random rand = new Random();
        int randInt = rand.nextInt(cityList.size()) + 1; // generates a random number

        return cityList.get(randInt);
    }



    public ArrayList<Airbnb> getFilteredListings(boolean cityFlag, boolean priceFlag,
        boolean reviewsFlag, String city, double floorPrice, double ceilingPrice, int floorReviews,
        int ceilingReviews) {
        
        ArrayList<Airbnb> filteredListings = null;
        
        if (cityFlag) {
            filteredListings = find(city);
        } else {
            for (int i = 0; i < cityList.size(); i++) {
                if (filteredListings != null) {
                    filteredListings.addAll(find(cityList.get(i))); // add city Airbnbs together
                } else {
                filteredListings = find(cityList.get(i));
                }
            }
        }

        if (priceFlag) {
            
            for (int i = 0; i < filteredListings.size(); i++) {
                
            }

        } else {

        }

        if (reviewsFlag) {

        } else {

        }
        
        

        //TODO:
        // Check each flag for specific filters - can be multiple
        // narrow down possible listings w/ city flags, then price (inclusive), then reviews
        // (inclusive)
        // make arraylist

        return filteredListings;
    }
    

    public ArrayList<Airbnb> getUnfilteredListings(String quantity) {
        // TODO Auto-generated method stub
        
        
        return null;
    }


}
