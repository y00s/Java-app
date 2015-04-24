package control;

import com.example.java_02_3265_3808.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

   public class LoadingProgressBar extends Activity   {
/** Called when the activity is first created. */
ProgressDialog  dialog;
@Override
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_flights_list);
    	dialog = new ProgressDialog(this);
    	dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    	dialog.setMessage("Loading...");
    	dialog.setCancelable(true);



    new Loader().execute();


}

class Loader extends AsyncTask<Object, Object, Object> {

    @Override
    protected Object doInBackground(Object... params) {
        // TODO Auto-generated method stub
        for( int i = 0; i <= 100; i+=10){

            publishProgress( i );
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub

        super.onPreExecute();
        dialog.show();
    }

    @Override
    protected void onPostExecute(Object result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        dialog.dismiss();
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        // TODO Auto-generated method stub
        super.onProgressUpdate(values);
        dialog.setProgress( ( (Integer)values[0]).intValue() );
    }

}}