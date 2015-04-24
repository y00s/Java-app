package control;

import java.util.List;

import com.example.java_02_3265_3808.R;

import control.FlightDetails.Loader;
import model.backend.BackendFactory;
import myHelpFunc.myCasts;
import entities.Flight;
import entities.Passenger;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;


public class LogIn_Screen extends Activity  implements OnClickListener{
	ProgressDialog dialog;
	Passenger p;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	//	BackendFactory.getInstance(getApplicationContext()).setLists();
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

		setContentView(R.layout.login_screen);


		final TextView textVuew = (TextView) findViewById(R.id.wellcomTV);
		//animation of the text
		TranslateAnimation ta;
		ta= new  TranslateAnimation(0f, 0f,- 100f, 0f);
		ta.setDuration(2000);
		textVuew.startAnimation(ta);


		try {

			//===========  set buttons listeners
			// the buttons are not in use directly but trhou the seekbar

			final Button registerBt = (Button)	findViewById(R.id.RegisterButton);
			registerBt.setOnClickListener(this);

			final Button guestBt = (Button)	findViewById(R.id.guestButton);
			guestBt.setOnClickListener(this);
			Button loginBt = (Button)	findViewById(R.id.logInButton);
			loginBt.setOnClickListener(this);


			//========== seekbar animated to tow options: 
			//		1.register	<<-------O------->>  2.guest

			SeekBar slidebar = (SeekBar) findViewById(R.id.slideBarLogIn);
			slidebar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {


				@Override
				public void onStopTrackingTouch(final SeekBar seekBar) {
					if (seekBar.getProgress()<85 && seekBar.getProgress()>15) {
						ValueAnimator vanim =  ValueAnimator.ofInt(seekBar.getProgress(),50);
						vanim.setDuration(500);
						vanim.addUpdateListener(new AnimatorUpdateListener() {

							@Override
							public void onAnimationUpdate(ValueAnimator animation) {
								int an = (Integer)animation.getAnimatedValue();
								seekBar.setProgress(an);

							}
						});
						vanim.start();

					} else if(seekBar.getProgress()>85) {
						ValueAnimator vanim =  ValueAnimator.ofInt(seekBar.getProgress(),100);
						vanim.setDuration(500);
						vanim.addUpdateListener(new AnimatorUpdateListener() {

							@Override
							public void onAnimationUpdate(ValueAnimator animation) {
								int an = (Integer)animation.getAnimatedValue();
								seekBar.setProgress(an);

							}
						});
						vanim.start();

						//when animation end go to onClick(registerBt);
						vanim.addListener(new Animator.AnimatorListener() {

							@Override
							public void onAnimationStart(Animator animation) {
								// not in use

							}

							@Override
							public void onAnimationRepeat(Animator animation) {
								// not in use

							}

							@Override
							public void onAnimationEnd(Animator animation) {
								try {
									onClick(registerBt);
								} catch (Exception e) {
									Toast.makeText(LogIn_Screen.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();


								}
							}

							@Override
							public void onAnimationCancel(Animator animation) {
								// not in use

							}
						});


					}
					else {
						ValueAnimator vanim =  ValueAnimator.ofInt(seekBar.getProgress(),0);
						vanim.setDuration(500);
						vanim.addUpdateListener(new AnimatorUpdateListener() {

							@Override
							public void onAnimationUpdate(ValueAnimator animation) {
								int an = (Integer)animation.getAnimatedValue();
								seekBar.setProgress(an);

							}
						});
						vanim.start();

						//when animation end go to onClick(guestBt);
						vanim.addListener(new Animator.AnimatorListener() {

							@Override
							public void onAnimationStart(Animator animation) {
								// not in use

							}

							@Override
							public void onAnimationRepeat(Animator animation) {
								// not in use

							}

							@Override
							public void onAnimationEnd(Animator animation) {
								try {
									onClick(guestBt);
								} catch (Exception e) {
									Toast.makeText(LogIn_Screen.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
								}
							}

							@Override
							public void onAnimationCancel(Animator animation) {
								// not in use

							}
						});
					}

				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// not in use

				}

				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					// not in use

				}
			});
		} catch (Exception e) {
			Toast.makeText(LogIn_Screen.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		//put the seekbar in middle again
		SeekBar seekBar = (SeekBar) findViewById(R.id.slideBarLogIn);
		seekBar.setProgress(50);

	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.guestButton:

			try {

				Intent guest_intent = new Intent(LogIn_Screen.this,  Flights_info.class);
				LogIn_Screen.this.startActivity(guest_intent);

			} catch (Exception e) {
				Toast.makeText(LogIn_Screen.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
			}

			break;
		case R.id.RegisterButton:

			Intent register_intent = new Intent(LogIn_Screen.this, Registretion.class);
			register_intent.putExtra("fromLogin", true);
			LogIn_Screen.this.startActivity(register_intent);

			break;
		case R.id.logInButton:
			//			==========    get the data from database =============
			//			//Loading progrresBar
			dialog = new ProgressDialog(this);
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.setMessage("Loading...");
			dialog.setCancelable(false);
			new Loader().execute();

			break;
		default:
			break;
		}

	}


	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);


	}
	class Loader extends AsyncTask<Object, Object, Object> {

		@Override
		protected Object doInBackground(Object... params) {

			TextView user = (TextView) findViewById(R.id.LogdInEmail);
			TextView pass = (TextView) findViewById(R.id.logInPassword);
			
			String ps = pass.getText().toString();
			try {
				p = BackendFactory.getInstance(getBaseContext()).autenticatePassenger((String)user.getText().toString(), (String)pass.getText().toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
			try {
				if (p==null) {
					AlertDialog dialog = new AlertDialog.Builder(LogIn_Screen.this).create();
					dialog.setTitle("ERROR");
					dialog.setMessage("Worng Email or Password");
					//dialog.show();

					// -------------- if forgot password -------------
					dialog.setButton(Dialog.BUTTON_NEUTRAL, "Forgot Password", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
									"mailto","yosyes@gmail.com", null));
							emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Forgot Password");
							emailIntent.putExtra(Intent.EXTRA_TEXT,  "hi, i forgot my password, please send it to me");
							startActivity(Intent.createChooser(emailIntent, "Send email..."));

						}
					}); 
					dialog.show();


				}
				else {


					Intent login_intent = new Intent(LogIn_Screen.this,Flights_info.class);
					login_intent.putExtra("passenger", p);
					login_intent.putExtra("guest", false);
					LogIn_Screen.this.startActivity(login_intent);

				}

			} catch (Exception e) {
				e.printStackTrace();
				Log.d("NET_CONECTION", e.getLocalizedMessage());
			}
			dialog.dismiss();
		}

		@Override
		protected void onProgressUpdate(Object... values) {

			super.onProgressUpdate(values);
			dialog.setProgress( ( (Integer)values[0]).intValue() );
		}

	}
}
