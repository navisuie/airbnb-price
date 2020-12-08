// --== CS400 File Header Information ==--
// Name: Jacob Lorenz
// Email: jlorenz2@wisc.edu
// Team: BC
// Role: Front End Developer 2
// TA: Bri Cochran
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

package BackEnd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BackEnd.Backend;

@SuppressWarnings("serial")
@WebServlet("/citylist")
public class CityListServlet extends HttpServlet {
	
	BackEnd backend = BackEnd.getInstance();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// do some processing here...
		String[] cities = backend.listCities();
		
		// create response here
		String responseString = "";
		
		if (cities.length > 0) {
			responseString = "{\n";
	        responseString += "\t\"count\": " + cities.length + ",\n";
	        responseString += "\t\"cityNames\": [\n";
	        responseString += "\t\t";
	        responseString += "\"";
	        responseString += cities[0];
	        responseString += "\"";
	        
	        for (int i = 1; i < cities.length; i++) { // adding cities to response string 
	            responseString += ",\n";
	            responseString += "\t\t";
	            responseString += "\"";
	            responseString += cities[i];
	            responseString += "\"";
	        }

	        responseString += "\n\t]\n}";
		} else {
			
			String home = System.getProperty("catalina.home");
			responseString = "{\n";
	        responseString += "\t\"count\": " + cities.length + ",\n";
	        responseString += "\t\"cityNames\": []";
	        responseString += "\t\"home\": \"" + home + "\"";
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