package control;




import model.backend.BackendFactory;

import com.example.java_02_3265_3808.R;

import entities.Flight;
import entities.Passenger;
import entities.ServiceType;
import entities.Ticket;
import entities.enums;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TabHost;
import android.widget.TextView;

public class PurchaseScreen extends ActionBarActivity  implements OnCheckedChangeListener, OnRatingBarChangeListener, OnClickListener {
	Passenger p;
	int f;
	TextView costTV;
	float cost;
	float baseCost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_purchase_screen);

		Intent intent = getIntent();
		p = (Passenger) intent.getSerializableExtra("passenger");
		f = (int) intent.getIntExtra("flight",0);
		setTitle("Flight: " + f);
		try {


			costTV = (TextView) findViewById(R.id.costTextView);	
			Flight flight = BackendFactory.getInstance(getBaseContext()).getFlightById(f);

			cost = baseCost = (float) (flight.getFlightTime()*1.5);
			costTV.setText(String.valueOf(cost)+ " $ ");

			TabHost th = (TabHost) findViewById(R.id.tabhost);

			// setup the tabs 
			th.setup();

			TabHost.TabSpec ts = th.newTabSpec("tag1"); 
			ts.setContent(R.id.tab1);
			ts.setIndicator("Food");
			th.addTab(ts);

			TabSpec ts1 = th.newTabSpec("tag2"); 
			ts1.setContent(R.id.tab2);
			ts1.setIndicator("Seat");
			th.addTab(ts1);

			TabSpec ts11 = th.newTabSpec("tag3"); 
			ts11.setContent(R.id.tab3);
			ts11.setIndicator("Class");
			th.addTab(ts11);

			//get the variables
			RadioGroup rgFood = (RadioGroup) findViewById(R.id.radioGroupFood);
			RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBarClass);
			Button buyBtn = (Button) findViewById(R.id.ConfirBuyBtn);

			//set the listeners
			buyBtn.setOnClickListener(this);
			ratingBar.setOnRatingBarChangeListener(this);
			rgFood.setOnCheckedChangeListener(this);

		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.purchase_screen, menu);
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

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {

		ImageView foodImage = (ImageView) findViewById(R.id.imageViewFood);

		switch (checkedId) {
		case R.id.radioButtonMeat:
			foodImage.setImageResource(R.drawable.meat_food);
			cost =baseCost +25;
			costTV.setText(String.valueOf(cost)+ " $ ");

			break;
		case R.id.radioButtonFish:
			foodImage.setImageResource(R.drawable.fish_food);
			cost = baseCost;
			costTV.setText(String.valueOf(cost)+ " $ ");
			break;
		case R.id.radioButtonVegi:
			cost = baseCost;
			foodImage.setImageResource(R.drawable.vegi_food);
			costTV.setText(String.valueOf(cost)+ " $ ");
			break;
		case R.id.radioButtonKosher:
			foodImage.setImageResource(R.drawable.kosher_food);
			cost =baseCost +25;
			costTV.setText(String.valueOf(cost+25)+ " $ ");
			break;

		default:
			foodImage.setImageResource(R.drawable.abc_ab_bottom_transparent_dark_holo);
			break;
		}

	}

	@Override
	public void onRatingChanged(RatingBar ratingBar, float rating,
			boolean fromUser) {

		TextView classTV = (TextView) findViewById(R.id.classTVpourchese);

		switch ((int)rating) {
		case 1:
			classTV.setText("Economy Class");
			costTV.setText(String.valueOf(cost)+ " $ ");
			break;
		case 2:
			classTV.setText("Business Class");

			costTV.setText(String.valueOf(cost*1.5)+ " $ ");
			break;
		case 3:
			classTV.setText("First Class");
			costTV.setText(String.valueOf(cost*2)+ " $ ");
			break;

		default:
			classTV.setText("chouse the class");
			break;
		}

	}

	@Override
	public void onClick(View v) {

		try {


			AlertDialog.Builder dialog = new AlertDialog.Builder(this);

			RadioGroup rgFood = (RadioGroup) findViewById(R.id.radioGroupFood);
			RadioGroup rgWin = (RadioGroup) findViewById(R.id.radioGroupChair);
			TextView classTv = (TextView) findViewById(R.id.classTVpourchese);

			LinearLayout ll = new LinearLayout(PurchaseScreen.this);
			ll.setOrientation(LinearLayout.VERTICAL);
			TextView tv= new TextView(this);
			TextView tv2= new TextView(this);
			TextView tv3= new TextView(this);
			TextView tv4= new TextView(this);

			tv.setText("Meal: "+((RadioButton)findViewById(rgFood.getCheckedRadioButtonId())).getText());
			tv2.setText("Class: "+classTv.getText());
			tv3.setText("Seat: "+((RadioButton)findViewById(rgWin.getCheckedRadioButtonId())).getText());
			tv4.setText("Price: "+ costTV.getText());
			tv.setGravity(Gravity.CENTER);
			tv2.setGravity(Gravity.CENTER);
			tv3.setGravity(Gravity.CENTER);
			tv4.setGravity(Gravity.CENTER);
			//put all the details in the linearlayout
			ll.addView(tv);
			ll.addView(tv2);
			ll.addView(tv3);
			ll.addView(tv4); 

			//FrameLayout fl = (FrameLayout) findViewById(android.R.id.custom);
			//fl.addView(ll);
			dialog.setView(ll)
			.setTitle("Summery")
			.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {

					//make the work in background
					new PurchaseScreen.Loader().execute();

					cost = (float) (cost * ((((RatingBar)findViewById(R.id.ratingBarClass)).getRating()==2)?1.5:
						(((RatingBar)findViewById(R.id.ratingBarClass)).getRating()==3)?2:0));
					//if not a user go to register
					if (p==null) {
						Intent in = new Intent(((Dialog) dialog).getContext(), Registretion.class);
						//the id of the service that we are bulding in the background
//						in.putExtra("service",f);
						in.putExtra("flight", f);
						in.putExtra("cost", cost);
						PurchaseScreen.this.startActivity(in);


						return;

					}
					else {

						//else go to my flights
						// if ( true )-- check here if the credit card can pay ---- not implemnted
						try {
							//add new service and new ticket
							enums.ClassType cls;
							cls = (((RatingBar)findViewById(R.id.ratingBarClass)).getRating()==3)?enums.ClassType.FIRST
									:(((RatingBar)findViewById(R.id.ratingBarClass)).getRating()==2)?enums.ClassType.BUSSINES
											:enums.ClassType.FIRST;
							BackendFactory.getInstance(getBaseContext()).addServiceType(new ServiceType(
									0, 
									cls,
									// if checked extra wehigt add 5 kilo
									((RatingBar)findViewById(R.id.ratingBarClass)).getRating()*10+((((CheckBox)findViewById(R.id.weightCHB)).isChecked())?5:0),
									(String) ((RadioButton)findViewById(((RadioGroup)findViewById(R.id.radioGroupFood)).getCheckedRadioButtonId())).getText(),
									((RadioButton)findViewById(R.id.radioButtonWindow)).isChecked()));
							
							BackendFactory.getInstance(getBaseContext()).addTicket(new Ticket(p.getId(), f, "", p.getId()+f, cost, 0));
						} catch (Exception e) {
							
							e.printStackTrace();
						}
						Intent in = new Intent(((Dialog) dialog).getContext(), My_flights.class);
						//in.putExtra("passenger", p);
						in.putExtra("passenger", p.getId());
						startActivity(in);

						
					}

				}
			});

			dialog.show();
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
		}

	}

	class Loader extends AsyncTask<Object, Object, Object> {

		public ProgressDialog dialog;
		@Override
		protected Object doInBackground(Object... params) {

			try {
				enums.ClassType cls;
				cls = (((RatingBar)findViewById(R.id.ratingBarClass)).getRating()==3)?enums.ClassType.FIRST
						:(((RatingBar)findViewById(R.id.ratingBarClass)).getRating()==2)?enums.ClassType.BUSSINES
								:enums.ClassType.FIRST;

				ServiceType st = new ServiceType(
						(p==null)?0:p.getId()+f,
								cls,
								// if checked extra wehigt add 5 kilo
								((RatingBar)findViewById(R.id.ratingBarClass)).getRating()*10+((((CheckBox)findViewById(R.id.weightCHB)).isChecked())?5:0), 
								(String) ((RadioButton)findViewById(((RadioGroup)findViewById(R.id.radioGroupFood)).getCheckedRadioButtonId())).getText(),
								((RadioButton)findViewById(R.id.radioButtonWindow)).isChecked());


				BackendFactory.getInstance(getBaseContext()).addServiceType(st);


				if(p!=null){
					//need to add with special id
					Ticket t =new Ticket(p.getId(), f, "", st.getServiceID(), cost,0);


					BackendFactory.getInstance(getBaseContext()).addTicket(t);

				}} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();


				}



			return null;
		}

		@Override
		protected void onPreExecute() {
			//Loading progrresBar
			super.onPreExecute();
			dialog= new ProgressDialog(PurchaseScreen.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.setMessage("Loading...");
			dialog.setCancelable(true);
			dialog.show();
		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
			PurchaseScreen.this.finish();
		}

		@Override
		protected void onProgressUpdate(Object... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			dialog.setProgress( ( (Integer)values[0]).intValue() );
		}

	}
}
