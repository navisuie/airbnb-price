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
public class InitialListingServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// read form fields
		String username = request.getParameter("username");


		// do some processing here...
		
		// get response writer
		PrintWriter writer = response.getWriter();
		
		// return response
		writer.println(htmlRespone);
		
	}

}