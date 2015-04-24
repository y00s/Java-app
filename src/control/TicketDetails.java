package control;

import com.example.java_02_3265_3808.R;
import entities.Flight;
import entities.Plain;
import entities.ServiceType;
import entities.Ticket;
import entities.enums;
import model.backend.BackendFactory;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TicketDetails extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ticket_details);
		
	Intent in = getIntent();
	int t = in.getIntExtra("ticket",0);
	
	TextView from = (TextView) findViewById(R.id.fromTicketTV);
	TextView to = (TextView) findViewById(R.id.toTicketTV);
	TextView depart = (TextView) findViewById(R.id.departTocketTV);
	Button airplainBtn = (Button) findViewById(R.id.airplaneBTNticket);
	Button serviceBtn = (Button) findViewById(R.id.serviceBtnTicket);
	
	try {
		Ticket ticket = BackendFactory.getInstance(getBaseContext()).getTicketById(t);
		Flight flight = BackendFactory.getInstance(getBaseContext()).getFlightById(ticket.getFlightID());
		final Plain plain = BackendFactory.getInstance(getBaseContext()).getPlainById(flight.getPlainWingID());
		final ServiceType st = BackendFactory.getInstance(getBaseContext()).getServiceTypeById(ticket.getServiceTypeID());
		
		
		setTitle("Ticket: "+ticket.getTicketCode());
		from.setText(flight.getOrigin());
		to.setText(flight.getDestination());
		depart.setText(DateFormat.format("dd/mm/yy hh:mm", flight.getDepartTime()));
		
		
		airplainBtn.setOnClickListener(new View.OnClickListener() {
		
			
			
			
			@Override
			public void onClick(View v) {
				ImageView iv = new ImageView(getBaseContext());
				AlertDialog.Builder dialog = new AlertDialog.Builder(TicketDetails.this);
				LinearLayout ll = new LinearLayout(TicketDetails.this);
				
				iv.setImageResource((plain.getSize()==enums.PlainSize.HUGE)?R.drawable.huge_plain:
					(plain.getSize()==enums.PlainSize.BIG)?R.drawable.large_plain:
						(plain.getSize()==enums.PlainSize.MEDIUM)?R.drawable.med_plain:R.drawable.small_plain);
			
				ll.addView(iv);
				dialog.setView(ll);
				dialog.show();
				
			}
		});
		
		serviceBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(TicketDetails.this);
				LinearLayout ll = new LinearLayout(TicketDetails.this);
				
				ll.setOrientation(LinearLayout.VERTICAL);
				TextView tv= new TextView(TicketDetails.this);
				TextView tv2= new TextView(TicketDetails.this);
				TextView tv3= new TextView(TicketDetails.this);
				TextView tv4= new TextView(TicketDetails.this);
				
				tv.setText("Meal: "+ st.getFoodType());
				tv2.setText("Class: "+st.getClassType().name());
				tv3.setText("Seat: "+(st.getWindow()) != null?"WINDOW":"HALY");
				tv4.setText("Weight: "+ st.getBaggageWhegit());
				tv.setGravity(Gravity.CENTER);
				tv2.setGravity(Gravity.CENTER);
				tv3.setGravity(Gravity.CENTER);
				tv4.setGravity(Gravity.CENTER);
				
				//put all the details in the linearlayout
				ll.addView(tv);
				ll.addView(tv2);
				ll.addView(tv3);
				ll.addView(tv4); 
				
				
				dialog.setView(ll);
				dialog.show();
				
			}
		});
		
		
		
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.ticket_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
