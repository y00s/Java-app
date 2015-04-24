package control;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import model.backend.BackendFactory;

import com.example.java_02_3265_3808.R;

import entities.Flight;
import entities.Passenger;
import entities.Plain;
import entities.enums;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FlightDetails extends Activity implements OnClickListener {

	Passenger p;
	Flight flight;
	int f;
	ProgressDialog dialog;
	boolean owned;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flight_details);
		
		Intent intent = getIntent();
		p = (Passenger) intent.getSerializableExtra("passenger");
		f = intent.getIntExtra("flight",0);
		
		owned = false;

		
//			==========    get the data from database =============
//		//Loading progrresBar
		dialog = new ProgressDialog(this);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setMessage("Loading...");
		dialog.setCancelable(false);
		new Loader().execute();
		
		
	
		
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.flight_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.nextFlightTv) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		Intent intent2 = new Intent(getApplicationContext(), PurchaseScreen.class);
		intent2.putExtra("passenger", p);
		intent2.putExtra("flight", f);
		
		FlightDetails.this.startActivity(intent2);
		
	}
	
	class Loader extends AsyncTask<Object, Object, Object> {

		@Override
		protected Object doInBackground(Object... params) {
			try {
				flight = BackendFactory.getInstance(getApplicationContext()).getFlightById(f);
				if (p != null) {
					 try {
							List<Flight> pflights = BackendFactory.getInstance(getApplicationContext()).getPassengerFlights(p);
							for (Flight flight : pflights) {
								if (flight.getFlightID()==f) {
									owned =true;
								}
										
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				Log.d("NET_CONECTION", e.getLocalizedMessage());
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			

			super.onPreExecute();
			dialog.show();
		}

		@Override
		protected void onPostExecute(Object result) {
			
			super.onPostExecute(result);
			updateDetails();
			dialog.dismiss();
		}

		@Override
		protected void onProgressUpdate(Object... values) {
			
			super.onProgressUpdate(values);
			dialog.setProgress( ( (Integer)values[0]).intValue() );
		}

	}

	public void updateDetails() {
		//set variables
				TextView flightIDtv = (TextView) findViewById(R.id.flightID_details);
				TextView flightFrom = (TextView) findViewById(R.id.fromTVdetails);
				TextView flightTo = (TextView) findViewById(R.id.toTVdetails);
				TextView flightLength = (TextView) findViewById(R.id.lengthTVdetails);
				TextView flightDeparture = (TextView) findViewById(R.id.departureTVdetails);
				TextView plainSizeTxt = (TextView) findViewById(R.id.plainSizeTxtDetails);
				TextView costTxt = (TextView) findViewById(R.id.costTVdetails);
				ImageView plainPic = (ImageView) findViewById(R.id.plainPicDetails);
				Button buyBtn = (Button) findViewById(R.id.buyTicketButton);
				
		try {
			//set variables by the flight
			flightIDtv.setText( String.valueOf(f));
			flightFrom.setText(flight.getOrigin());
			flightTo.setText(flight.getDestination());
			flightLength.setText(Integer.toString(flight.getFlightTime())+" Minuts");

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			flightDeparture.setText(dateFormat.format( flight.getDepartTime()));
			costTxt.setText(String.valueOf((float)flight.getFlightTime()*1.5)+ " $ ");
			
			//costTxt.setText(flight.getCost());
			
			final TextView nextTV = (TextView) findViewById(R.id.nextFlightTvDetails);
			if (owned) {
				buyBtn.setVisibility(View.INVISIBLE);
				
				//add timer -> time to flight
				
				
			new CountDownTimer( flight.getDepartTime().getTime()- Calendar.getInstance().get(Calendar.SECOND), 1000) {

			     public void onTick(long millisUntilFinished) {
			    	 
			    	 nextTV.setText("Next Flight in: \n"+ String.format("%02d days and\n %02d:%02d:%02d", 
			    			 TimeUnit.MILLISECONDS.toDays(millisUntilFinished),
			    			 TimeUnit.MILLISECONDS.toHours(millisUntilFinished)-TimeUnit.MILLISECONDS.toDays(millisUntilFinished),
			    			 TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -  
			    			 TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))-TimeUnit.MILLISECONDS.toDays(millisUntilFinished), 
			    			 TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MILLISECONDS.toDays(millisUntilFinished) -
			    			 TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))); 
			     }

			     public void onFinish() {
			         nextTV.setText("the Flight is NOW!, run...");
			     }
			  }.start();
			  
			
				
			}
			else {
				nextTV.setVisibility(View.INVISIBLE);
				buyBtn.setOnClickListener(this);
				
			}
			
			
			//set the plain details
			Plain plain = BackendFactory.getInstance(getApplicationContext()).getPlainById(flight.getPlainWingID());
			if (plain.getSize()==enums.PlainSize.HUGE) {
				plainPic.setImageResource(R.drawable.huge_plain);
				plainSizeTxt.setText("Boing 777-300");
			}	 
			else if (plain.getSize()==enums.PlainSize.BIG) {
				plainPic.setImageResource(R.drawable.large_plain);
				plainSizeTxt.setText("Boing 787-8");
				
			}
			else if (plain.getSize()==enums.PlainSize.MEDIUM) {
				plainPic.setImageResource(R.drawable.med_plain);
				plainSizeTxt.setText("Boing 767-300");
				
			}
			else{
				plainPic.setImageResource(R.drawable.small_plain);
				plainSizeTxt.setText("Boing 737-800");
				
			}
		
		
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		
	}
}
