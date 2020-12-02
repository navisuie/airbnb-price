import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/getAirBnb")
public class GetAirBnB extends HttpServlet {

	Backend backend = new Backend();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String cityName = request.getParameter("cityName"); 
		
		ArrayList<Airbnb> listOfAirbnbs = backend.getAirBnbs(cityName);
		
		PrintWriter writer = response.getWriter();
		
		JSONArray airbnbList = new JSONArray();
		for (Airbnb airbnb : listOfAirbnbs) {
			JSONObject singleAirbnb = new JSONObject();
			try {
				singleAirbnb.put("city", airbnb.getCity());
				singleAirbnb.put("ID", airbnb.getID());
				singleAirbnb.put("hostID", airbnb.getHostID());
				singleAirbnb.put("roomType", airbnb.getRoomType());
				singleAirbnb.put("price", airbnb.getPrice());
				singleAirbnb.put("minNights", airbnb.getMinNights());
			} catch (JSONException e) {
				e.printStackTrace();
			} 
			airbnbList.put(singleAirbnb);
		}	
		writer.println(airbnbList);
	}

}