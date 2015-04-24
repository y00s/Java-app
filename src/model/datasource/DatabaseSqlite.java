package model.datasource;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import entities.*;
import model.backend.Backend;
import model.datasource.SQLName;

public class DatabaseSqlite extends SQLiteOpenHelper implements Backend {
	
	public DatabaseSqlite(Context context) {
		
		super(context , SQLName.DataBaseName , null , SQLName.DataBaseVersion);
	}
		@Override
		public void onCreate(SQLiteDatabase db) {

			String CreateTable = "CREATE TABLE " + SQLName.PassengersTable + "("
					+ SQLName.PassFirstNameRow + " TEXT,"
					+ SQLName.PassLastNameRow + " TEXT,"
					+ SQLName.EmailRow + " TEXT,"
					+ SQLName.PhoneNumberRow + " TEXT,"
					+ SQLName.PassIDRow + " INTEGER PRIMARY KEY,"
					+ SQLName.AdrressRow + " TEXT,"
					+ SQLName.CreditCardRow + " INTEGER,"
					+ SQLName.DobRow + " TEXT,"
					+ SQLName.PasswordRow + " TEXT)";
			db.execSQL(CreateTable);
			     
			CreateTable = "CREATE TABLE " + SQLName.PilotsTable + "("
					+ SQLName.PilotFirstNameRow + " TEXT,"
					+ SQLName.PilotLastNameRow + " TEXT,"
					+ SQLName.LicenseRow + " INTEGER PRIMARY KEY,"
					+ SQLName.MaxSizePlainRow + " TEXT,"
					+ SQLName.MaxTimeFlightRow + " INTEGER)";
			db.execSQL(CreateTable);
	
		   
			CreateTable = "CREATE TABLE " + SQLName.PlainsTable + "("
					+ SQLName.ModelRow + " TEXT,"
					+ SQLName.WingIdRow + " INTEGER PRIMARY KEY,"
					+ SQLName.NumberPassengersRow + " INTEGER,"
					+ SQLName.LengthRow + " REAL,"
					+ SQLName.HasVideoRow + " INTEGER,"
					+ SQLName.SizeRow + " TEXT)";
			db.execSQL(CreateTable);
			      
			CreateTable = "CREATE TABLE " + SQLName.ServicesTable + "("
					+ SQLName.ServiceIDRow + " INTEGER PRIMARY KEY,"
					+ SQLName.ClassTypeRow + " TEXT,"
					+ SQLName.BaggageWhegitRow + " REAL,"
					+ SQLName.FoodTypeRow + " TEXT,"
					+ SQLName.WindowRow + " INTEGER)";
			db.execSQL(CreateTable);
	
			   
			CreateTable = "CREATE TABLE " + SQLName.FlightsTable + "("
					+ SQLName.DestinationRow + " TEXT,"
					+ SQLName.OriginRow + " TEXT,"
					+ SQLName.PilotIDRow + " INTEGER,"
					+ SQLName.PlainWingIDRow + " INTEGER,"
					+ SQLName.FlightTimeRow + " INTEGER,"
					+ SQLName.FlightIDRow + " INTEGER PRIMARY KEY,"
					+ SQLName.DepartTimeRow + " TEXT,"
					+"constraint fk1 foreign key" + "(" + SQLName.PlainWingIDRow + ") references " 
					+ SQLName.PlainsTable + "(" + SQLName.WingIdRow + ") on delete SET NULL on update SET NULL)";
			
			db.execSQL(CreateTable);
		    
			CreateTable = "CREATE TABLE " + SQLName.TicketsTable + "("
					+ SQLName.PassengerIDRow + " INTEGER,"
					+ SQLName.FlightIDTRow + " INTEGER,"
					+ SQLName.SeatRow + " TEXT,"
					+ SQLName.serviceTypeIDRow + " INTEGER,"
					+ SQLName.CostRow + " REAL,"
					+ SQLName.TicketCodeRow + " INTEGER PRIMARY KEY,"
					+"constraint fk2 foreign key" + "(" + SQLName.PassengerIDRow + ") references " + SQLName.PassengersTable + "(" + SQLName.PassIDRow +") on delete SET NULL on update SET NULL,"
					+"constraint fk3 foreign key" + "(" + SQLName.FlightIDTRow + ") references " + SQLName.FlightsTable + "(" +SQLName.FlightIDRow + ") on delete SET NULL on update SET NULL,"
					+"constraint fk4 foreign key" + "(" + SQLName.serviceTypeIDRow + ") references " + SQLName.ServicesTable + "(" + SQLName.ServiceIDRow + ") on delete SET NULL on update SET NULL)";
			db.execSQL(CreateTable);
				 
		}//end onCreate()
		 
