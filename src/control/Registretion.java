package control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.backend.BackendFactory;

import com.example.java_02_3265_3808.R;

import entities.*;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Registretion extends Activity {
	

	public int progrresPercent;
	boolean fromLogin;
	int flight;
	float cost;
	
 
	
 
	public  Registretion() {
		progrresPercent=0;

	}

	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registretion);
		
		

		Intent in = getIntent();
		fromLogin =in.getBooleanExtra("fromLogin", false);
		flight = in.getIntExtra("flight", 0);
		cost= in.getFloatExtra("cost", 0.0f);
		
	

		

		final EditText editText1= (EditText) findViewById(R.id.registerFirstName);
		final EditText editText2 = (EditText) findViewById(R.id.regiserLastName);
		final EditText editText3= (EditText) findViewById(R.id.registerEmail);
		final EditText editText4 = (EditText) findViewById(R.id.registerPhone);
		final EditText editText5 = (EditText) findViewById(R.id.registerId);
		final EditText editText6 = (EditText) findViewById(R.id.registerAdrress);
		final EditText editText7 = (EditText) findViewById(R.id.registerCreditCard);
		final EditText editText8 = (EditText) findViewById(R.id.RegisterDOB);
		final EditText editText9 = (EditText) findViewById(R.id.registerPassword);
		final EditText editText10 = (EditText) findViewById(R.id.RegisterReEnterPassword);

		final ProgressBar progressBar = (ProgressBar) findViewById(R.id.registerProgrresBar);
		final ArrayList<Boolean> flags = new ArrayList<Boolean>();
		
	
		for (int i = 0; i <11; i++) {
			flags.add(false);
		}
		
		editText8.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(final View v, boolean hasFocus) {
				try {
					
				
				if (v.getId()==R.id.RegisterDOB && hasFocus) {
					DatePickerDialog dialogdp = new DatePickerDialog(Registretion.this, new DatePickerDialog.OnDateSetListener() {
						
						@Override
						public void onDateSet(DatePicker view, int year, int monthOfYear,
								int dayOfMonth) {
							((EditText)v).setText(dayOfMonth +"/"+ (monthOfYear+1) +"/"+year);
									
						}
					}, 1980, 1, 1);
					dialogdp.show();
					dialogdp.setCancelable(false);

					if (hasFocus) {
						v.clearFocus();
					}
					
				}
				} catch (Exception e) {
					//Toast.makeText(Registretion.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
					
				}
				
			}
				
		});
		
		progrresBarAdvancment(editText1,progressBar,flags,1);
		progrresBarAdvancment(editText2,progressBar,flags,2);
		progrresBarAdvancment(editText3,progressBar,flags,3);
		progrresBarAdvancment(editText4,progressBar,flags,4);
		progrresBarAdvancment(editText5,progressBar,flags,5);
		progrresBarAdvancment(editText6,progressBar,flags,6);
		progrresBarAdvancment(editText7,progressBar,flags,7);
		progrresBarAdvancment(editText8,progressBar,flags,8);
		progrresBarAdvancment(editText9,progressBar,flags,9);
		progrresBarAdvancment(editText10,progressBar,flags,10);
		
		
		
		
		Button registerButton= (Button) findViewById(R.id.registerButton);
		
		Button cleanButton = (Button) findViewById(R.id.CleanButton);


		cleanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editText1.setText("");
				editText2.setText("");
				editText3.setText("");
				editText4.setText("");
				editText5.setText("");
				editText6.setText("");
				editText7.setText("");
				editText8.setText("");
				editText9.setText("");
				editText10.setText("");
			}
		});
	

	
		registerButton.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				if (editText9.getText().toString().compareTo(editText10.getText().toString())!=0) {
					
					editText10.setError("The passwords do not mach");
					editText9.setError("The passwords do not mach");
					
					
				}

	
				else if  (!editText1.getText().toString().matches("") &&
		!editText2.getText().toString().matches("") &&
		!editText3.getText().toString().matches("") &&
		!editText5.getText().toString().matches("") &&
		!editText9.getText().toString().matches("") &&
		!editText10.getText().toString().matches("") &&
		!editText7.getText().toString().matches("") ) {
		
		//implement get data of pessenger
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			if (editText8.getText().length()==0) {
				editText8.setText("01/01/1900");

			}
			
			final Passenger p = new Passenger(editText1.getText().toString(),
					editText2.getText().toString(),
					editText3.getText().toString(),
					editText4.getText().toString(),
					Integer.parseInt(editText5.getText().toString()),
					editText6.getText().toString(),
					Integer.parseInt(editText7.getText().toString()),
					formatter.parse(editText8.getText().toString()),editText9.getText().toString());
			
			BackendFactory.getInstance(getApplicationContext()).addPassenger(p);
			
			if (fromLogin) {
				
			
			Intent  flightsIntent = new Intent(Registretion.this, Flights_info.class);
			flightsIntent.putExtra("passenger", p);
			Registretion.this.startActivity(flightsIntent);
			Registretion.this.finish();
			
			}
			else {
				
				
				//update the temporary service ID and add the new ticket async..
				new AsyncTask<Void, Void, Void>() {

				

					@Override
					protected Void doInBackground(Void... params) {
						
						
						ServiceType st;
						try {
						st = BackendFactory.getInstance(getBaseContext()).getServiceTypeById(flight);
					
						BackendFactory.getInstance(getBaseContext()).deleteServiceType(st);
						//String f = service.substring(3, service.length());
						//service.replace("xxx",String.valueOf(p.getId()));
						int service = p.getId()+flight;
						st.setServiceID(service);
						BackendFactory.getInstance(getBaseContext()).addServiceType(st);
						
						//add the new ticket
						//make special ticket number	
						Ticket t =new Ticket(p.getId(), flight,"" , service	, cost,0);
				
						BackendFactory.getInstance(getApplicationContext()).addTicket(t);
						
						
						
						} catch (Exception e) {
							
							e.printStackTrace();
							Toast.makeText(Registretion.this, e.getMessage(), Toast.LENGTH_SHORT).show();
						}
						return null;
					}
				};
				
				
				//start my flights activity
				Intent  flightsIntent = new Intent(Registretion.this, My_flights.class);
				flightsIntent.putExtra("passenger", p);
				//flightsIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				
				Registretion.this.startActivity(flightsIntent);
				Registretion.this.finish();
				
			}
			
		} catch (Exception e) {
			Toast.makeText(Registretion.this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	
		
	}
	else {
		try {
		if (editText1.getText().toString().matches("")) {
			editText1.setError("This filed is obligatory");
		}
		if (editText2.getText().toString().matches("")) {
			editText2.setError("This filed is obligatory");
		}
		if (editText3.getText().toString().matches("")) {
			editText3.setError("This filed is obligatory");
		}
		if (editText5.getText().toString().matches("")) {
			editText5.setError("This filed is obligatory");
		}
		if (editText7.getText().toString().matches("")) {
			editText7.setError("This filed is obligatory");
		}
		}
	
	
			 catch (Exception e) {
				Toast.makeText(Registretion.this, e.getMessage(), Toast.LENGTH_LONG).show();
			}

			}
			}
		});
		


	
	}
	private void progrresBarAdvancment( EditText et, final ProgressBar pb, final ArrayList<Boolean> f,final int i) {
		

		
		et.addTextChangedListener(new TextWatcher() {
		
			@Override
			public void afterTextChanged(Editable s) {
			//	f.set(i, false);
			//	if (!(editText.getText().toString()=="") && !flagName) {
				if (s.length()==1 && !f.get(i)) {
					
			
					progrresPercent+=10;
				//	Animation an = new Animation
					//pb.setAnimation(animation);
					pb.setProgress(progrresPercent);
					f.set(i, true) ;
					
				}
				else if(s.toString().matches("")) {
				//	f = false;
					progrresPercent-=10;
					pb.setProgress(progrresPercent);
					f.set(i, false);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {}			

			@Override
			public void onTextChanged(CharSequence s, int start, int before,	int count) {}
		});
	
		}
	};
	
	


