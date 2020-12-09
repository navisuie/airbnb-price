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
@WebServlet("/initiallistings")
public class InitialListingsServlet extends HttpServlet {
	
	Backend backend = Backend.getInstance();
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
        // read request body fields here
        StringBuffer jb = new StringBuffer();
        String line = null;
        int quantity = 0;
        try {
        	BufferedReader reader = request.getReader();
        	while ((line = reader.readLine()) != null) {
        		jb.append(line);
        	}
        	JSONParser parser = new JSONParser();
        	JSONObject json = (JSONObject) parser.parse(jb.toString());
        	quantity = Integer.parseInt((String) json.get("quantity"));
        } catch (Exception e) { 
        	e.printStackTrace();
        }
        
		// do some processing here...
        ArrayList<Airbnb> unfilteredListings = backend.getUnfilteredListings(quantity);
		
		// create response here
        String responseString = "";
        if (unfilteredListings.size() > 0) {
        	responseString = "{\n";
            responseString += "\t\"count\": " + unfilteredListings.size() + ",\n";
            responseString += "\t\"listings\": [\n";
            responseString += "\t\t";
            responseString += "{";
            responseString += "\"name\": \"" + unfilteredListings.get(0).getName() + "\", ";
            responseString += "\"type\": \"" + unfilteredListings.get(0).getType() + "\", ";
            responseString += "\"city\": \"" + unfilteredListings.get(0).getLocation() + "\", ";
            responseString += "\"price\": " + unfilteredListings.get(0).getPrice() + ", ";
            responseString += "\"minNights\": " + unfilteredListings.get(0).getMinNights() + ", ";
            responseString += "\"reviews\": " + unfilteredListings.get(0).getReviews();
            responseString += "}";

            for (int i = 1; i < unfilteredListings.size(); i++) {
                responseString += ",\n";
                responseString += "\t\t";
                responseString += "{";
                responseString += "\"name\": \"" + unfilteredListings.get(i).getName() + "\", ";
                responseString += "\"type\": \"" + unfilteredListings.get(i).getType() + "\", ";
                responseString += "\"city\": \"" + unfilteredListings.get(i).getLocation() + "\", ";
                responseString += "\"price\": " + unfilteredListings.get(i).getPrice() + ", ";
                responseString += "\"minNights\": " + unfilteredListings.get(i).getMinNights() + ", ";
                responseString += "\"reviews\": " + unfilteredListings.get(i).getReviews();
                responseString += "}";
            }

            responseString += "\n\t]\n}";
        } else {
        	responseString = "{\n";
            responseString += "\t\"count\": " + unfilteredListings.size() + ",\n";
            responseString += "\t\"listings\": []";
            responseString += "\n}";
        }
        
		// return response
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(responseString);
        out.flush();
		
	}

}