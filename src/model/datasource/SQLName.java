package model.datasource;

public class SQLName {
	
	public static final int DataBaseVersion = 1;
	
	public static final String DataBaseName = "sql_database.db";
	
	public static final String FlightsTable = "flights_table";
	public static final String PassengersTable = "passengers_table";
	public static final String PilotsTable = "pilots_table";
	public static final String PlainsTable = "plains_table";
	public static final String ServicesTable = "services_table";
	public static final String TicketsTable = "tickets_table";
	
	//flights rows
	public static final String DestinationRow = "destination";
	public static final String OriginRow = "origin";
	public static final String PilotIDRow = "pilot_id";
	public static final String PlainWingIDRow = "plain_wing_id";
	public static final String FlightTimeRow = "flight_time";
	public static final String FlightIDRow = "flight_id";
	public static final String DepartTimeRow = "depart_time";

	//passengers rows
	public static final String PassFirstNameRow = "pass_first_name";
	public static final String PassLastNameRow = "last_name";
	public static final String EmailRow = "email";
	public static final String PhoneNumberRow = "phone_number";
	public static final String PassIDRow = "passenger_id";
	public static final String AdrressRow = "adrress";
	public static final String CreditCardRow = "credit_card";
	public static final String DobRow = "dob";
	public static final String PasswordRow = "password";
	
	//pilots rows
	public static final String PilotFirstNameRow = "pilot_first_name";
	public static final String PilotLastNameRow = "pilot_last_name";
	public static final String LicenseRow = "pilot_id";
	public static final String MaxSizePlainRow = "max_size_plain";
	public static final String MaxTimeFlightRow = "max_time_flight";
	
	//plains rows
	public static final String ModelRow = "model";
	public static final String WingIdRow = "plain_wing_id";
	public static final String NumberPassengersRow = "number_passengers";
	public static final String LengthRow = "length";
	public static final String HasVideoRow = "has_video";
	public static final String SizeRow = "size";
	
	//services rows
	public static final String ServiceIDRow = "service_id";
	public static final String ClassTypeRow = "class_type";
	public static final String BaggageWhegitRow = "baggage_whegit";
	public static final String FoodTypeRow = "food_type";
	public static final String WindowRow = "window";
	
	//tickets row
	public static final String PassengerIDRow = "passenger_id";
	public static final String FlightIDTRow = "flight_id";
	public static final String SeatRow = "seat";
	public static final String serviceTypeIDRow = "service_id";
	public static final String CostRow = "cost";
	public static final String TicketCodeRow = "ticket_code";
}