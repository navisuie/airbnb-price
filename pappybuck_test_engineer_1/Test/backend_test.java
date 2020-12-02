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
  
    BackEnd.cityList.get(0);

  }



    @Test
    public void find_test(){
     assertTrue(true);
    }
    
    @Test
    public void get_test() {
      assertTrue(true);
    }
    
    @Test
    public void list_test() {
     assertTrue(true);
      
    }

  public static void main(String[] args) {
    BackEnd.loadCities();
    System.out.println(BackEnd.cityList.get(0));
  }


}