package backend.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;

// --== CS400 File Header Information ==--
// Name: Dyuthi Nair
// Email: dnair5@wisc.edu 
// Team: BC
// TA: Bri Cochran
// Lecturer: Florian Heimerl	
// Notes to Grader: 

public class Backend {

	public static ArrayList<String> allCities = new ArrayList<String>();
	private static ArrayList<Airbnb> allRoomsInCity = new ArrayList<Airbnb>();
	private static HashTableMap roomsInCity = new HashTableMap();

	public Backend() {
		try {
			load();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void load() throws FileNotFoundException { // doesn't work yet, need to fix

		String home = System.getProperty("catalina.home");
		String url = home + "/webapps/AirBnB/WEB-INF/classes/data/Cities/Cities.csv";

		try {

			BufferedReader cityReader = new BufferedReader(new FileReader(url));

			String firstLine = cityReader.readLine();
			String currentLine = "";
			while ((currentLine = cityReader.readLine()) != null) {
				allCities.add(currentLine.split(",")[1]);
			}
			cityReader.close();
		} catch (IOException d) {
			d.printStackTrace();
		}

		for (String city : allCities) {
			loadAirbnb(city);
		}

	}

	private static void loadAirbnb(String city) { //some of algorithm from Luis/Jacob

		String[] info = new String[16];
		String[] restOfInfo = new String[15];
		BufferedReader airbnbReader;

		try {
			String home = System.getProperty("catalina.home");
			String url = home + "/webapps/AirBnB/WEB-INF/classes/data/" + city + ".csv";
			airbnbReader = new BufferedReader(new FileReader(url));
			String currentLine = "";
			String firstLine = airbnbReader.readLine();
			while ((currentLine = airbnbReader.readLine()) != null) {
				if (currentLine.contains("\",")) {
					info = currentLine.split("\",", 2);
					info = info[1].split("\",");
					if (info.length == 2) {
						restOfInfo = currentLine.split(",", 2);
						restOfInfo = restOfInfo[1].split(",\"");
						if (restOfInfo.length == 1) {
							restOfInfo = restOfInfo[0].split("\",\\d");
							info = restOfInfo[1].split(",");
							allRoomsInCity.add(new Airbnb(city, restOfInfo[0], info[6], Integer.parseInt(info[7]),
									Integer.parseInt(info[8]), Integer.parseInt(info[9])));
						} else {
							restOfInfo = info[1].split(",");
							info = info[0].split(",");
							allRoomsInCity.add(new Airbnb(city, info[0], restOfInfo[4], Integer.parseInt(restOfInfo[5]),
									Integer.parseInt(restOfInfo[6]), Integer.parseInt(restOfInfo[7])));
						}
					}
				} else {
					info = currentLine.split(",");
					allRoomsInCity.add(new Airbnb(city, info[1], info[8], Integer.parseInt(info[9]),
							Integer.parseInt(info[10]), Integer.parseInt(info[11])));
				}
			}
			roomsInCity.put(city, allRoomsInCity);
			allRoomsInCity.clear();
		} catch (IOException d) {
			d.printStackTrace();
		}

	}

	public ArrayList<Airbnb> find(String city) {
		if (city != null && allCities.contains(city)) {
			return (ArrayList<Airbnb>) roomsInCity.get(city);
		}
		return null;
	}

	public String[] showCities() {
		int count = 0;
		String[] cities = new String[allCities.size()];
		for (String city : allCities) {
			cities[count] = city;
			count++;
		}
		return cities;
	}

	public String get(Airbnb airbnb) {
		return airbnb.getName() + ", " + airbnb.getRoomType() + ", " + airbnb.getCity() + ", " + airbnb.getPrice()
				+ ", " + airbnb.getMinNights() + ", " + airbnb.getReviews() + ".";
	}

	public String pickRandomCity() {
		int totalCities = allCities.size();
		Random rand = new Random(totalCities);
		return allCities.get(rand.nextInt());
	}

	public ArrayList<Airbnb> getFilteredListings(boolean cityFlag, boolean priceFlag, boolean reviewsFlag, String city, //taken from Luis/Jacob
			int floorPrice, int ceilingPrice, int floorReviews, int ceilingReviews, int quantity) {

		ArrayList<Airbnb> filteredListings = null;
		ArrayList<Airbnb> tempList = new ArrayList<Airbnb>();

		if (cityFlag) {
			filteredListings = find(city);
		} else {
			for (int i = 0; i < allCities.size(); i++) {
				if (filteredListings != null) {
					filteredListings.addAll(find(allCities.get(i)));
				} else {
					filteredListings = find(allCities.get(i));
				}
			}
		}

		if (priceFlag) {

			for (int i = 0; i < filteredListings.size(); i++) {
				int curPrice = filteredListings.get(i).getPrice();

				if (curPrice <= (int) ceilingPrice && curPrice >= (int) floorPrice) {
					tempList.add(filteredListings.get(i));
				}
			}
			filteredListings.removeAll(filteredListings);
			filteredListings.addAll(tempList);
			tempList.removeAll(tempList);

		}

		if (reviewsFlag) {

			for (int i = 0; i < filteredListings.size(); i++) {
				if (filteredListings.get(i).getReviews() <= ceilingReviews
						&& filteredListings.get(i).getReviews() >= floorReviews) {
					tempList.add(filteredListings.get(i));
				}

			}

			filteredListings.removeAll(filteredListings);
			filteredListings.addAll(tempList);
			tempList.removeAll(tempList);

		}

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

	public ArrayList<Airbnb> getUnfilteredListings(int quantity) { //taken from Luis/Jacob

		ArrayList<Airbnb> unfilteredListings = new ArrayList<Airbnb>();
		ArrayList<Airbnb> tempList = new ArrayList<Airbnb>();

		for (int i = 0; i < roomsInCity.size(); i++) {
			tempList.add((Airbnb) roomsInCity.get(allCities.get(i)));
		}

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
		}

		return unfilteredListings;
	}

	public static class Airbnb {
		String city;
		String name;
		String roomType;
		int price;
		int minNights;
		int reviews;

		public Airbnb(String city, String name, String roomType, int price, int minNights, int reviews) {
			this.city = city;
			this.name = name;
			this.roomType = roomType;
			this.price = price;
			this.minNights = minNights;
			this.reviews = reviews;
		}

		public String getCity() {
			return city;
		}

		public String getName() {
			return name;
		}

		public String getRoomType() {
			return roomType;
		}

		public int getPrice() {
			return price;
		}

		public int getMinNights() {
			return minNights;
		}

		public int getReviews() {
			return reviews;
		}
	}
}
