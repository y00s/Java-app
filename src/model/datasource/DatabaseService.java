package model.datasource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.backend.Backend;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;







import android.util.Log;

import com.google.gson.Gson;

import entities.Flight;
import entities.Passenger;
import entities.Pilot;
import entities.Plain;
import entities.ServiceType;
import entities.Ticket;


public class DatabaseService implements Backend {
	//TODO change url
	public  static  final String URL =  
				//	"http://localhost:9021/javaProjectService/api/db/"; 
			"https://javapp.herokuapp.com/api/db/"; 
			//"http://10.0.2.2:9021/javaProjectService/api/db/";



	private String GET(String url){ 
		InputStream inputStream = null; 
		String result = ""; 
		try { 
			HttpClient httpclient = new DefaultHttpClient(); 
			HttpResponse httpResponse = httpclient.execute(new 
					HttpGet(url));  
			inputStream = httpResponse.getEntity().getContent(); 
			if(inputStream != null) 
				result = convertInputStreamToString(inputStream); 
			else 
				result = "Did not work!"; 
		} catch (Exception e) { 
			Log.d("InputStream", e.getLocalizedMessage()); 
		} 
		return  result; 
	}  

	private String POST(String url, String jsonParamsAsString){ 
		String result = ""; 
		try { 
			DefaultHttpClient httpClient = new DefaultHttpClient(); 
			HttpPost postRequest = new HttpPost(url); 
			StringEntity input = new 
					StringEntity(jsonParamsAsString); 
			input.setContentType("application/json; charset=utf-8"); 
			postRequest.setEntity(input); 
			HttpResponse response = httpClient.execute(postRequest); 
			result = convertInputStreamToString( 
					response.getEntity().getContent());  
			byte ptext[] = result.getBytes(); 
			result = new String(ptext);  

		} catch (Exception e) { 

		} 
		return result; 
	}  
	private String convertInputStreamToString(InputStream inputStream)  
			throws IOException{ 
		BufferedReader bufferedReader = new BufferedReader(new 
				InputStreamReader(inputStream)); 
		String line = ""; 
		String result = ""; 
		while((line = bufferedReader.readLine()) != null) 
			result += line; 
		inputStream.close(); 
		return  result; 

	}  

	@SuppressWarnings("deprecation")
	@Override
	public void addFlight(Flight flght) throws Exception {
		//				Gson gson = new Gson();
		//				String result = POST(URL+"addFlight", gson.toJson(flght, Flight.class)); 
		//				if (!result.equals("success")) 
		//					throw  new Exception("Connection Problems"); 

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.getSerializationConfig().setDateFormat(dateFormat);
		String toJson = objectMapper.writeValueAsString(flght);
		String result = POST(URL+"addFlight", toJson);

		if (!result.equals("success")) 
			throw  new Exception("Connection Problems"); 

	}

	@Override
	public void deleteFlight(Flight flght) throws Exception {
		String result = GET(URL +"deleteFlightByID?flightID="+flght.getFlightID());
		if (!result.equals("success")) 
			throw  new Exception("Connection Problems"); 

	}

	@Override
	public void updateFlight(Flight flght) throws Exception {
		//		Gson gson = new Gson();
		//		String result = POST(URL+"updateFlight", gson.toJson(flght, Flight.class));
		//		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.getSerializationConfig().setDateFormat(dateFormat);
		String toJson = objectMapper.writeValueAsString(flght);
		String result = POST(URL+"updateFlight", toJson);

		if (!result.equals("success")) 
			throw  new Exception("Connection Problems"); 

	}

	@Override
	public List<Flight> getFlightlist() throws Exception {
		//
		//		String result = GET(URL +"getFlightList");
		//		Gson gson = new Gson();
		//		return new ArrayList<Flight>
		//		(Arrays.asList(gson.fromJson(result, Flight[].class)));

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ObjectMapper objectMapper = new ObjectMapper();
		String result = GET(URL + "getFlightList");
		objectMapper.getDeserializationConfig().setDateFormat(dateFormat);
		return new ArrayList<Flight>(Arrays.asList(objectMapper.readValue(result, Flight[].class)));



	}

