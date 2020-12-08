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
@WebServlet("/filterlistings")
public class FilteredListingsServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
        // read request body fields here
        JSONObject cityObject = request.getParameter("city");
        JSONObject priceObject = request.getParameter("price");
        JSONObject reviewsObject = request.getParameter("reviews");

        String cityFilter = "";
        int lowPrice = -1;
        int highPrice = -1;
        int lowReviews = 0;
        int highReviews = -1;


        if (cityObject.filterFlag != null && cityObject.filterFlag == true) {
            cityFilter = cityObject.citySelection;
        }

        if (priceObject.filterFlag != null && cityObject.filterFlag == true) {
            lowPrice = cityObject.priceRangeStart;
            highPrice = cityObject.priceRangeEnd;
        }

        if (reviewsObject.filterFlag != null && cityObject.filterFlag == true) {
            lowReviews = reviewsObject.reviewsRangeStart;
            highReviews = reviewsObject.reviewsRangeEnd;
        }

        // do some processing here...
        
        int count = 0;
		
        // create response here
        String responseString = "{";
        responseString += "count: " + count + ",";
        responseString += "listings: [";

        //TODO: Add the listings here

        responseString += "]}";

		
		// return response
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(responseString);
        out.flush();
	}

}