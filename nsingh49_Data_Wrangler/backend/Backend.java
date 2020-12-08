import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
	private static boolean dataloaded = false;
	
	public ArrayList<Airbnb> getAirBnbs(String city) throws FileNotFoundException {
		load();
		return find(city);
	}
	
	private void load() throws FileNotFoundException { //doesn't work yet, need to fix
		if (!dataloaded)
		{
			File cities = new File("Cities.csv");
			Scanner cityReader = new Scanner(cities);
			
			while (cityReader.hasNextLine()) {
				allCities.add(cityReader.next());
			}
			cityReader.close();
			
			String[] info = new String[16];
			File individualCity;
			Scanner airbnbReader;
			Airbnb airbnb;
			
			for(String city : allCities) {
				individualCity = new File(city + ".csv");
				airbnbReader = new Scanner(individualCity);
				
				while(airbnbReader.hasNextLine()) {
					info = cityReader.nextLine().split(",");
					airbnb = new Airbnb(city, info[0], info[2], info[8], info[9], info[10]);
					allRoomsInCity.add(airbnb);
				}
				roomsInCity.put(city, allRoomsInCity);
				allRoomsInCity.clear();
			}
			dataloaded = true;
		}
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
	
		
	
}