	@Override
	public void addPassenger(Passenger passenger) throws Exception {
		//		Gson gson = new Gson();
		//		String result = POST(URL+"addPassenger", gson.toJson(passenger,Passenger.class)); 
		//		if (!result.equals("success")) 
		//			throw  new Exception("Connection Problems"); 

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.getSerializationConfig().setDateFormat(dateFormat);
		String toJson = objectMapper.writeValueAsString(passenger);
		String result = POST(URL+"addPassenger", toJson);

		if (!result.equals("success")) 
			throw  new Exception("Connection Problems"); 

	}

	@Override
	public void deletePassenger(Passenger passenger) throws Exception {
		String result = GET(URL +"deletePassengerByID?passengerID="+passenger.getId());
		if (!result.equals("success")) 
			throw  new Exception("Connection Problems"); 

	}

	@Override
	public void updatePassenger(Passenger passenger) throws Exception {
		//		Gson gson = new Gson();
		//		String result = POST(URL+"updatePassenger", gson.toJson(passenger, Passenger.class)); 
		//		if (!result.equals("success")) 
		//			throw  new Exception("Connection Problems"); 

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.getSerializationConfig().setDateFormat(dateFormat);
		String toJson = objectMapper.writeValueAsString(passenger);
		String result = POST(URL+"addPassenger", toJson);

		if (!result.equals("success")) 
			throw  new Exception("Connection Problems"); 
	}

	@Override
	public List<Passenger> getPassengerlist() throws Exception {
		//		String result = GET(URL +"getPassengerList");
		//		Gson gson = new Gson();
		//		return new ArrayList<Passenger>
		//		(Arrays.asList(gson.fromJson(result, Passenger[].class)));
		//		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ObjectMapper objectMapper = new ObjectMapper();
		String result = GET(URL + "getPassengerList");
		objectMapper.getDeserializationConfig().setDateFormat(dateFormat);
		return new ArrayList<Passenger>(Arrays.asList(objectMapper.readValue(result, Passenger[].class)));


	}

	@Override
	public void addPilot(Pilot pilot) throws Exception {
		Gson gson = new Gson();
		String result = POST(URL+"addPilot", gson.toJson(pilot,Pilot.class)); 
		if (!result.equals("success")) 
			throw  new Exception("Connection Problems"); 

	}

	@Override
	public void deletePilot(Pilot pilot) throws Exception {
		String result = GET(URL +"deletePilotByID?pilotID="+pilot.getLicense());
		if (!result.equals("success")) 
			throw  new Exception("Connection Problems"); 

	}

	@Override
	public void updatePilot(Pilot pilot) throws Exception {
		Gson gson = new Gson();
		String result = POST(URL+"updatePilot", gson.toJson(pilot, Pilot.class)); 
		if (!result.equals("success")) 
			throw  new Exception("Connection Problems"); 

	}

	@Override
	public List<Pilot> getPilotlist() throws Exception {
		String result = GET(URL +"getPilotList");
		Gson gson = new Gson();
		return new ArrayList<Pilot>
		(Arrays.asList(gson.fromJson(result, Pilot[].class)));

	}

	@Override
	public void addPlain(Plain plain) throws Exception {
		Gson gson = new Gson();
		String result = POST(URL+"addPlain", gson.toJson(plain,Plain.class)); 
		if (!result.equals("success")) 
			throw  new Exception("Connection Problems"); 

	}

	@Override
	public void deletePlain(Plain plain) throws Exception {
		String result = GET(URL +"deletePlainByID?plainID="+plain.getWingId());
		if (!result.equals("success")) 
			throw  new Exception("Connection Problems"); 

	}

	@Override
	public void updatePlain(Plain plain) throws Exception {
		Gson gson = new Gson();
		String result = POST(URL+"updatePlain", gson.toJson(plain, Plain.class)); 
		if (!result.equals("success")) 
			throw  new Exception("Connection Problems"); 

	}

