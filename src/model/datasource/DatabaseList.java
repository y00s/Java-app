package model.datasource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.media.Image.Plane;
import android.text.format.DateFormat;
import android.widget.Toast;
import entities.Flight;
import entities.Passenger;
import entities.Pilot;
import entities.Plain;
import entities.ServiceType;
import entities.Ticket;
import entities.enums;
import model.backend.Backend;
import model.backend.BackendFactory;

public class DatabaseList implements Backend {
	
	List<Flight> flights = new ArrayList<Flight>();
	List<Passenger> passengers = new ArrayList<Passenger>();
	List<Pilot> pilots = new ArrayList<Pilot>();
	List<Plain> plains = new ArrayList<Plain>();
	List<ServiceType> services = new ArrayList<ServiceType>();
	List<Ticket> tickets = new ArrayList<Ticket>();

	int flightsListCounter = 0;
	int passangersListCounter = 0;
	int pilotsListCounter = 0;
	int plainsListCounter = 0;
	int servicesListCounter = 0;
	int ticketsListCounter = 0;
	
	@Override
	public void addFlight(Flight flght) throws Exception {
		for (Flight flight : flights) {
			if (flight.getFlightID()==flght.getFlightID()) {
				throw new Exception("flight already exist");
				
			}
		}
		flights.add(flght);
		flightsListCounter++;
		

	}

	@Override
	public void deleteFlight(Flight flght) throws Exception {
		for (Flight flight : flights) {
			if (flight.getFlightID()==flght.getFlightID()) {
				flights.remove(flight);
				flightsListCounter--;
				return;
							
			}
		}
		throw new Exception("flight dont exist");

	}

	@Override
	public void updateFlight(Flight flght) throws Exception {
		for (Flight flight : flights) {
			if (flight.getFlightID()==flght.getFlightID()) {
				
				flights.remove(flight);
				flights.add(flght);
				return;
				
			}
		}
		throw new Exception("flight dont exist");

	}

	@Override
	public List<Flight> getFlightlist() throws Exception {
		return flights;

	}

	@Override
	public void addPassenger(Passenger passenger) throws Exception {
		for (Passenger p : passengers) {
			if (p.getId()==passenger.getId()) {
				throw new Exception("passenger already exist");
				
			}
		}
		passengers.add(passenger);
		passangersListCounter++;

	}

	@Override
	public void deletePassenger(Passenger passenger) throws Exception {
		for (Passenger p : passengers) {
			if (p.getId()==passenger.getId()) {
				passengers.remove(p);
				passangersListCounter--;
				return;
			}
		}
		
		throw new Exception("passenger dont exist");

	}

	@Override
	public void updatePassenger(Passenger passenger) throws Exception {
		for (Passenger p : passengers) {
			if (p.getId()==passenger.getId()) {
				passengers.remove(p);
				passengers.add(passenger);
				return;
			}
		}
		
		throw new Exception("passenger dont exist");

	}

	@Override
	public List<Passenger> getPassengerlist() throws Exception {
		return passengers;

	}

	

	@Override
	public Passenger autenticatePassenger(String user, String pass) {
	for (int i = 0; i < passengers.size(); i++) {
			
			if (passengers.get(i).getEmail().compareTo(user)==0) {
				if (passengers.get(i).rightPassword(user,pass)) {
					return passengers.get(i);
				}
			}
			
		}
		return null;
	}
	
	
	@Override
	public void addPilot(Pilot pilot) throws Exception {
		for (Pilot p : pilots) {
			if (p.getLicense()==pilot.getLicense()) {
				throw new Exception("pilot already exist");
				
			}
		}
		pilots.add(pilot);
		pilotsListCounter++;

	}

	@Override
	public void deletePilot(Pilot pilot) throws Exception {
		for (Pilot p : pilots) {
			if (p.getLicense()==pilot.getLicense()) {
				pilots.remove(p);
				pilotsListCounter--;
				return;
			}
		}
		
		throw new Exception("pilot dont exist");

	}

	@Override
	public void updatePilot(Pilot pilot) throws Exception {
		for (Pilot p : pilots) {
			if (p.getLicense()==pilot.getLicense()) {
				pilots.remove(p);
				pilots.add(pilot);
				return;
			}
		}
		
		throw new Exception("pilot dont exist");

	}

	@Override
	public List<Pilot> getPilotlist() throws Exception {
		return pilots;

	}

	@Override
	public void addPlain(Plain plain) throws Exception {
		for (Plain p : plains) {
			if (p.getWingId()==plain.getWingId()) {
				throw new Exception("plain already exist");
				
			}
		}
		plains.add(plain);
		plainsListCounter++;

	}

	@Override
	public void deletePlain(Plain plain) throws Exception {
		for (Plain p : plains) {
			if (p.getWingId()==plain.getWingId()) {
				plains.remove(p);
				plainsListCounter--;
				return;
			}
		}
		
		throw new Exception("plain dont exist");

	}

