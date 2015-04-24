package entities;

import java.io.Serializable;
import java.util.Date;

public class Passenger implements Serializable{


/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
String fName;
String lName;
String email;
String phoneNumber;
int id;
String adrress;
int creditCard;
Date dob;
String password;



public Passenger(String fName, String lName, String email, String phoneNumber,
		int id, String adrress, int creditCard, Date dob,String password) {
	super();
	this.fName = fName;
	this.lName = lName;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.id = id;
	this.adrress = adrress;
	this.creditCard = creditCard;
	this.dob = dob;
	this.password = password;
	
}

public Passenger() {
	this.fName = null;
	this.lName = null;
	this.email = null;
	this.phoneNumber = null;
	this.id = 0;
	this.adrress = null;
	this.creditCard = 0;
	this.dob = null;
	this.password = null;
}

public Passenger(Passenger p) {
	this.fName = p.fName;
	this.lName = p.lName;
	this.email = p.email;
	this.phoneNumber = p.phoneNumber;
	this.id = p.id;
	this.adrress = p.adrress;
	this.creditCard = p.creditCard;
	this.dob = p.dob;
	this.password = p.password;
}

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getAdrress() {
	return adrress;
}
public void setAdrress(String adrress) {
	this.adrress = adrress;
}
public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
}

public String getfName() {
	return fName;
}
public void setfName(String fName) {
	this.fName = fName;
}
public String getlName() {
	return lName;
}
public void setlName(String lName) {
	this.lName = lName;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getCreditCard() {
	return creditCard;
}
public void setCreditCard(int creditCard) {
	this.creditCard = creditCard;
}

public boolean rightPassword(String user, String pass) {
	
	if ((email.compareTo(user)==0) &&
			(password.compareTo(pass)==0)) {
		return true;

	}
	return false;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password=password;
	
}

}