	@Override
	public List<Plain> getPlainlist() throws Exception {
		String result = GET(URL +"getPlainList");
		Gson gson = new Gson();
		return new ArrayList<Plain>
		(Arrays.asList(gson.fromJson(result, Plain[].class)));

	}

	@Override
	public void addServiceType(ServiceType st) throws Exception {
		Gson gson = new Gson();
		String result = POST(URL+"addServiceType", gson.toJson(st,ServiceType.class)); 
		if (!result.equals("success")) 
			throw  new Exception("Connection Problems"); 

	}

	@Override
	public void deleteServiceType(ServiceType st) throws Exception {
		String result = GET(URL +"deleteServiceByID?serviceID="+st.getServiceID());
		if (!result.equals("success")) 
			throw  new Exception("Connection Problems"); 

	}

	@Override
	public void updateServiceType(ServiceType st) throws Exception {
		Gson gson = new Gson();
		String result = POST(URL+"updateService", gson.toJson(st, ServiceType.class)); 
		if (!result.equals("success")) 
			throw  new Exception("Connection Problems"); 

	}

	@Override
	public List<ServiceType> getServiceTypelist() throws Exception {
		String result = GET(URL +"getServiceList");
		Gson gson = new Gson();
		return new ArrayList<ServiceType>
		(Arrays.asList(gson.fromJson(result, ServiceType[].class)));

	}

	@Override
	public void addTicket(Ticket ticket) throws Exception {
		Gson gson = new Gson();
		String result = POST(URL+"addTicket", gson.toJson(ticket,Ticket.class)); 
		if (!result.equals("success")) 
			throw  new Exception("Connection Problems"); 

	}

	@Override
	public void deleteTicket(Ticket ticket) throws Exception {
		String result = GET(URL +"deleteTicketByID?ticketID="+ticket.getTicketCode());
		if (!result.equals("success")) 
			throw  new Exception("Connection Problems"); 

	}

	@Override
	public void updateTicket(Ticket ticket) throws Exception {
		Gson gson = new Gson();
		String result = POST(URL+"updateTicket", gson.toJson(ticket, Ticket.class)); 
		if (!result.equals("success")) 
			throw  new Exception("Connection Problems"); 

	}

	@Override
	public List<Ticket> getTicketlist() throws Exception {
		String result = GET(URL +"getTicketList");
		Gson gson = new Gson();
		return new ArrayList<Ticket>
		(Arrays.asList(gson.fromJson(result, Ticket[].class)));

	}

	@Override
	public void setLists() {
		// no need - finally is a real data base

	}

	@Override
	public Passenger autenticatePassenger(String user, String pass) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ObjectMapper objectMapper = new ObjectMapper();
		String result = GET(URL + "autenticatePassenger?user="+user+"?pass="+pass);
		objectMapper.getDeserializationConfig().setDateFormat(dateFormat);
		return new Passenger(objectMapper.readValue(result, Passenger.class));

	}

	@Override
	public List<Flight> getPassengerFlights(Passenger p) throws Exception {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ObjectMapper objectMapper = new ObjectMapper();
		String result = GET(URL + "getPassengerFlights?passengerID="+p.getId());
		objectMapper.getDeserializationConfig().setDateFormat(dateFormat);
		return new ArrayList<Flight>(Arrays.asList(objectMapper.readValue(result, Flight[].class)));


	}



	@Override
	public Passenger getPassengerById(int p) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pilot getPilotById(int p) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Ticket> getPassengerTickets(Passenger p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flight getFlightById(int f) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ObjectMapper objectMapper = new ObjectMapper();
		String result = GET(URL + "getFlightById?flightID="+String.valueOf(f));
		objectMapper.getDeserializationConfig().setDateFormat(dateFormat);
		return new Flight(objectMapper.readValue(result, Flight.class));


	}

	@Override
	public Ticket getTicketById(int t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plain getPlainById(int p) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceType getServiceTypeById(int st) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



}