	@Override
	public void updatePlain(Plain plain) throws Exception {
		for (Plain p : plains) {
			if (p.getWingId()==plain.getWingId()) {
				plains.remove(p);
				plains.add(plain);
				return;
			}
		}
		
		throw new Exception("plain dont exist");

	}

	@Override
	public List<Plain> getPlainlist() throws Exception {
		return plains;

	}

	@Override
	public void addServiceType(ServiceType serviceType) throws Exception {
		for (ServiceType s : services) {
			if (s.getServiceID()==serviceType.getServiceID()) {
				throw new Exception("ServiceType already exist");
				
			}
		}
		services.add(serviceType);
		servicesListCounter++;

	}

	@Override
	public void deleteServiceType(ServiceType serviceType) throws Exception {
		for (ServiceType s : services) {
			if (s.getServiceID()==serviceType.getServiceID()) {
				services.remove(s);
				servicesListCounter--;
				return;
			}
		}
		
		throw new Exception("ServiceType dont exist");

	}

	@Override
	public void updateServiceType(ServiceType serviceType) throws Exception {
		for (ServiceType s : services) {
			if (s.getServiceID()==s.getServiceID()) {
				services.remove(s);
				services.add(serviceType);
				return;
			}
		}
		
		throw new Exception("ServiceType dont exist");

	}

	@Override
	public List<ServiceType> getServiceTypelist() throws Exception {
		return services;

	}

	@Override
	public void addTicket(Ticket ticket) throws Exception {
		for (Ticket t : tickets) {
			if (t.getTicketCode()==ticket.getTicketCode()) {
				throw new Exception("ticket already exist");
				
			}
		}
		ticket.setTicketCode(ticketsListCounter++);
		tickets.add(ticket);
		

	}

	@Override
	public void deleteTicket(Ticket ticket) throws Exception {
		for (Ticket t : tickets) {
			if (t.getTicketCode()==ticket.getTicketCode()) {
				tickets.remove(t);
				ticketsListCounter--;
				return;
			}
		}
		
		throw new Exception("ticket dont exist");

	}

	@Override
	public void updateTicket(Ticket ticket) throws Exception {
		for (Ticket t : tickets) {
			if (t.getTicketCode()==ticket.getTicketCode()) {
				tickets.remove(t);
				tickets.add(ticket);
				return;
			}
		}
		
		throw new Exception("ticket dont exist");

	}

	@Override
	public List<Ticket> getTicketlist() throws Exception {
		return tickets;
		
	}

	@Override
	public List<Flight> getPassengerFlights(Passenger p) throws Exception {
		List<Flight> myFlights = new ArrayList<Flight>();
			//find my tickets if the id in the ticket is my id
		
		for (Ticket t : tickets) {
			
			if (t.getPassengerID()==p.getId()) {
				//add the flight to my flights if is the same as id as in my ticket
				for (Flight flight : flights) {
					
					if (flight.getFlightID()==t.getFlightID()){
						myFlights.add(flight);
						}	
				}
			}
		}
		return myFlights;
		
	}
	
