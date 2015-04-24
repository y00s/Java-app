package control;



import java.util.ArrayList;
import java.util.List;

import com.example.java_02_3265_3808.R;

import model.backend.BackendFactory;
import entities.Flight;
import entities.Passenger;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class Flights_info extends ActionBarActivity implements OnClickListener {

	ProgressDialog  dialog;
	Passenger p;
	FragmentTransaction fTransaction;
	List<Flight> flightsList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flights_info);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		
		//make the data import on the background only needed in lists base
		//Loading progrresBar
		dialog = new ProgressDialog(this);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.setMessage("Loading...");
		dialog.setCancelable(true);
		
		
		new Loader().execute();
		
					TextView user = (TextView) findViewById(R.id.hello_user);
					Intent intent = getIntent();
					if(intent.getBooleanExtra("guest",true))
					{
						user.setText("Hello there!");
						p=null;
					}
						
					else{
						p = (Passenger) getIntent().getSerializableExtra("passenger");
						user.setText("Hello, " + p.getfName() + " " + p.getlName()) ;
					}
		
				
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.flights_info, menu);
		//if it is a guest there is no 'my flights' to show
		menu.findItem(R.id.my_flights_icon).setVisible(!(p==null));
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.my_flights_icon) {
			//this can only heppend when is logd in - else there is no button 'my flights'
			Intent myFlights = new Intent(Flights_info.this, My_flights.class);
			//myFlights.putExtra("passanger", p);
			myFlights.putExtra("passanger", p.getId());
			Flights_info.this.startActivity(myFlights);
		}
		if (id == R.id.search_flights) {
			
			serach_flights();
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	
	private void serach_flights() {
		
		//show a dialog with parameters to search flights
		final AlertDialog.Builder dialog = new AlertDialog.Builder(Flights_info.this);
		View l = View.inflate(getBaseContext(), R.layout.search_dialog,null);
		final EditText from = (EditText) l.findViewById(R.id.fromEt);
		final EditText to = (EditText) l.findViewById(R.id.toEt);
		final EditText length = (EditText) l.findViewById(R.id.lengthEt);
		final RadioGroup lg = (RadioGroup) l.findViewById(R.id.lessGreatRadioGroup);
		
		final List<Flight> result_flights = new ArrayList<Flight>();
		
		dialog.setNeutralButton("Search!",	new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
		
				try {
					//not most optimal search but will sufice
					
					for (Flight flight : flightsList) { 
						
						
						if(((((CharSequence)from.getText()).length()==0)  ||  (flight.getOrigin().contains(from.getText())))								
								&&
						((((CharSequence)to.getText()).length()==0) || ((flight.getDestination().contains(to.getText()))))
								&&
						((length.getText().length()==0) || 
						((lg.getCheckedRadioButtonId()==R.id.lessRb)?
								(flight.getFlightTime()<Integer.parseInt(myHelpFunc.myCasts.charStoString(length.getText()))):
									(flight.getFlightTime()>Integer.parseInt(myHelpFunc.myCasts.charStoString(length.getText()))))						
									
						))
						{
							result_flights.add(flight);
						}
						
						//show these flights by creating new fragment with the new list
						FlightsList updated_list =new FlightsList(result_flights,p);
											
						
						fTransaction = getSupportFragmentManager().beginTransaction();
						fTransaction.replace(R.id.flights_info_layout, updated_list);
						fTransaction.addToBackStack(null);
						
						fTransaction.commit();
						((DialogInterface) dialog).cancel();
						
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
	
			}
		});
		
		
		dialog.setView(l);
		dialog.show();
		
	}

// ======================= importing data
	class Loader extends AsyncTask<Object, Object, Object> {

		@Override
		protected Object doInBackground(Object... params) {
			try {
				//BackendFactory.getInstance(getBaseContext()).setLists();
				flightsList = BackendFactory.getInstance(getApplicationContext()).getFlightlist();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			try {
				
				
				fTransaction = getSupportFragmentManager().beginTransaction();
				fTransaction.add(R.id.flights_info_layout, new FlightsList(flightsList,p));
				fTransaction.commit();
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			dialog.dismiss();
		}

		@Override
		protected void onProgressUpdate(Object... values) {
			
			super.onProgressUpdate(values);
			dialog.setProgress( ( (Integer)values[0]).intValue() );
		}

	}


	@Override
	public void onClick(View v) {
		
			Intent fd = new Intent(this, FlightDetails.class);
			fd.putExtra("flight",Integer.parseInt((String) ((TextView) v.findViewById(R.id.flightN)).getText()));
			fd.putExtra("passenger",p);
			Flights_info.this.startActivity(fd);
		
	}
}
