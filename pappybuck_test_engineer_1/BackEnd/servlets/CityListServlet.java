// --== CS400 File Header Information ==--
// Name: Jacob Lorenz
// Email: jlorenz2@wisc.edu
// Team: BC
// Role: Front End Developer 2
// TA: Bri Cochran
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

package pappybuck_test_engineer_1.BackEnd.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/citylist")
public class CityListServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// do some processing here...
		
		// create response here
        String responseString = "{";
        responseString += "count: " + count + ",";
        responseString += "cityNames: [";

        //TODO: Add the city names here

        responseString += "]}";

		
		// return response
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(responseString);
        out.flush();
		
	}

}