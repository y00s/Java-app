package entities;

import entities.enums.ClassType;





public class ServiceType{
    

    
 int serviceID;
 enums.ClassType classType;
 float baggageWhegit;
 String foodType;
 Boolean window;
 
 
 
 
public Boolean getWindow() {
	return window;
}
public void setWindow(Boolean window) {
	this.window = window;
}
public ServiceType() {
	super();
	this.serviceID = 0;
	this.classType = null;
	this.baggageWhegit = 0;
	this.foodType = null;
	this.window = null;
}
public ServiceType(int serviceID, ClassType classType, float baggageWhegit,
		String foodType,Boolean window) {
	super();
	this.serviceID = serviceID;
	this.classType = classType;
	this.baggageWhegit = baggageWhegit;
	this.foodType = foodType;
	this.window = window;
}
public int getServiceID() {
	return serviceID;
}
public void setServiceID(int serviceID) {
	this.serviceID = serviceID;
}
public enums.ClassType getClassType() {
	return classType;
}
public void setClassType(enums.ClassType classType) {
	this.classType = classType;
}
public float getBaggageWhegit() {
	return baggageWhegit;
}
public void setBaggageWhegit(float baggageWhegit) {
	this.baggageWhegit = baggageWhegit;
}
public String getFoodType() {
	return foodType;
}
public void setFoodType(String foodType) {
	this.foodType = foodType;
}
 
 
}