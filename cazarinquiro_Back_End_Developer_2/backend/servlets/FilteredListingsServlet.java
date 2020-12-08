package backend.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import backend.business.BackEnd;
import backend.business.BackEnd.Airbnb;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

@SuppressWarnings("serial")
@WebServlet("/filterlistings")
public class FilteredListingsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

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

        ArrayList<Airbnb> filteredListings =
            BackEnd.getFilteredListings(cityObject.filterFlag, priceObject.filterFlag,
                reviewsObject.filterFlag, city, lowPrice, highPrice, lowReviews, highReviews);

        // create response here
        String responseString = "{";
        responseString += "count: " + filteredListings.size() + ","; 
                                                                     
        responseString += "listings: [";

        responseString += "{ " + "name: " + filteredListings.get(0).getName() + ", ";
        responseString += "type: " + filteredListings.get(0).getType() + ", ";
        responseString += "city: " + filteredListings.get(0).getLocation() + ", ";
        responseString += "price: " + filteredListings.get(0).getPrice() + ", ";
        responseString += "minNights: " + filteredListings.get(0).getMinNights() + ", ";
        responseString += "reviews: " + filteredListings.get(0).getReviews();
        responseString += "}";

        for (int i = 1; i < filteredListings.size(); i++) {
            responseString += ", { " + "name: " + filteredListings.get(i).getName() + ", ";
            responseString += "type: " + filteredListings.get(i).getType() + ", ";
            responseString += "city: " + filteredListings.get(i).getLocation() + ", ";
            responseString += "price: " + filteredListings.get(i).getPrice() + ", ";
            responseString += "minNights: " + filteredListings.get(i).getMinNights() + ", ";
            responseString += "reviews: " + filteredListings.get(i).getReviews();
            responseString += "}";
        }
        responseString += "]}";


        // return response
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(responseString);
        out.flush();
    }

}

