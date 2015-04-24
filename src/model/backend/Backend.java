package model.backend;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import entities.*;

public interface Backend {
	
	public void addFlight(Flight flght) throws Exception;
	public void deleteFlight(Flight flght)throws Exception;
	public void updateFlight(Flight flght)throws Exception;
	public List<Flight> getFlightlist() throws Exception;
	
	public void addPassenger(Passenger passenger)throws Exception;
	public void deletePassenger(Passenger passenger)throws Exception;
	public void updatePassenger(Passenger passenger)throws Exception;
	public List<Passenger> getPassengerlist() throws Exception;
	public Passenger autenticatePassenger(String user, String pass) throws Exception;
	
	public void addPilot(Pilot pilot)throws Exception;
	public void deletePilot(Pilot pilot)throws Exception;
	public void updatePilot(Pilot pilot)throws Exception;
	public List<Pilot> getPilotlist() throws Exception;

	public void addPlain(Plain plain)throws Exception;
	public void deletePlain(Plain plain)throws Exception;
	public void updatePlain(Plain plain)throws Exception;
	public List<Plain> getPlainlist() throws Exception;
	
	public void addServiceType(ServiceType st)throws Exception;
	public void deleteServiceType(ServiceType st)throws Exception;
	public void updateServiceType(ServiceType st)throws Exception;
	public List<ServiceType> getServiceTypelist() throws Exception;
	
	public void addTicket(Ticket ticket)throws Exception;
	public void deleteTicket(Ticket ticket)throws Exception;
	public void updateTicket(Ticket ticket)throws Exception;
	public List<Ticket> getTicketlist() throws Exception;
	
	
	//functions 
	public void setLists();
	public List<Flight> getPassengerFlights(Passenger p) throws Exception;
	public Flight getFlightById(int f) throws Exception;
	public Ticket getTicketById(int t) throws Exception;
	public Passenger getPassengerById(int p) throws Exception;
	public Pilot getPilotById(int p) throws Exception;
	public Plain getPlainById(int p) throws Exception;
	public ServiceType getServiceTypeById(int st) throws Exception;
	public List<Ticket> getPassengerTickets(Passenger p)throws Exception;
	
	


}
