// --== CS400 File Header Information ==--
// Name: Patrick Buck
// Email:pfbuck@wisc.edu
// Team: BC
// TA: Brianna Cochran
// Lecturer: Florian Heimerl
// Notes to Grader:
package pappybuck_test_engineer_1.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pappybuck_test_engineer_1.BackEnd.*;

public class backend_test {

  private static BackEnd test;

  @BeforeClass
  public static void setup(){
    BackEnd.loadCities();
  }

  @Before
  public void intialize(){
    test = new BackEnd();
  }

  @Test
  public void load_test(){
    /*String[] tester = {"Asheville", "Austin", "Boston", "Broward", "Cambridge", "Chicago", "Clark County", "Columbus", "Denver",
     "Hawaii", "Jersey City", "Los Angeles", "Nashville", "New Orleans", "New York City", "Oakland", "Pacific Grove", "Portland", 
    "Rhode Island", "Salem", "San Diego", "San Francisco", "San Mateo", "Santa Clara", "Santa Cruz", "Seattle", "Twin Cities", "Washington D.C."}; */
    String[] tester = {"Asheville"};
    String[] testing = test.listCities();
    assertTrue(BackEnd.cityList.get(0).equals("Asheville"), "Test 1 Failed");
    assertTrue(Arrays.equals(test.listCities(), tester), "Test 2 Failed");

  }



    @Test
    public void find_test(){
      ArrayList<BackEnd.Airbnb> Testing;

      //Asheville
      {
        Testing = test.find("Asheville");
        { //Apartment 1
        assertTrue(Testing.get(0).getPrice() == 80, "Asheville Apartment 1 Price Failure");
        assertTrue(Testing.get(0).getMinNights() == 30, "Asheville Apartment 1 Min Nights Failure");
        assertTrue(Testing.get(0).getReviews() == 89, "Asheville Apartment 1 Reviews Failure");
        assertTrue(Testing.get(0).getName().equals("Walk to stores/parks/downtown. Fenced yard/Pets OK"), "Asheville Apartment 1 Name Failure");
        assertTrue(Testing.get(0).getType().equals("Entire home/apt"), "Asheville Apartment 1 Type Failure");
        }
        { //Apartment 2
        assertTrue(Testing.get(363).getPrice() == 110, "Asheville Apartment 2 Price Failure");
        assertTrue(Testing.get(363).getMinNights() == 3, "Asheville Apartment 2 Min Nights Failure");
        assertTrue(Testing.get(363).getReviews() == 44, "Asheville Apartment 2 Reviews Failure");
        assertTrue(Testing.get(363).getName().equals("\"THE SWEET SPOT- serene hilltop, close to downtown"), "Asheville Apartment 2 Name Failure");
        assertTrue(Testing.get(363).getType().equals("Entire home/apt"), "Asheville Apartment 2 Type Failure");
        }
        { //Apartment 3
        assertTrue(Testing.get(625).getPrice() == 213, "Asheville Apartment 3 Price Failure");
        assertTrue(Testing.get(625).getMinNights() == 2, "Asheville Apartment 3 Min Nights Failure");
        assertTrue(Testing.get(625).getReviews() == 193, "Asheville Apartment 3 Reviews Failure");
        assertTrue(Testing.get(625).getName().equals("\"✥ The White House ✥ 9 min to DT A-Ville, Sleeps 8+"), "Asheville Apartment 3 Name Failure");
        assertTrue(Testing.get(625).getType().equals("Entire home/apt"), "Asheville Apartment 3 Type Failure");
        }
      }
      //Tampa
      {
        Testing = test.find("Tampa");
        assertTrue(Testing == null, "Missing City Failure");
      }
    }
    
    @Test
    public void get_test() {
      //Asheville
      {
        assertTrue(test.get(new BackEnd.Airbnb("Sunny Modern Country Apartment with Hot Tub", "Asheville", "Entire home/apt", 86, 2, 269))
        .equals("Sunny Modern Country Apartment with Hot Tub, Entire home/apt, Asheville, 86.0, 2, 269."), "Asheville Get 1 Failure");
      }
      
      
      


    }

    public static void main(String[] args) {
      BackEnd.loadCities();
    }
}