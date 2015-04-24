package control;

import java.util.List;

import com.example.java_02_3265_3808.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import entities.Flight;
import entities.Passenger;



public class FlightsList extends Fragment  implements OnClickListener {

	List<Flight> myFlightsList;
	ArrayAdapter<Flight> flightsAdapter;
	Passenger p;

	public FlightsList(List<Flight> sourceFlightList,Passenger p)
	{
		super();
		myFlightsList = sourceFlightList;
		this.p=p;
		
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		
//		List<Flight> myFlightsListTemp = null;
//		try {
//			myFlightsListTemp = BackendFactory.getInstance(getActivity()).getFlightlist();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//		
		//myFlightsList = sourceFlightList;
		ListView listView = new ListView(getActivity());

		flightsAdapter = new ArrayAdapter<Flight>(getActivity(), R.layout.flightview, myFlightsList) 
				{
			@Override
			public View getView(int position, View convertView, android.view.ViewGroup parent) {

				if (convertView ==null) {
					convertView = View.inflate(getActivity(), R.layout.flightview, null);

					TextView from = (TextView)convertView.findViewById(R.id.from);
					TextView to = (TextView)convertView.findViewById(R.id.destinetion);
					TextView flightN = (TextView)convertView.findViewById(R.id.flightN);

					convertView.setOnClickListener( FlightsList.this);
					from.setText(myFlightsList.get(position).getOrigin());
					to.setText(myFlightsList.get(position).getDestination());
					flightN.setText(String.valueOf(myFlightsList.get(position).getFlightID()));



				}
				return convertView;



			};
				};

				listView.setAdapter(flightsAdapter);


				return listView;


	}


	@Override
	public void onClick(View v) {
		Intent fd = new Intent(getActivity(), FlightDetails.class);
		fd.putExtra("flight",Integer.parseInt((String) ((TextView) v.findViewById(R.id.flightN)).getText()));
		fd.putExtra("passenger",p);
		FlightsList.this.startActivity(fd);


	}


	
	

}
