package control;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import model.backend.BackendFactory;

import com.example.java_02_3265_3808.R;

import entities.*;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class My_flights extends ActionBarActivity implements OnClickListener {
	Passenger p;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//BackendFactory.getInstance(getBaseContext()).setLists();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_flights);
		
		//start variables
		LinearLayout ll = (LinearLayout) findViewById(R.id.my_flights_layout);
		Flight firstNextFlight = null;
		Intent intent = getIntent();
		
		
		final TextView timer = (TextView) findViewById(R.id.timerTxt);
		TextView nextTV = (TextView) findViewById(R.id.nextFlightTV);
		
		//Object obj = intent.getSerializableExtra("passenger");
		//p = (Passenger)obj;
		int pid = intent.getIntExtra("passanger", 0);
				try {
					p = BackendFactory.getInstance(getBaseContext()).getPassengerById(pid);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
	try {
		List<Ticket> tic = BackendFactory.getInstance(getApplicationContext()).getPassengerTickets(p);
		
		
		
				//add the flights to the layout 
			for (Ticket ticket : tic) {
				Flight flight = BackendFactory.getInstance(getBaseContext()).getFlightById(ticket.getFlightID());
				
			
				View fv = View.inflate(this, R.layout.ticketview, null);

				TextView from = (TextView)fv.findViewById(R.id.from);
				TextView to = (TextView)fv.findViewById(R.id.destinetion);
				TextView flightN = (TextView)fv.findViewById(R.id.flightN);
				TextView ticketId = (TextView)fv.findViewById(R.id.ticketId);

				fv.setOnClickListener(this);
				from.setText(flight.getOrigin());
				to.setText(flight.getDestination());
				flightN.setText(String.valueOf(flight.getFlightID()));
				ticketId.setText(String.valueOf(ticket.getTicketCode()));
				ll.addView(fv);
				
				if (firstNextFlight==null) {
					firstNextFlight = flight;
				}
				else if (firstNextFlight.getDepartTime().before(flight.getDepartTime())) {
					firstNextFlight = flight;
				}
			}	
		
//		set the timer
			
		if (firstNextFlight==null) {
			
			nextTV.setText("Sorry,");	
			timer.setText("No Flights!");
			
					
		} 
		
		else {
			nextTV.setText("Next Flight in:");
			
		new CountDownTimer( firstNextFlight.getDepartTime().getTime()- Calendar.getInstance().get(Calendar.SECOND), 1000) {

		     public void onTick(long millisUntilFinished) {
		    	 
		       	 timer.setText(String.format("%02d:%02d:%02d",
		    			 TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
		    			 TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -  
		    			 TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)), 
		    			 TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
		    			 TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))); 
		     }

		     public void onFinish() {
		         timer.setText("NOW!, run...");
		     }
		  }.start();
		  
		}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.my_flights, menu);
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
	public void onBackPressed() {
		Intent in = new Intent(getBaseContext(), Flights_info.class);
		in.putExtra("passenger", p);
		in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		My_flights.this.startActivity(in);
		My_flights.this.finish();
		
	};

	@Override
	public void onClick(View v) {
		Intent fd = new Intent(getBaseContext(), TicketDetails.class);
		fd.putExtra("ticket",Integer.parseInt((String) ((TextView) v.findViewById(R.id.ticketId)).getText()));
		My_flights.this.startActivity(fd);
		
	}
}
