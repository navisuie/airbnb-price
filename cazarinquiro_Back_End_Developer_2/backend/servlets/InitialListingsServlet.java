package backend.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import backend.business.BackEnd;
import backend.business.BackEnd.Airbnb;

@SuppressWarnings("serial")
@WebServlet("/initiallistings")
public class InitialListingsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // read request body fields here
        String quantity = request.getParameter("quantity"); // 1st thing - initial table of listing


        ArrayList<Airbnb> unfilteredListings = BackEnd.getUnfilteredListings(quantity);

        // create response here
        String responseString = "{";
        responseString += "count: " + unfilteredListings.size() + ","; 
                                                                       
        responseString += "listings: [";

        responseString += "{ " + "name: " + unfilteredListings.get(0).getName() + ", ";
        responseString += "type: " + unfilteredListings.get(0).getType() + ", ";
        responseString += "city: " + unfilteredListings.get(0).getLocation() + ", ";
        responseString += "price: " + unfilteredListings.get(0).getPrice() + ", ";
        responseString += "minNights: " + unfilteredListings.get(0).getMinNights() + ", ";
        responseString += "reviews: " + unfilteredListings.get(0).getReviews();
        responseString += "}";

        for (int i = 1; i < unfilteredListings.size(); i++) {
            responseString += ", { " + "name: " + unfilteredListings.get(i).getName() + ", ";
            responseString += "type: " + unfilteredListings.get(i).getType() + ", ";
            responseString += "city: " + unfilteredListings.get(i).getLocation() + ", ";
            responseString += "price: " + unfilteredListings.get(i).getPrice() + ", ";
            responseString += "minNights: " + unfilteredListings.get(i).getMinNights() + ", ";
            responseString += "reviews: " + unfilteredListings.get(i).getReviews();
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

