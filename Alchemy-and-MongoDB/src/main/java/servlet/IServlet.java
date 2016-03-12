package servlet;

import connector.AlchemyConnector;
import connector.MongoDBClient;
import java.io.*;
import java.util.*;
import java.net.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@WebServlet(name = "IServlet", urlPatterns = {"/IServlet"})
public class IServlet extends HttpServlet {

	private String FACE_ENDPOINT_URL = "http://gateway-a.watsonplatform.net/calls/url/URLGetRankedImageFaceTags";

 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	
		AlchemyConnector connector = new AlchemyConnector();
		//AlchemyVision service = new AlchemyVision();
		//service.setApiKey(connector.getAPIKey());

		String input_url = (String) request.getParameter("imageURL");
		StringBuilder sb = new StringBuilder();
		String line;
		
		URL face_url = new URL(FACE_ENDPOINT_URL+"?url="+input_url+"&apikey="+connector.getAPIKey()+"&outputMode=json");
		BufferedReader reader = new BufferedReader(new InputStreamReader(face_url.openStream()));
		while ((line = reader.readLine()) != null){
			sb.append(line);
		}
		request.setAttribute("extractInfo",sb.toString());
		
		try {
		MongoDBClient db = new MongoDBClient();
		db.addEntry(sb.toString());
		
		List<String> dbEntry = db.getEntry();
		JSONParser parser = new JSONParser();
		Object obj = new Object();
		JSONObject jsonObject = (JSONObject) obj;
		JSONArray jsonArray = (JSONArray) jsonObject.get(dbEntry);
		JSONObject jsonObject1 = new JSONObject();
		for(int i=0;i<=dbEntry.size();i++){
			if(i == dbEntry.size()){
				jsonObject1 = (JSONObject) jsonArray.get(i);
			}
		}
		String age = jsonObject1.get("age").toString();
		String gender = jsonObject1.get("gender").toString();
		request.setAttribute("age", age);
		request.setAttribute("gender", gender);
		request.setAttribute("msg", "Entry Added!");
		request.setAttribute("result", db.getEntry());
		
		//JSONParser parser = new JSONParser();
		//Object obj = parser.parse(db.getEntry());
            
		//JSONObject jsonObject = (JSONObject) obj;
		//System.out.println(jsonObject.toString());
		/*
		JSONArray jsonArray = (JSONArray) jsonObject.get(db.getEntry());
		for(int i=0;i<db.getEntry().size();i++){
		JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
		String age = jsonObject1.get("age").toString();
		}*/
		//request.setAttribute("age", character_count);
		}
		catch (Exception e) {
				e.printStackTrace(System.err);
			}
		//ImageFaces image_faces = service.recognizeFaces(input_url,false);
		//request.setAttribute("image_faces",image_faces);
		
		/*try{
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(sb.toString());
            
				JSONObject jsonObject = (JSONObject) obj;
				System.out.println(jsonObject.toString());
            
				String character_count = jsonObject.get("character_count").toString();
				//("character_count: " + character_count);
            
				//JSONarray []
				JSONArray jsonArray = (JSONArray) jsonObject.get("translations");
				JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
				String translation = jsonObject1.get("translation").toString();
				//alert("translation: " + translation);
            
				String word_count = jsonObject.get("word_count").toString();
				//alert("word_count: " + word_count);
				
				request.setAttribute("charCount", character_count);
				request.setAttribute("trans", translation);
				request.setAttribute("wordCount", word_count);
				
				PrintWriter writer = response.getWriter();
				DBHelper db = new DBHelper(writer);
				db.insertInto(character_count, translation);
				
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}*/
	
		response.setContentType("text/html");
        response.setStatus(200);
		
        request.getRequestDispatcher("extractedinfo.jsp").forward(request, response);

	}

}

