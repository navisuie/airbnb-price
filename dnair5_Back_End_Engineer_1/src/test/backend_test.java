// --== CS400 File Header Information ==--
// Name: Patrick Buck
// Email:pfbuck@wisc.edu
// Team: BC
// TA: Brianna Cochran
// Lecturer: Florian Heimerl
// Notes to Grader:
package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import backend.business.Backend;	

public class backend_test {

	private static Backend test;

	@Before
	public void intialize() {
		test = new Backend();
	}

	/**
	 * Tests to see if the Backend load method is working correctly
	 */
	@Test
	public void load_test() {
		String[] tester = { "Asheville", "Austin", "Boston", "Broward", "Cambridge", "Chicago", "Clark County",
				"Columbus", "Denver", "Hawaii", "Jersey City", "Los Angeles", "Nashville", "New Orleans",
				"New York City", "Oakland", "Pacific Grove", "Portland", "Rhode Island", "Salem", "San Diego",
				"San Francisco", "San Mateo", "Santa Clara", "Santa Cruz", "Seattle", "Twin Cities" };

		assertTrue(Backend.allCities.get(0).equals("Asheville"), "Test 1 Failed");
		assertTrue(Backend.allCities.get(5).equals("Chicago"), "Test 2 Failed");
		assertTrue(Backend.allCities.get(11).equals("Los Angeles"), "Test 3 Failed");
		assertTrue(Backend.allCities.get(17).equals("Portland"), "Test 4 Failed");
		assertTrue(Backend.allCities.get(26).equals("Twin Cities"), "Test 5 Failed");
		assertTrue(Arrays.equals(test.showCities(), tester), "Test 6 Failed");

	}

