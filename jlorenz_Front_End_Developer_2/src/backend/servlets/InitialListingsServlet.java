package backend.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/initiallistings")
public class InitialListingsServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
        // read request body fields here
        int quantity = request.getParameter("quantity");

		// do some processing here...
		
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