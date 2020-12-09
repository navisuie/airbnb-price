// --== CS400 File Header Information ==--
// Name: Jacob Lorenz
// Email: jlorenz2@wisc.edu
// Team: BC
// Role: Front End Developer 2
// TA: Bri Cochran
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

package backend.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import backend.business.Backend;
import backend.business.Backend.Airbnb;

@SuppressWarnings("serial")
@WebServlet("/filteredlistings")
public class FilteredListingsServlet extends HttpServlet {

	Backend backend = new Backend();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
        try {
        	// read request body fields here
            StringBuffer jb = new StringBuffer();
            String line = null;
        	BufferedReader reader = request.getReader();
        	while ((line = reader.readLine()) != null) {
        		jb.append(line);
        	}
        	
        	JSONParser parser = new JSONParser();
        	JSONObject json = (JSONObject) parser.parse(jb.toString());
        	
        	int quantity = Integer.parseInt((String) json.get("quantity"));
        	
        	JSONObject cityObject = (JSONObject) json.get("city");
        	JSONObject priceObject = (JSONObject) json.get("price");
        	JSONObject reviewsObject = (JSONObject) json.get("reviews");
      
        	boolean cityFilterFlag = false;
			String cityFilter = "";
			boolean priceFilterFlag = false;
	        int lowPrice = -1;
	        int highPrice = -1;
	        boolean reviewsFilterFlag = false;
	        int lowReviews = -1;
	        int highReviews = -1;
	        
	        if ((boolean) cityObject.get("filterFlag") == true) {
	        	cityFilterFlag = true;
	            cityFilter = (String) cityObject.get("citySelection");
	        }

	        if ((boolean) priceObject.get("filterFlag") == true) {
	        	priceFilterFlag = true;
	            lowPrice = Integer.parseInt((String) priceObject.get("priceRangeStart"));
	            highPrice = Integer.parseInt((String) priceObject.get("priceRangeEnd"));
	        }
	        

	        if ((boolean) reviewsObject.get("filterFlag") == true) {
	        	reviewsFilterFlag = true;
	            lowReviews = Integer.parseInt((String) reviewsObject.get("reviewsRangeStart"));
	            highReviews = Integer.parseInt((String) reviewsObject.get("reviewsRangeEnd"));
	        }
	        
	        // do some processing here...
	        ArrayList<Airbnb> filteredListings = 
	        		backend.getFilteredListings(
	        				cityFilterFlag,
	        				priceFilterFlag,
	        				reviewsFilterFlag,
	        				cityFilter,
	        				lowPrice,
	        				highPrice,
	        				lowReviews,
	        				highReviews,
	        				quantity);
			
			// create response here
	        String responseString = "";
	        if (filteredListings.size() > 0) {
	        	responseString = "{\n";
		        responseString += "\t\"count\": " + filteredListings.size() + ",\n";
		        responseString += "\t\"listings\": [\n";
		        responseString += "\t\t";
		        responseString += "{";
		        responseString += "\"name\": \"" + filteredListings.get(0).getName() + "\", ";
		        responseString += "\"type\": \"" + filteredListings.get(0).getRoomType() + "\", ";
		        responseString += "\"city\": \"" + filteredListings.get(0).getCity() + "\", ";
		        responseString += "\"price\": " + filteredListings.get(0).getPrice() + ", ";
		        responseString += "\"minNights\": " + filteredListings.get(0).getMinNights() + ", ";
		        responseString += "\"reviews\": " + filteredListings.get(0).getReviews();
		        responseString += "}";

		        for (int i = 1; i < filteredListings.size(); i++) {
		            responseString += ",\n";
		            responseString += "\t\t";
		            responseString += "{";
		            responseString += "\"name\": \"" + filteredListings.get(i).getName() + "\", ";
		            responseString += "\"type\": \"" + filteredListings.get(i).getRoomType() + "\", ";
		            responseString += "\"city\": \"" + filteredListings.get(i).getCity() + "\", ";
		            responseString += "\"price\": " + filteredListings.get(i).getPrice() + ", ";
		            responseString += "\"minNights\": " + filteredListings.get(i).getMinNights() + ", ";
		            responseString += "\"reviews\": " + filteredListings.get(i).getReviews();
		            responseString += "}";
		        }

		        responseString += "\n\t]\n}";
	        } else {
	        	responseString = "{\n";
		        responseString += "\t\"count\": " + filteredListings.size() + ",\n";
		        responseString += "\t\"listings\": []";
		        responseString += "\n}";
	        }
	        
			// return response
			PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(responseString);
	        out.flush();
        	
        } catch (Exception e) { 
        	e.printStackTrace();
        }
	}

}