	/**
	 * Tests to see if the Backend find method is working correctly
	 */
	@Test
	public void find_test() {
		ArrayList<Backend.Airbnb> Testing;

		// Asheville
		{
			Testing = test.find("Asheville");
			{ // Apartment 1
				assertTrue(Testing.get(0).getPrice() == 80, "Asheville Apartment 1 Price Failure");
				assertTrue(Testing.get(0).getMinNights() == 30, "Asheville Apartment 1 Min Nights Failure");
				assertTrue(Testing.get(0).getReviews() == 89, "Asheville Apartment 1 Reviews Failure");
				assertTrue(Testing.get(0).getName().equals("Walk to stores/parks/downtown. Fenced yard/Pets OK"),
						"Asheville Apartment 1 Name Failure");
				assertTrue(Testing.get(0).getRoomType().equals("Entire home/apt"), "Asheville Apartment 1 Type Failure");
			}
			{ // Apartment 2
				assertTrue(Testing.get(363).getPrice() == 110, "Asheville Apartment 2 Price Failure");
				assertTrue(Testing.get(363).getMinNights() == 3, "Asheville Apartment 2 Min Nights Failure");
				assertTrue(Testing.get(363).getReviews() == 44, "Asheville Apartment 2 Reviews Failure");
				assertTrue(Testing.get(363).getName().equals("\"THE SWEET SPOT- serene hilltop, close to downtown"),
						"Asheville Apartment 2 Name Failure");
				assertTrue(Testing.get(363).getRoomType().equals("Entire home/apt"), "Asheville Apartment 2 Type Failure");
			}
			{ // Apartment 3
				assertTrue(Testing.get(625).getPrice() == 213, "Asheville Apartment 3 Price Failure");
				assertTrue(Testing.get(625).getMinNights() == 2, "Asheville Apartment 3 Min Nights Failure");
				assertTrue(Testing.get(625).getReviews() == 193, "Asheville Apartment 3 Reviews Failure");
				assertTrue(Testing.get(625).getName().equals("\"✥ The White House ✥ 9 min to DT A-Ville, Sleeps 8+"),
						"Asheville Apartment 3 Name Failure");
				assertTrue(Testing.get(625).getRoomType().equals("Entire home/apt"), "Asheville Apartment 3 Type Failure");
			}
		}
		// Tampa
		{
			Testing = test.find("Tampa");
			assertTrue(Testing == null, "Missing City Failure");
		}
		// Columbus
		{
			Testing = test.find("Columbus");
			{ // Apartment 1
				assertTrue(Testing.get(178).getPrice() == 45, "Columbus Apartment 1 Price Failure");
				assertTrue(Testing.get(178).getMinNights() == 2, "Columbus Apartment 1 Min Nights Failure");
				assertTrue(Testing.get(178).getReviews() == 126, "Columbus Apartment 1 Reviews Failure");
				assertTrue(Testing.get(178).getName().equals("Haven on High St: Let's give together!"),
						"Columbus Apartment 1 Name Failure");
				assertTrue(Testing.get(178).getRoomType().equals("Entire home/apt"), "Columbus Apartment 1 Type Failure");
			}
			{ // Apartment 2
				assertTrue(Testing.get(417).getPrice() == 136, "Columbus Apartment 2 Price Failure");
				assertTrue(Testing.get(417).getMinNights() == 2, "Columbus Apartment 2 Min Nights Failure");
				assertTrue(Testing.get(417).getReviews() == 24, "Columbus Apartment 2 Reviews Failure");
				assertTrue(Testing.get(417).getName().equals("The Gatsby Flat—Downtown Columbus/Convention Ctr"),
						"Columbus Apartment 2 Name Failure");
				assertTrue(Testing.get(417).getRoomType().equals("Entire home/apt"), "Columbus Apartment 2 Type Failure");
			}
			{ // Apartment 3
				assertTrue(Testing.get(1051).getPrice() == 112, "Columbus Apartment 3 Price Failure");
				assertTrue(Testing.get(1051).getMinNights() == 2, "Columbus Apartment 3 Min Nights Failure");
				assertTrue(Testing.get(1051).getReviews() == 2, "Columbus Apartment 3 Reviews Failure");
				assertTrue(Testing.get(1051).getName().equals("Cute and Cozy 3 Bedroom Ranch Home"),
						"Columbus Apartment 3 Name Failure");
				assertTrue(Testing.get(1051).getRoomType().equals("Entire home/apt"), "Columbus Apartment 3 Type Failure");
			}
		}
		// New York City
		{
			Testing = test.find("New York City");
			{ // Apartment 1
				assertTrue(Testing.get(185).getPrice() == 82, "New York City Apartment 1 Price Failure");
				assertTrue(Testing.get(185).getMinNights() == 28, "New York City Apartment 1 Min Nights Failure");
				assertTrue(Testing.get(185).getReviews() == 188, "New York City Apartment 1 Reviews Failure");
				assertTrue(Testing.get(185).getName().equals("SpaHa Studio Monthly Rental"),
						"New York City Apartment 1 Name Failure");
				assertTrue(Testing.get(185).getRoomType().equals("Entire home/apt"),
						"New York City Apartment 1 Type Failure");
			}
			{ // Apartment 2
				assertTrue(Testing.get(554).getPrice() == 599, "New York City Apartment 2 Price Failure");
				assertTrue(Testing.get(554).getMinNights() == 3, "New York City Apartment 2 Min Nights Failure");
				assertTrue(Testing.get(554).getReviews() == 7, "New York City Apartment 2 Reviews Failure");
				assertTrue(Testing.get(554).getName().equals("2 BR Duplex @ Box House Hotel"),
						"New York City Apartment 2 Name Failure");
				assertTrue(Testing.get(554).getRoomType().equals("Private room"), "New York City Apartment 2 Type Failure");
			}
			{ // Apartment 3
				assertTrue(Testing.get(786).getPrice() == 56, "New York City Apartment 3 Price Failure");
				assertTrue(Testing.get(786).getMinNights() == 29, "New York City Apartment 3 Min Nights Failure");
				assertTrue(Testing.get(786).getReviews() == 20, "New York City Apartment 3 Reviews Failure");
				assertTrue(Testing.get(786).getName().equals("Williamsburg near soho .support artist living"),
						"New York City Apartment 3 Name Failure");
				assertTrue(Testing.get(786).getRoomType().equals("Private room"), "New York City Apartment 3 Type Failure");
			}
		}
	}
}