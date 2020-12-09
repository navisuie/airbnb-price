// --== CS400 File Header Information ==--
// Name: Luis Cazarin Quiroga
// Email: cazarinquiro@wisc.edu
// Team: BC
// TA: Brianna Cochran
// Lecturer: Florian Heimerl
// Notes to Grader: Assisted by Jacob Lorenz

package backend.business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import CS400HashTable.HashTableMap;

public class Backend {

	private static final Backend backend = new Backend();
	private static HashTableMap<Airbnb, Airbnb> airbnbDatabase;
	private static HashTableMap<String, ArrayList<Airbnb>> cityDatabase;
	private static ArrayList<String> cityList;

	private Backend() { 
		airbnbDatabase = new HashTableMap<Airbnb, Airbnb>();
		cityDatabase = new HashTableMap<String, ArrayList<Airbnb>>();
		cityList = new ArrayList<String>();
		loadCities();
	}

	public static Backend getInstance() {
		return backend;
	}

	// ------- Section above is related to Singleton Design Pattern ------- //
	// -------------------------------------------------------------------- //
	

	public static class Airbnb { // Class for objects we will store in hashtable
		String name;
		String type;
		String location; // city
		int price; 
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

		public int getPrice() {
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
		public String toString(){
		 return this.getName() + ", " + this.getType() + ", " + this.getLocation() + ", "
			+ this.getPrice() + ", " + this.getMinNights() + ", "
			+ this.getReviews() + ".";
		}
	}

	/*
	 * Loads data from CSV files
	 */
	public static void loadCities() {
		
		String home = System.getProperty("catalina.home");
		String url = home + "/webapps/AirBnB/WEB-INF/classes/data/Cities/Cities.csv";

		try { // helped along by Jake L
//			BufferedReader csvReader =
//					new BufferedReader(new FileReader("../../../../../eclipse-workspace/AirBnB/src/data/Cities/Cities.csv"));

			BufferedReader csvReader =
					new BufferedReader(new FileReader(url));

			
			String currentLine = "";
			String firstLine = csvReader.readLine(); // skip over first line
			while ((currentLine = csvReader.readLine()) != null) {
				String[] data = currentLine.split(",");

				backend.cityList.add(data[1]); // add city to city list
			}
			csvReader.close();

		} catch (IOException d) {
			d.printStackTrace();
		}

		for (int i = 0; i < backend.cityList.size(); i++) {
			ArrayList<Airbnb> curListings = loadCity(backend.cityList.get(i));
			String cityName = backend.cityList.get(i);
			backend.cityDatabase.put(cityName, curListings);
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
			String home = System.getProperty("catalina.home");
			String url = home + "/webapps/AirBnB/WEB-INF/classes/data/" + city + ".csv";
//			BufferedReader csvReader =
//					new BufferedReader(new FileReader("../../../../../eclipse-workspace/AirBnB/src/data/" + city + ".csv"));

			BufferedReader csvReader =
					new BufferedReader(new FileReader(url));

			
			String currentLine = "";
			String firstLine = csvReader.readLine(); // skip over first line
			while ((currentLine = csvReader.readLine()) != null) {
				String[] data = null;
				String[] secondaryData = null;
				if (!currentLine.contains("\",")) { // default case

					data = currentLine.split(",");
//					System.out.println(currentLine);
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

				String unquotedName = name.replaceAll("\"", "");
				Airbnb airbnb = new Airbnb(unquotedName, location, type, price, minNights, reviews);
				listOfAirbnbs.add(airbnb);
				backend.airbnbDatabase.put(airbnb, airbnb); // for hashtable, airbnb object is key & value
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
		if (airbnb == null || !airbnbDatabase.containsKey(airbnb)) {
			return null; // airbnb not in database
		}

		return airbnb.getName() + ", " + airbnb.getType() + ", " + airbnb.getLocation() + ", "
		+ airbnb.getPrice() + ", " + airbnb.getMinNights() + ", "
		+ airbnb.getReviews() + ".";
	}

	/*
	 * Returns an Airbnb array of all airbnbs in a city
	 */
	public static ArrayList<Airbnb> find(String city) {
		if (city == null || !backend.cityList.contains(city)) {
			return null; // city not in city list
		}
		ArrayList<Airbnb> returnList = new ArrayList<Airbnb>();
		returnList.addAll(backend.cityDatabase.get(city));
		return returnList; // returns an array of Airbnbs in this city
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
	public static String randomCity() {
		Random rand = new Random();
		int randInt = rand.nextInt(backend.cityList.size()) + 1; // generates a random number

		return backend.cityList.get(randInt);
	}

	/*
     * Returns a filtered list of Airbnbs based on city, price, and reviews
     * Assisted by Jacob Lorenz
     */
    public ArrayList<Airbnb> getFilteredListings(boolean cityFlag, boolean priceFlag,
        boolean reviewsFlag, String city, int floorPrice, int ceilingPrice, int floorReviews,
        int ceilingReviews, int quantity) {

        ArrayList<Airbnb> filteredListings = null;
        ArrayList<Airbnb> tempList = new ArrayList<Airbnb>();

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
            	int curPrice = filteredListings.get(i).getPrice();
            	
                if (curPrice <= (int) ceilingPrice && 
                	curPrice >= (int) floorPrice) { // if price between price floor and ceiling
                    tempList.add(filteredListings.get(i));
                }
            }
            filteredListings.removeAll(filteredListings);
            filteredListings.addAll(tempList);
            tempList.removeAll(tempList); // clear tempList
            
        }

        if (reviewsFlag) {

            for (int i = 0; i < filteredListings.size(); i++) {
                if (filteredListings.get(i).getReviews() <= ceilingReviews
                    && filteredListings.get(i).getReviews() >= floorReviews) { // if # of reviews
                                                                               // between floor and
                                                                               // ceiling
                    tempList.add(filteredListings.get(i));
                }

            }

            filteredListings.removeAll(filteredListings);
            filteredListings.addAll(tempList);
            tempList.removeAll(tempList); // clear tempList

        }
        
        // limit how many listings are returned based on quantity requested
        int maxQuantity = 0;
        if (filteredListings.size() > quantity) {
        	maxQuantity = quantity;
        } else {
        	maxQuantity = filteredListings.size();
        }
        
        Random rng = new Random();
        
        for (int i = 0; i < maxQuantity; i++) {
        	int randomIndex = rng.nextInt(filteredListings.size());
            tempList.add(filteredListings.get(randomIndex));
            filteredListings.remove(randomIndex);
        }

        return tempList;
    }
	
    /*
     * Returns an unfiltered list of Airbnbs depending on the quantity
     * Assisted by Jacob Lorenz
     */
    public ArrayList<Airbnb> getUnfilteredListings(int quantity) {

        ArrayList<Airbnb> unfilteredListings = new ArrayList<Airbnb>();
        ArrayList<Airbnb> tempList = new ArrayList<Airbnb>();

        for (int i = 0; i < cityDatabase.size(); i++) {
            tempList.addAll(cityDatabase.get(cityList.get(i)));
        } // combine all Airbnb ArrayLists into one Airbnb ArrayList
        
        int maxQuantity = 0;
        if (tempList.size() > quantity) {
        	maxQuantity = quantity;
        } else {
        	maxQuantity = tempList.size();
        }
        
        Random rng = new Random();
        
        for (int i = 0; i < maxQuantity; i++) {
        	int randomIndex = rng.nextInt(tempList.size());
        	unfilteredListings.add(tempList.get(randomIndex));
        	tempList.remove(randomIndex);
//            unfilteredListings.add(tempList.get(i));
        }

        return unfilteredListings;
    }

}
