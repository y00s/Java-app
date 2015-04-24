package entities;

import java.util.Date;

public class Flight{
    

   String destination;
   String origin;
   int pilotID;
   int plainWingID;
   int flightTime;
   int flightID;
   Date departTime;
   
   public Flight() {
		super();
		this.destination = null;
		this.origin = null;
		this.pilotID = 0;
		this.plainWingID = 0;
		this.flightTime = 0;
		this.flightID = 0;
		this.departTime = null;
	}
   
public Flight(String destination, String origin, int pilotID,
		int plainWingID, int flightTime, int flightID, Date departTime) {
	super();
	this.destination = destination;
	this.origin = origin;
	this.pilotID = pilotID;
	this.plainWingID = plainWingID;
	this.flightTime = flightTime;
	this.flightID = flightID;
	this.departTime = departTime;
}
public Flight(Flight fl) {
	this.destination = fl.destination;
	this.origin = fl.origin;
	this.pilotID = fl.pilotID;
	this.plainWingID = fl.plainWingID;
	this.flightTime = fl.flightTime;
	this.flightID = fl.flightID;
	this.departTime = fl.departTime;
}

public Date getDepartTime() {
	return departTime;
}
public void setDepartTime(Date departTime) {
	this.departTime = departTime;
}

public String getDestination() {
	return destination;
}
public void setDestination(String destination) {
	this.destination = destination;
}
public String getOrigin() {
	return origin;
}
public void setOrigin(String origin) {
	this.origin = origin;
}
public int getPilotID() {
	return pilotID;
}
public void setPilotID(int pilotID) {
	this.pilotID = pilotID;
}
public int getPlainWingID() {
	return plainWingID;
}
public void setPlainWingID(int plainWingID) {
	this.plainWingID = plainWingID;
}
public int getFlightTime() {
	return flightTime;
}
public void setFlightTime(int flightTime) {
	this.flightTime = flightTime;
}
public int getFlightID() {
	return flightID;
}
public void setFlightID(int flightID) {
	this.flightID = flightID;
}
   
   
}
