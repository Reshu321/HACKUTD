import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class FlightGenerator {
    private static HttpURLConnection connection;     

   
    
    
	public ArrayList<String> generateDestinations(String address) throws JSONException, IOException{
		
		//Arraylist of strings of destinations
		ArrayList<String> firstAirportDestIATA = new ArrayList<String>();
        
		
		//Run API for address1
        URL url = null;
        int status = -1;
        BufferedReader reader;                                         // declares reader as type BufferedReader
        String line = "";                                                 // declares line as a string 
        StringBuffer responseContent = new StringBuffer();             
        
         //if the addresses are not the address selected
            url = new URL ("http://api.travelpayouts.com/v2/prices/latest?currency=usd&period_type=year&page=1&limit=30&show_to_affiliates=true&sorting=price&trip_class=0&origin=" + address + "&token=18f98e36d329590294297456f91e7c6e");             // this is the url connection with the api key to hit the api and get its contents
            connection = (HttpURLConnection) url.openConnection();         
            connection.setRequestMethod("GET");     //connection is established here with GET
            connection.setConnectTimeout(5000);     // interval of times to wait for connection (wait time for connection to the server) 
            connection.setReadTimeout(5000);        // wait time for data to be avaliable for reading 
            status = connection.getResponseCode();                     // sets the status to the connection
            
            if(status>299)                                                                                         // checks to make sure the request was accepted then takes the contents and inputs it in a string context
            {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));                 // if request fails while trying to read the input stream then we implememt getErrorStream
            while((line = reader.readLine())!=null)                                                            // this will go line by line until there it no more content left 
            {
                responseContent.append(line);                                                                 // this will get the full response of the requested information 
            }
            reader.close();                             // this will disconnect the connection or close it 
	        }
	        else
	        {
	            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));                 //when input stream works 
	            while((line = reader.readLine())!=null)                                                            // this will go line by line until there it no more content left 
	            {
	            	
	                responseContent.append(line);                                                                 // this will get the full response of the requested information 
	            }
	            reader.close();                             // this will disconnect the connection or close it 
	        }
	        
	       
	        JSONObject object = new JSONObject (responseContent.toString()); 
	        JSONArray JSONdata = object.getJSONArray("data"); 
	        for(int i = 0; i < JSONdata.length(); i++) {
		        JSONObject rec = JSONdata.getJSONObject(i);
		        firstAirportDestIATA.add(rec.getString("destination"));
	        }
	        
	        // FINISHED RUNNING API FOR ADDRESS 1 //
        
        
        return firstAirportDestIATA;  
        
	}
	

    public static void main(String[] args) throws IOException, JSONException 
    {
        
 
        
    }

}