import java.io.*;

import java.util.Hashtable;



public class ReadFile {
  public static void main(String[] args) {

  }

  public static <T> void upload() {
    Hashtable<T, T> data = new Hashtable<T, T>();
    String line = "";
    BufferedReader br = null;
    try {
       br = new BufferedReader(new FileReader("listings.csv"));
      while ((line = br.readLine()) != null) {
        
        String[] cityRow = line.split(",");
      }

    } catch (

    FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } 
      
     finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
