package backend.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import backend.business.BackEnd;
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

        String[] cities = BackEnd.listCities();
        // do some processing here...
        
        // create response here
        
        String responseString = "{";
        responseString += "count: " + cities.length + ",";
        responseString += "cityNames: [";
        responseString += cities[0];
        for (int i = 1; i < cities.length; i++) {
            responseString += ", " + cities[i];
        } // adding cities to response string 
                      

        responseString += "]}";

        
        // return response
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(responseString);
        out.flush();
        
    }

}
