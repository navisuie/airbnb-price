// --== CS400 File Header Information ==--
// Name: Patrick Buck
// Email:pfbuck@wisc.edu
// Team: BC
// TA: Brianna Cochran
// Lecturer: Florian Heimerl
// Notes to Grader:
package pappybuck_test_engineer_1.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.Test;
import pappybuck_test_engineer_1.BackEnd.*;

public class backend_test {


  @Test
  public void load_test(){
    BackEnd.loadCities();
    assertTrue(BackEnd.cityList.get(0).equals("Asheville"), "Test 1 Failed");
    assertTrue(BackEnd.cityList.get(27).equals("Washington D.C."), "Test 2 Failed");
    assertTrue(BackEnd.listCities().equals("Asheville, Austin, Boston, Broward, Cambridge, Chicago, Clark County, Columbus, Denver, Hawaii, Jersey City, Los Angeles, Nashville, New Orleans, New York City, Oakland, Pacific Grove, Portland, Rhode Island, Salem, San Diego, San Francisco, San Mateo, Santa Clara, Santa Cruz, Seattle, Twin Cities, Washington D.C."), "Test 3 Failed");

  }



    @Test
    public void find_test(){
     
    }
    
    @Test
    public void get_test() {
      assertTrue(true);
    }
}