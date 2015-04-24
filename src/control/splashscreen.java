package control;




import model.backend.BackendFactory;

import com.example.java_02_3265_3808.R;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class splashscreen extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen); 
		//overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


			try {
				if ((BackendFactory.getInstance(getApplicationContext())).getFlightlist().size()==0) {
					BackendFactory.getInstance(getApplicationContext()).setLists();
				}
				
			} catch (Exception e) {
				
				Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
			
	
	
	//maybe combine the 2 functions with progress bar
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent login_intent = new Intent(splashscreen.this,LogIn_Screen.class);
				splashscreen.this.startActivity(login_intent);
				splashscreen.this.finish();
				
			}
	}, 700);

	}

	
}