	@Override
	public void setLists() {
		this.passengers.add(new Passenger("yosef", "binjamini","yosyes@gmail.com", "770", 1, "somwhere", 123, null, "1"));
		this.passengers.add(new Passenger("eder", "yphrach","edi.otg@gmail.com", "613", 2, "somwhere", 123, null, "1"));
		this.passengers.add(new Passenger("oshri", "cohen","somthing@gmail.com", "1080",3, "somwhere", 123, null, "1"));
		
		
		Calendar cal = Calendar.getInstance();
		cal.set(2015, 03, 02, 12, 00);
		Date d = cal.getTime();
		this.flights.add(new Flight("tlv","jrm",  11, 100, 50, 100, d));
		cal.set(2015, 04, 02, 12, 00);
		d = cal.getTime();
		this.flights.add(new Flight("tlv","elt",  11, 100, 100, 101, d));
		cal.set(2015, 04, 02, 17, 00);
		d = cal.getTime();
		this.flights.add(new Flight("tlv","k8",  11, 101, 60,102, d));
		cal.set(2015, 01, 30, 12, 00);
		d = cal.getTime();
		this.flights.add(new Flight("tlv","elt",  11, 101, 50, 103, d));
		cal.set(2015, 01, 30, 06, 00);
		d = cal.getTime();
		this.flights.add(new Flight("tlv","nyc",  11, 101, 50, 104, d));
		cal.set(2015, 02, 02, 13, 00);
		d = cal.getTime();
		this.flights.add(new Flight("tlv","rio",  11, 102, 110, 105, d));
		cal.set(2015, 02, 02, 10, 00);
		d = cal.getTime();
		this.flights.add(new Flight("tlv","ita",  11, 102, 50, 106,d));
		cal.set(2015, 01, 02, 12, 00);
		d = cal.getTime();
		this.flights.add(new Flight("tlv","ams",  11, 102, 100, 107,d));
		cal.set(2015, 02, 02, 23, 00);
		d = cal.getTime();
		this.flights.add(new Flight("tlv","snf",  11, 103, 60, 108,d));
		cal.set(2015, 04, 02, 12, 00);
		d = cal.getTime();
		this.flights.add(new Flight("jrm","elt",  11,103, 50, 109,d));
		d.setTime(d.getTime()+30);
		this.flights.add(new Flight("jrm","tlv",  11, 103, 50, 110,d));
		d.setTime(d.getTime()+30);
		this.flights.add(new Flight("jrm","k8",  11, 104, 110, 111,d));
		d.setTime(d.getTime()+30);
		this.flights.add(new Flight("jrm","rio",  11, 104, 50, 121,d));
		d.setTime(d.getTime()+30);
		this.flights.add(new Flight("jrm","nyc",  11, 104, 100, 122,d));
		d.setTime(d.getTime()+30);
		this.flights.add(new Flight("jrm","k8",  11, 103, 60, 123,d));
		d.setTime(d.getTime()+30);
		this.flights.add(new Flight("jrm","elt",  11, 102, 50,124,d));
		d.setTime(d.getTime()+30);
		this.flights.add(new Flight("nyc","jrm",  11, 101, 50, 125,d));
		d.setTime(d.getTime()+30);
		this.flights.add(new Flight("nyc","tlv",  11, 101, 110, 126,d));
		d.setTime(d.getTime()+30);
		
	
		this.tickets.add(new Ticket(1, 100, "12c", 1, 190,1));
		this.tickets.add(new Ticket(1, 102, "1a", 2, 180,2));
		this.tickets.add(new Ticket(2, 103, "7b", 3, 400,3));
		this.tickets.add(new Ticket(2, 104, "3d", 4, 320,4));
		this.tickets.add(new Ticket(3, 101, "2a", 5, 120,5));
		this.tickets.add(new Ticket(3, 102, "12a", 6, 300,6));
		
		this.plains.add(new Plain("boing 737", 100, 60, 1100, true,enums.PlainSize.SMALL));
		this.plains.add(new Plain("boing 737-1", 101, 80, 1500, true,enums.PlainSize.MEDIUM));
		this.plains.add(new Plain("boing 747", 102, 100, 1700, true,enums.PlainSize.BIG));
		this.plains.add(new Plain("boing 777", 103, 120, 2000, true,enums.PlainSize.HUGE));
		this.plains.add(new Plain("boing 737", 104, 60, 1100, true,enums.PlainSize.SMALL));
		
		this.services.add(new ServiceType(1, enums.ClassType.BUSSINES,(float) 22.5,"fish" , true));
		this.services.add(new ServiceType(2, enums.ClassType.ECONOMY,(float) 20,"kosher" , false));
		this.services.add(new ServiceType(3, enums.ClassType.ECONOMY,(float) 25,"meat" , true));
		this.services.add(new ServiceType(4, enums.ClassType.BUSSINES,(float) 27.5,"vegi" , false));
		this.services.add(new ServiceType(5, enums.ClassType.FIRST,(float) 28.5,"meat" , true));
		this.services.add(new ServiceType(6, enums.ClassType.FIRST,(float) 33.5,"kosher" , true));
	
	}

	
	@Override
	public Flight getFlightById(int f) throws Exception {
		for (Flight flight : flights) {
			if (flight.getFlightID()==f) {
				return flight;
			}
		}
		return null;
	}

	@Override
	public Ticket getTicketById(int t) throws Exception {
		for (Ticket ticket : tickets) {
			if (ticket.getTicketCode()==t) {
				return ticket;
			}
		}
		return null;
	}

	@Override
	public Passenger getPassengerById(int p) throws Exception {
		for (Passenger	passenger : passengers) {
			if (passenger.getId()==p) {
				return passenger;

			}
		}
		return null;
	}

	@Override
	public Pilot getPilotById(int p) throws Exception {
		for (Pilot pilot : pilots) {
			if (pilot.getLicense()==p) {
				return pilot;
			}
		}
		return null;
	}

	@Override
	public Plain getPlainById(int p) throws Exception {
		for (Plain plain : plains) {
			if (plain.getWingId()==p) {
				return plain;
			}
		}
		return null;
	}

	@Override
	public ServiceType getServiceTypeById(int st) throws Exception {
		for (ServiceType serviceType : services) {
			if (serviceType.getServiceID()==st) {
				return serviceType;				
			}
		}
		return null;
	}

	@Override
	public List<Ticket> getPassengerTickets(Passenger p) {
		List<Ticket> t = new ArrayList<Ticket>();
		for (Ticket ticket : tickets) {
			if (ticket.getPassengerID() == p.getId()) {
				t.add(ticket);			
			}
		}
		
		return t;
	}


}