		 @Override
		 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			  
			 db.execSQL("DROP TABLE IF EXISTS " + SQLName.FlightsTable);
			 db.execSQL("DROP TABLE IF EXISTS " + SQLName.PassengersTable);
			 db.execSQL("DROP TABLE IF EXISTS " + SQLName.PilotsTable);
			 db.execSQL("DROP TABLE IF EXISTS " + SQLName.PlainsTable);
			 db.execSQL("DROP TABLE IF EXISTS " + SQLName.ServicesTable);
			 db.execSQL("DROP TABLE IF EXISTS " + SQLName.TicketsTable);
			 
			 onCreate(db);   
		 }
		 
	//========================================== 
	// flight 
	//==========================================

	@Override
	public void addFlight(Flight flight) throws Exception {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(SQLName.DestinationRow, flight.getDestination());
		values.put(SQLName.OriginRow, flight.getOrigin());
		values.put(SQLName.PilotIDRow, flight.getPilotID());	   
		values.put(SQLName.PlainWingIDRow, flight.getPlainWingID());
		values.put(SQLName.FlightTimeRow, flight.getFlightTime());
		values.put(SQLName.FlightIDRow, flight.getFlightID());
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		values.put(SQLName.DepartTimeRow,dateFormat.format(flight.getDepartTime()) );
		
		db.insertWithOnConflict(SQLName.FlightsTable, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		db.close();
	}

	@Override
	public void deleteFlight(Flight flight) throws Exception {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(SQLName.FlightsTable, SQLName.FlightIDRow + "= ?"
				, new String[] {String.valueOf(flight.getFlightID())});		
	}

	@Override
	public void updateFlight(Flight flight) throws Exception {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(SQLName.DestinationRow, flight.getDestination());
		values.put(SQLName.OriginRow, flight.getOrigin());
		values.put(SQLName.PilotIDRow, flight.getPilotID());
		values.put(SQLName.PlainWingIDRow, flight.getPlainWingID());
		values.put(SQLName.FlightTimeRow, flight.getFlightTime());
		values.put(SQLName.FlightIDRow, flight.getFlightID());

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		values.put(SQLName.DepartTimeRow, dateFormat.format(flight.getDepartTime()));
		
		db.update(SQLName.FlightsTable, values, SQLName.FlightIDRow + "= ?"
				, new String[] {String.valueOf(flight.getFlightID())});
		db.close();
	}

	@Override
	public List<Flight> getFlightlist() throws Exception {
		List<Flight> FlightList = new ArrayList<Flight>();
		String SelectQuery = "SELECT * FROM " + SQLName.FlightsTable;
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(SelectQuery, null) ;
		
		if(cursor.moveToFirst()){
			do{
				Flight flight = new Flight(null, null, 0, 0, 0, 0, null);
				flight.setDestination(cursor.getString(0));
				flight.setOrigin(cursor.getString(1));
				flight.setPilotID(cursor.getInt(2));
				flight.setPlainWingID(cursor.getInt(3));
				flight.setFlightTime(cursor.getInt(4));
				flight.setFlightID(cursor.getInt(5));
				SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				try {
					flight.setDepartTime(formater.parse(cursor.getString(6)));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
				FlightList.add(flight);
			}while(cursor.moveToNext());
		}
		return FlightList;
	}

	//========================================== 
	// passenger 
	//==========================================
	
	@Override
	public void addPassenger(Passenger passenger) throws Exception {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(SQLName.PassFirstNameRow, passenger.getfName());
		values.put(SQLName.PassLastNameRow, passenger.getlName());
		values.put(SQLName.EmailRow, passenger.getEmail());
		values.put(SQLName.PhoneNumberRow, passenger.getPhoneNumber());
		values.put(SQLName.PassIDRow, passenger.getId());
		values.put(SQLName.AdrressRow, passenger.getAdrress());
		values.put(SQLName.CreditCardRow, passenger.getCreditCard());

		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		values.put(SQLName.DobRow, formater.format(passenger.getDob()));
		values.put(SQLName.PasswordRow, passenger.getPassword());
		
		db.insertWithOnConflict(SQLName.PassengersTable, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		db.close();
		
	}

	@Override
	public void deletePassenger(Passenger passenger) throws Exception {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(SQLName.PassengersTable, SQLName.PassIDRow + "= ?"
				, new String[] {String.valueOf(passenger.getId())});	
		
	}

	@Override
	public void updatePassenger(Passenger passenger) throws Exception {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(SQLName.PassFirstNameRow, passenger.getfName());
		values.put(SQLName.PassLastNameRow, passenger.getlName());
		values.put(SQLName.EmailRow, passenger.getEmail());
		values.put(SQLName.PhoneNumberRow, passenger.getPhoneNumber());
		values.put(SQLName.PassIDRow, passenger.getId());
		values.put(SQLName.AdrressRow, passenger.getAdrress());
		values.put(SQLName.CreditCardRow, passenger.getCreditCard());

		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		values.put(SQLName.DobRow,formater.format(passenger.getDob()) );
		values.put(SQLName.PasswordRow, passenger.getPassword());
		
		db.update(SQLName.PassengersTable, values, SQLName.PassIDRow + "= ?"
				, new String[] {String.valueOf(passenger.getId())});
		db.close();
		
	}

	@Override
	public List<Passenger> getPassengerlist() throws Exception {
		List<Passenger> PassengerList = new ArrayList<Passenger>();
		String SelectQuery = "SELECT * FROM " + SQLName.PassengersTable;
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(SelectQuery, null) ;
		
		if(cursor.moveToFirst()){
			do{
				Passenger passenger = new Passenger();
				passenger.setfName(cursor.getString(0));
				passenger.setlName(cursor.getString(1));
				passenger.setEmail(cursor.getString(2));
				passenger.setPhoneNumber(cursor.getString(3));
				passenger.setId(cursor.getInt(4));
				passenger.setAdrress(cursor.getString(5));
				passenger.setCreditCard(cursor.getInt(6));
				SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				try {
					passenger.setDob(formater.parse(cursor.getString(7)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				passenger.setPassword(cursor.getString(8));
					
				PassengerList.add(passenger);
			}while(cursor.moveToNext());
		}
		return PassengerList;
	}

	//========================================== 
	// pilot 
	//==========================================
	
	@Override
	public void addPilot(Pilot pilot) throws Exception {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(SQLName.PilotFirstNameRow , pilot.getfNamel());
		values.put(SQLName.PilotLastNameRow , pilot.getLname());
		values.put(SQLName.LicenseRow , pilot.getLicense());
		values.put(SQLName.MaxSizePlainRow , pilot.getMaxSizePlain().toString());
		values.put(SQLName.MaxTimeFlightRow , pilot.getMaxTimeFlight());
				
		db.insertWithOnConflict(SQLName.PilotsTable, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		db.close();
		
	}

	@Override
	public void deletePilot(Pilot pilot) throws Exception {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(SQLName.PilotsTable, SQLName.PilotIDRow + "= ?"
				, new String[] {String.valueOf(pilot.getLicense())});	
		
	}

	@Override
	public void updatePilot(Pilot pilot) throws Exception {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(SQLName.PilotFirstNameRow , pilot.getfNamel());
		values.put(SQLName.PilotLastNameRow , pilot.getLname());
		values.put(SQLName.LicenseRow , pilot.getLicense());
		values.put(SQLName.MaxSizePlainRow , pilot.getMaxSizePlain().toString());
		values.put(SQLName.MaxTimeFlightRow , pilot.getMaxTimeFlight());
		
		db.update(SQLName.PilotsTable, values, SQLName.LicenseRow + "= ?"
				, new String[] {String.valueOf(pilot.getLicense())});
		db.close();
		
	}

	@Override
	public List<Pilot> getPilotlist() throws Exception {
		List<Pilot> PilotList = new ArrayList<Pilot>();
		String SelectQuery = "SELECT * FROM " + SQLName.PilotsTable;
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(SelectQuery, null) ;
		
		if(cursor.moveToFirst()){
			do{
				Pilot pilot = new Pilot(null, null, 0, null, 0);
				pilot.setfNamel(cursor.getString(0));
				pilot.setLname(cursor.getString(1));
				pilot.setLicense(cursor.getInt(2));
				String strMSP = cursor.getString(3);
				enums.PlainSize MSP = enums.PlainSize.valueOf(strMSP);
				pilot.setMaxSizePlain(MSP);
				pilot.setMaxTimeFlight(cursor.getInt(4));
				
				PilotList.add(pilot);
			}while(cursor.moveToNext());
		}
		return PilotList;
	}

	//========================================== 
	// plain 
	//==========================================
	
	@Override
	public void addPlain(Plain plain) throws Exception {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(SQLName.ModelRow ,plain.getModel());
		values.put(SQLName.WingIdRow ,plain.getWingId());
		values.put(SQLName.NumberPassengersRow ,plain.getNumberPassengers());
		values.put(SQLName.LengthRow ,plain.getLength());
		values.put(SQLName.HasVideoRow ,plain.getHasVideo());
		values.put(SQLName.SizeRow ,plain.getSize().toString());

				
		db.insertWithOnConflict(SQLName.PlainsTable, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		db.close();
		
	}

	@Override
	public void deletePlain(Plain plain) throws Exception {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(SQLName.PlainsTable, SQLName.WingIdRow + "= ?"
				, new String[] {String.valueOf(plain.getWingId())});	
		
	}

	@Override
	public void updatePlain(Plain plain) throws Exception {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(SQLName.ModelRow ,plain.getModel());
		values.put(SQLName.WingIdRow ,plain.getWingId());
		values.put(SQLName.NumberPassengersRow ,plain.getNumberPassengers());
		values.put(SQLName.LengthRow ,plain.getLength());
		values.put(SQLName.HasVideoRow ,plain.getHasVideo());
		values.put(SQLName.SizeRow ,plain.getSize().toString());
		
		db.update(SQLName.PlainsTable, values, SQLName.WingIdRow + "= ?"
				, new String[] {String.valueOf(plain.getWingId())});
		db.close();
	}

	@Override
	public List<Plain> getPlainlist() throws Exception {
		List<Plain> PlainList = new ArrayList<Plain>();
		String SelectQuery = "SELECT * FROM " + SQLName.PlainsTable;
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(SelectQuery, null) ;
		
		if(cursor.moveToFirst()){
			do{
				Plain plain = new Plain(null, 0, 0, 0, null, null);
				plain.setModel(cursor.getString(0));
				plain.setWingId(cursor.getInt(1));
				plain.setNumberPassengers(cursor.getInt(2));
				plain.setLength(cursor.getFloat(3));
				
				Boolean flag = false;
				String strHS = cursor.getString(4);
				if (strHS=="1")
					flag = true;
				if (strHS=="0")
					flag = false;
				plain.setHasVideo(flag);
				
				String strS = cursor.getString(5);
				enums.PlainSize PS = enums.PlainSize.valueOf(strS);
				plain.setSize(PS);	
					
				PlainList.add(plain);
			}while(cursor.moveToNext());
		}
		return PlainList;
	}

	//========================================== 
	// service 
	//==========================================
	
	@Override
	public void addServiceType(ServiceType st) throws Exception {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(SQLName.ServiceIDRow ,st.getServiceID());
		values.put(SQLName.ClassTypeRow ,st.getClassType().toString());
		values.put(SQLName.BaggageWhegitRow ,st.getBaggageWhegit());
		values.put(SQLName.FoodTypeRow ,st.getFoodType());
		values.put(SQLName.WindowRow ,st.getWindow());
						
		db.insertWithOnConflict(SQLName.ServicesTable, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		db.close();
		
	}

	@Override
	public void deleteServiceType(ServiceType st) throws Exception {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(SQLName.ServicesTable, SQLName.ServiceIDRow + "= ?"
				, new String[] {String.valueOf(st.getServiceID())});	
		
	}

	@Override
	public void updateServiceType(ServiceType st) throws Exception {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(SQLName.ServiceIDRow ,st.getServiceID());
		values.put(SQLName.ClassTypeRow ,st.getClassType().toString());
		values.put(SQLName.BaggageWhegitRow ,st.getBaggageWhegit());
		values.put(SQLName.FoodTypeRow ,st.getFoodType());
		values.put(SQLName.WindowRow ,st.getWindow());
		
		db.update(SQLName.ServicesTable, values, SQLName.ServiceIDRow + "= ?"
				, new String[] {String.valueOf(st.getServiceID())});
		db.close();
		
	}

	@Override
	public List<ServiceType> getServiceTypelist() throws Exception {
		List<ServiceType> ServiceList = new ArrayList<ServiceType>();
		String SelectQuery = "SELECT * FROM " + SQLName.ServicesTable;
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(SelectQuery, null) ;
		
		if(cursor.moveToFirst()){
			do{
				ServiceType st = new ServiceType(0, null, 0, null, null);
				st.setServiceID(cursor.getInt(0));
				String strCT = cursor.getString(1);
				enums.ClassType CT = enums.ClassType.valueOf(strCT);
				st.setClassType(CT);
				st.setBaggageWhegit(cursor.getInt(2));
				st.setFoodType(cursor.getString(3));
				
				Boolean flag = false;
				String strW = cursor.getString(4);
				if (strW=="1")
					flag = true;
				if (strW=="0")
					flag = false;
				st.setWindow(flag);
				
		
		
									
				ServiceList.add(st);
			}while(cursor.moveToNext());
		}
		return ServiceList;
	}

	//========================================== 
	// ticket 
	//==========================================
	
	@Override
	public void addTicket(Ticket ticket) throws Exception {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(SQLName.PassengerIDRow ,ticket.getPassengerID());
		values.put(SQLName.FlightIDTRow ,ticket.getFlightID());
		values.put(SQLName.SeatRow ,ticket.getSeat());
		values.put(SQLName.serviceTypeIDRow ,ticket.getServiceTypeID());
		values.put(SQLName.CostRow ,ticket.getCost());
		values.put(SQLName.TicketCodeRow ,ticket.getTicketCode()); 
		
		db.insertWithOnConflict(SQLName.TicketsTable, SQLName.TicketCodeRow, values, SQLiteDatabase.CONFLICT_IGNORE);
		db.close();
		
	}

	@Override
	public void deleteTicket(Ticket ticket) throws Exception {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(SQLName.TicketsTable, SQLName.TicketCodeRow + "= ?"
				, new String[] {String.valueOf(ticket.getTicketCode())});
		
	}

	@Override
	public void updateTicket(Ticket ticket) throws Exception {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(SQLName.PassengerIDRow ,ticket.getPassengerID());
		values.put(SQLName.FlightIDTRow ,ticket.getFlightID());
		values.put(SQLName.SeatRow ,ticket.getSeat());
		values.put(SQLName.serviceTypeIDRow ,ticket.getServiceTypeID());
		values.put(SQLName.CostRow ,ticket.getCost());
		values.put(SQLName.TicketCodeRow ,ticket.getTicketCode());

		db.update(SQLName.TicketsTable, values, SQLName.TicketCodeRow + "= ?"
				, new String[] {String.valueOf(ticket.getTicketCode())});
		db.close();
		
	}

	@Override
	public List<Ticket> getTicketlist() throws Exception {
		List<Ticket> TicketList = new ArrayList<Ticket>();
		String SelectQuery = "SELECT * FROM " + SQLName.TicketsTable;
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(SelectQuery, null) ;
		
		if(cursor.moveToFirst()){
			do{
				Ticket ticket = new Ticket(0, 0, null, 0, 0, 0);
				ticket.setPassengerID(cursor.getInt(0));
				ticket.setFlightID(cursor.getInt(1));
				ticket.setSeat(cursor.getString(2));
				ticket.setServiceTypeID(cursor.getInt(3));
				ticket.setCost(cursor.getFloat(4));
				ticket.setTicketCode(cursor.getInt(5));
									
				TicketList.add(ticket);
			}while(cursor.moveToNext());
		}
		return TicketList;
	}

	//========================================== 
	// another gets & sets 
	//==========================================
	
	@Override
	public List<Flight> getPassengerFlights(Passenger p) throws Exception {
		int id = p.getId();
		List<Flight> PFlightList = new ArrayList<Flight>();
		String SelectQuery = "SELECT * FROM " + SQLName.FlightsTable + " flights, "
		+ SQLName.TicketsTable + " tickets, " 
				+ "WHERE tickets." + SQLName.PassengerIDRow + " = '" +  id + "' AND tickets." 
				+ SQLName.FlightIDTRow + " = flights." + SQLName.FlightIDRow;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(SelectQuery, null) ;
		if(cursor.moveToFirst()){
			do{
				Flight flight = new Flight(null, null, 0, 0, 0, 0, null);
				flight.setDestination(cursor.getString(0));
				flight.setOrigin(cursor.getString(1));
				flight.setPilotID(cursor.getInt(2));
				flight.setPlainWingID(cursor.getInt(3));
				flight.setFlightTime(cursor.getInt(4));
				flight.setFlightID(cursor.getInt(5));
				SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				try {
					flight.setDepartTime(formater.parse(cursor.getString(6)));
				} catch (ParseException e) {
					e.printStackTrace();
				}	
				PFlightList.add(flight);
			}while(cursor.moveToNext());
		}
		return PFlightList;
	}

	@Override
	public Flight getFlightById(int f) throws Exception {
		
		
		String SelectQuery = "SELECT * FROM " + SQLName.FlightsTable 
				+ " WHERE " + SQLName.FlightIDRow + "= '" + f + "'";
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(SelectQuery, null) ;
		Flight flight = new Flight(null, null, 0, 0, 0, 0, null);
		cursor.moveToFirst();
		flight.setDestination(cursor.getString(0));
		flight.setOrigin(cursor.getString(1));
		flight.setPilotID(cursor.getInt(2));
		flight.setPlainWingID(cursor.getInt(3));
		flight.setFlightTime(cursor.getInt(4));
		flight.setFlightID(cursor.getInt(5));
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			flight.setDepartTime(formater.parse(cursor.getString(6)));
		} catch (ParseException e) {
			e.printStackTrace();
		} 	
		
		return flight;
	}

	@Override
	public Ticket getTicketById(int t) throws Exception {
		String SelectQuery = "SELECT * FROM " + SQLName.TicketsTable  
				+ " WHERE " + SQLName.TicketCodeRow + " = '"+ t + "'" ;
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(SelectQuery, null) ;
		
		Ticket ticket = new Ticket(0, 0, null, 0, 0, 0);
		cursor.moveToFirst();
		ticket.setPassengerID(cursor.getInt(0));
		ticket.setFlightID(cursor.getInt(1));
		ticket.setSeat(cursor.getString(2));
		ticket.setServiceTypeID(cursor.getInt(3));
		ticket.setCost(cursor.getFloat(4));
		ticket.setTicketCode(cursor.getInt(5));
									
		return ticket;
	}
	
	@Override
	public List<Ticket>  getPassengerTickets(Passenger p) throws Exception {
		List<Ticket> TicketList = new ArrayList<Ticket>();
		String SelectQuery = "SELECT * FROM " + SQLName.TicketsTable
				+" WHERE " + SQLName.PassengerIDRow + " = '" + p.getId() + "'";
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(SelectQuery, null) ;
		
		if(cursor.moveToFirst()){
			do{
				Ticket ticket = new Ticket(0, 0, null, 0, 0, 0);
				ticket.setPassengerID(cursor.getInt(0));
				ticket.setFlightID(cursor.getInt(1));
				ticket.setSeat(cursor.getString(2));
				ticket.setServiceTypeID(cursor.getInt(3));
				ticket.setCost(cursor.getFloat(4));
				ticket.setTicketCode(cursor.getInt(5));
									
				TicketList.add(ticket);
			}while(cursor.moveToNext());
		}
		return TicketList;
	}

	@Override
	public Passenger getPassengerById(int p) throws Exception {
		String SelectQuery = "SELECT * FROM " + SQLName.PassengersTable
				+ " WHERE " + SQLName.PassIDRow + " = '" + p + "'";
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(SelectQuery, null) ;
		Passenger passenger = new Passenger();
		cursor.moveToFirst();
		passenger.setfName(cursor.getString(0));
		passenger.setlName(cursor.getString(1));
		passenger.setEmail(cursor.getString(2));
		passenger.setPhoneNumber(cursor.getString(3));
		passenger.setId(cursor.getInt(4));
		passenger.setAdrress(cursor.getString(5));
		passenger.setCreditCard(cursor.getInt(6));
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			passenger.setDob(formater.parse(cursor.getString(7)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		passenger.setPassword(cursor.getString(8));
		
		return passenger;
	}

	@Override
	public Pilot getPilotById(int p) throws Exception {
		String SelectQuery = "SELECT * FROM " + SQLName.PilotsTable
				+ " WHERE " + SQLName.PilotIDRow + " = '" + p + "'";
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(SelectQuery, null) ;
		
		Pilot pilot = new Pilot(null, null, 0, null, 0);
		cursor.moveToFirst();
		pilot.setfNamel(cursor.getString(0));
		pilot.setLname(cursor.getString(1));
		pilot.setLicense(cursor.getInt(2));
		String strMSP = cursor.getString(3);
		enums.PlainSize MSP = enums.PlainSize.valueOf(strMSP);
		pilot.setMaxSizePlain(MSP);
		pilot.setMaxTimeFlight(cursor.getInt(4));

		return pilot;
	}

	@Override
	public Plain getPlainById(int p) throws Exception {
		String SelectQuery = "SELECT * FROM " + SQLName.PlainsTable
				+ " WHERE " + SQLName.WingIdRow + " + '" + p + "'" ;
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(SelectQuery, null) ;

		Plain plain = new Plain(null, 0, 0, 0, null, null);
		cursor.moveToFirst();
		plain.setModel(cursor.getString(0));
		plain.setWingId(cursor.getInt(1));
		plain.setNumberPassengers(cursor.getInt(2));
		plain.setLength(cursor.getFloat(3));
				
		Boolean flag = false;
		String strHS = cursor.getString(4);
		if (strHS=="1")
			flag = true;
		if (strHS=="0")
			flag = false;
		plain.setHasVideo(flag);
				
		String strS = cursor.getString(5);
		enums.PlainSize PS = enums.PlainSize.valueOf(strS);
		plain.setSize(PS);	
					
		return plain;
	}

	@Override
	public ServiceType getServiceTypeById(int st) throws Exception {
		String SelectQuery = "SELECT * FROM " + SQLName.ServicesTable
				+ " WHERE " + SQLName.ServiceIDRow + " = '" + st + "'";
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(SelectQuery, null) ;

		ServiceType service = new ServiceType(0, null, 0, null, null);
		cursor.moveToFirst();
		service.setServiceID(cursor.getInt(0));
		String strCT = cursor.getString(1);
		enums.ClassType CT = enums.ClassType.valueOf(strCT);
		service.setClassType(CT);
		service.setBaggageWhegit(cursor.getInt(2));
		service.setFoodType(cursor.getString(3));
		
		Boolean flag = false;
		String strW = cursor.getString(4);
		if (strW=="1")
			flag = true;
		if (strW=="0")
			flag = false;
		service.setWindow(flag);
				
		return service;
	}

	@Override
	public Passenger autenticatePassenger(String user, String pass) {
	
		String SelectQuery = "SELECT * FROM " + SQLName.PassengersTable
				+ " WHERE " + SQLName.EmailRow + " = '" + user + "'"
				+ " AND " + SQLName.PasswordRow + " = '" + pass + "'";
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(SelectQuery, null) ;
		Passenger passenger = new Passenger();
		cursor.moveToFirst();
		passenger.setfName(cursor.getString(0));
		passenger.setlName(cursor.getString(1));
		passenger.setEmail(cursor.getString(2));
		passenger.setPhoneNumber(cursor.getString(3));
		passenger.setId(cursor.getInt(4));
		passenger.setAdrress(cursor.getString(5));
		passenger.setCreditCard(cursor.getInt(6));
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			passenger.setDob(formater.parse(cursor.getString(7)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		passenger.setPassword(cursor.getString(8));
		
		return passenger;

	}
	@Override
	public void setLists() {
		try {
			
			Calendar cal = Calendar.getInstance();
			cal.set(1987, 05, 18, 12, 00);
			Date d = cal.getTime();
		this.addPassenger(new Passenger("yosef", "binjamini","yosyes@gmail.com", "770", 1, "somwhere", 123, d, "1"));
		this.addPassenger(new Passenger("eder", "yphrach","edi.otg@gmail.com", "613", 2, "somwhere", 123, d, "1"));
		this.addPassenger(new Passenger("oshri", "cohen","somthing@gmail.com", "1080",3, "somwhere", 123, d, "1"));
		
		
		cal = Calendar.getInstance();
		cal.set(2015, 03, 02, 12, 00);
		d = cal.getTime();
		this.addFlight(new Flight("tlv","jrm",  11, 100, 50, 100, d));
		cal.set(2015, 04, 02, 12, 00);
		d = cal.getTime();
		this.addFlight(new Flight("tlv","elt",  11, 100, 100, 101, d));
		cal.set(2015, 04, 02, 17, 00);
		d = cal.getTime();
		this.addFlight(new Flight("tlv","k8",  11, 101, 60,102, d));
		cal.set(2015, 01, 30, 12, 00);
		d = cal.getTime();
		this.addFlight(new Flight("tlv","elt",  11, 101, 50, 103, d));
		cal.set(2015, 01, 30, 06, 00);
		d = cal.getTime();
		this.addFlight(new Flight("tlv","nyc",  11, 101, 50, 104, d));
		cal.set(2015, 02, 02, 13, 00);
		d = cal.getTime();
		this.addFlight(new Flight("tlv","rio",  11, 102, 110, 105, d));
		cal.set(2015, 02, 02, 10, 00);
		d = cal.getTime();
		this.addFlight(new Flight("tlv","ita",  11, 102, 50, 106,d));
		cal.set(2015, 01, 02, 12, 00);
		d = cal.getTime();
		this.addFlight(new Flight("tlv","ams",  11, 102, 100, 107,d));
		cal.set(2015, 02, 02, 23, 00);
		d = cal.getTime();
		this.addFlight(new Flight("tlv","snf",  11, 103, 60, 108,d));
		cal.set(2015, 04, 02, 12, 00);
		d = cal.getTime();
		this.addFlight(new Flight("jrm","elt",  11,103, 50, 109,d));
		d.setTime(d.getTime()+30);
		this.addFlight(new Flight("jrm","tlv",  11, 103, 50, 110,d));
		d.setTime(d.getTime()+30);
		this.addFlight(new Flight("jrm","k8",  11, 104, 110, 111,d));
		d.setTime(d.getTime()+30);
		this.addFlight(new Flight("jrm","rio",  11, 104, 50, 121,d));
		d.setTime(d.getTime()+30);
		this.addFlight(new Flight("jrm","nyc",  11, 104, 100, 122,d));
		d.setTime(d.getTime()+30);
		this.addFlight(new Flight("jrm","k8",  11, 103, 60, 123,d));
		d.setTime(d.getTime()+30);
		this.addFlight(new Flight("jrm","elt",  11, 102, 50,124,d));
		d.setTime(d.getTime()+30);
		this.addFlight(new Flight("nyc","jrm",  11, 101, 50, 125,d));
		d.setTime(d.getTime()+30);
		this.addFlight(new Flight("nyc","tlv",  11, 101, 110, 126,d));
		d.setTime(d.getTime()+30);
		
	
		this.addTicket(new Ticket(1, 100, "12c", 1, 190,1));
		this.addTicket(new Ticket(1, 102, "1a", 2, 180,2));
		this.addTicket(new Ticket(2, 103, "7b", 3, 400,3));
		this.addTicket(new Ticket(2, 104, "3d", 4, 320,4));
		this.addTicket(new Ticket(3, 101, "2a", 5, 120,5));
		this.addTicket(new Ticket(3, 102, "12a", 6, 300,6));
		
		this.addPlain(new Plain("boing 737", 100, 60, 1100, true,enums.PlainSize.SMALL));
		this.addPlain(new Plain("boing 737-1", 101, 80, 1500, true,enums.PlainSize.MEDIUM));
		this.addPlain(new Plain("boing 747", 102, 100, 1700, true,enums.PlainSize.BIG));
		this.addPlain(new Plain("boing 777", 103, 120, 2000, true,enums.PlainSize.HUGE));
		this.addPlain(new Plain("boing 737", 104, 60, 1100, true,enums.PlainSize.SMALL));
		
		this.addServiceType(new ServiceType(1, enums.ClassType.BUSSINES,(float) 22.5,"fish" , true));
		this.addServiceType(new ServiceType(2, enums.ClassType.ECONOMY,(float) 20,"kosher" , false));
		this.addServiceType(new ServiceType(3, enums.ClassType.ECONOMY,(float) 25,"meat" , true));
		this.addServiceType(new ServiceType(4, enums.ClassType.BUSSINES,(float) 27.5,"vegi" , false));
		this.addServiceType(new ServiceType(5, enums.ClassType.FIRST,(float) 28.5,"meat" , true));
		this.addServiceType(new ServiceType(6, enums.ClassType.FIRST,(float) 33.5,"kosher" , true));
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	

	
}

