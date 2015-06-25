package rnu.iit.waelgroup.student;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class ResultatFinTest extends ActionBarActivity {

	TextView note, mention,remarque;
	Button espace;
	ImageView im;
	String newString="",newString2= null, newString3="", duree="";
	int numrepjustes, score;
	List<NameValuePair> params;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultat_fin_test);

		note= (TextView) findViewById(R.id.noteFinTest);
		mention= (TextView) findViewById(R.id.mentionFinTest);
		remarque= (TextView) findViewById(R.id.remarqueTxt);
		im =(ImageView) findViewById(R.id.remarqueImg);
		params = new ArrayList<NameValuePair>();
		 Bundle  extras = getIntent().getExtras();
		    if(extras == null) {
		        newString= null;
		    } else {

	    		numrepjustes=(Integer) extras.getInt("somme");
	    		newString2=(String) extras.getString("cin");
	    		newString3= (String) extras.getString("id");
		    }
		    duree="30";
		    
		    if (numrepjustes>2){
    			remarque.setText("Succes :)");
    			im.setImageResource(R.drawable.succesicon);
    		}else{ remarque.setText("Echec :(");
			
    			im.setImageResource(R.drawable.echecicon);}
	    
    	score= numrepjustes*4;
    	note.setText(String.valueOf(score)+" / 20");
    	if (score<10) mention.setText("faible"); else if (score<12) mention.setText("passable"); else if (score<16) mention.setText("assez bien"); else mention.setText("excellent"); 
		
    	
		params.add(new BasicNameValuePair("cin", newString2));
		params.add(new BasicNameValuePair("numrepjust", String.valueOf(numrepjustes)));
		params.add(new BasicNameValuePair("mention", mention.getText().toString()));
		params.add(new BasicNameValuePair("rapidite", duree));
		params.add(new BasicNameValuePair("idtest", newString3));
		
    	espace=(Button) findViewById(R.id.espaceBtn);
		espace.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				DownloadTask dlTask = new DownloadTask();
				dlTask.execute(); 		
				ResultatFinTest.this.finish();
			}
		});
		if (savedInstanceState == null) {
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resultat_fin_test, menu);
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

	public void setUrl(String newstr){
		strURL+=newstr;
	}
	
	public   String strURL = "http://10.0.3.2/MyProjectConnect/Etudiant/";
	public class DownloadTask extends AsyncTask<UrlEncodedFormEntity, Void, String>{
		
		@Override
		protected String doInBackground(UrlEncodedFormEntity... urls) {
			
			String returnString = "";
			
				// Envoie de la commande http
				try{
					HttpClient httpclient = new DefaultHttpClient();					
					setUrl("CreeResultFinTest.php");
					HttpPost httppost = new HttpPost(strURL);
					httppost.setEntity(new UrlEncodedFormEntity(params));
					httpclient.execute(httppost);
				}
				catch(Exception e){
					Log.e("log_tag", "Error in http connection " + e.toString());
				}
				
				return returnString; 
		}

		protected void onPostExecute(String returnString) {
			Toast.makeText(getApplicationContext(),"Result added succefully" , Toast.LENGTH_LONG).show();
		}

	}
}
