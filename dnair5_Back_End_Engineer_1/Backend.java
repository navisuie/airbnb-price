import java.util.ArrayList;
import java.util.Random;

// --== CS400 File Header Information ==--
// Name: Dyuthi Nair
// Email: dnair5@wisc.edu 
// Team: BC
// TA: Bri Cochran
// Lecturer: Florian Heimerl	
// Notes to Grader: 

public class Backend {
	
	private static ArrayList<String> allCities;
	private static ArrayList<Airbnb> allRoomsInCity;
	private static HashTableMap roomsInCity;
	
	public static void main(String[] args) {
		roomsInCity = new HashTableMap();//key is city, value is allRoomsInCity
		load();
		
	}

	private static void load() {
		//read city, put into allRoomsInCity
			//in read Airbnbs, put into allRooms
		//put String city(value), ArrayList<Airbnb> allRoomsInCity
		//clear allRoomsInCity
	}
	
	public ArrayList<Airbnb> find(String city) {
		return (ArrayList<Airbnb>) roomsInCity.get(city);
	}
	
	public ArrayList<String> showCities() {
		return allCities;
	}
	
	public String pickRandomCity() {
		int totalCities = allCities.size();
		Random rand = new Random(totalCities);
		return allCities.get(rand.nextInt());
	}
	
	private class Airbnb {
		String city;
		int ID;
		int hostID;
		String roomType;
		int price;
		int minNights;
		int rating;
		boolean availability;
		
		private Airbnb(String city, int ID, int hostID, String roomType, int price, int minNights, int rating, boolean availability) {
			this.city = city;
			this.ID = ID;
			this.roomType = roomType;
			this.price = price;
			this.minNights = minNights;
			this.rating = rating;
			this.availability = availability;
		}

		public String getCity() {
			return city;
		}

		public int getID() {
			return ID;
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

		public int getRating() {
			return rating;
		}

		public boolean isAvailability() {
			return availability;
		}

		public int getHostID() {
			return hostID;
		}		
	}
	
	
}
