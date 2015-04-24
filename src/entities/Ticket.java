package entities;

public class Ticket{
    
 
    
    int passengerID;
    int flightID;
    String seat;
    int serviceTypeID;
    float cost;
    int ticketCode;
    
    
	public Ticket(int passengerID, int flightID, String seat,
			int serviceTypeID, float cost,int ticketCode) {
		super();
		this.passengerID = passengerID;
		this.flightID = flightID;
		this.seat = seat;
		this.serviceTypeID = serviceTypeID;
		this.cost = cost;
		this.ticketCode = ticketCode;
	}
	
	public Ticket() {
		super();
		this.passengerID = 0;
		this.flightID = 0;
		this.seat = null;
		this.serviceTypeID = 0;
		this.cost = 0;
		this.ticketCode = 0;
	}
	public int getPassengerID() {
		return passengerID;
	}
	public void setPassengerID(int passengerID) {
		this.passengerID = passengerID;
	}
	public int getFlightID() {
		return flightID;
	}
	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public int getServiceTypeID() {
		return serviceTypeID;
	}
	public void setServiceTypeID(int serviceTypeID) {
		this.serviceTypeID = serviceTypeID;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public int getTicketCode() {
		return ticketCode;
	}
	public void setTicketCode(int ticketCode) {
		this.ticketCode = ticketCode;
	}
     
}