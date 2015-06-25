package rnu.iit.waelgroup.student;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ResultatTest extends ActionBarActivity {
	Button resultBtn;
	EditText sea, mat;
	DatePicker dat;
	String newString,seaStr="", datStr="", matStr="", cin="";
	static String str="";
	Intent i;
	String[] strs;
	int id, jour, mois, annee;
	//Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultat_test);
		resultBtn= (Button) findViewById(R.id.resultBtn);
		sea= (EditText) findViewById(R.id.seaResultInput);
		dat= (DatePicker) findViewById(R.id.datePicker1);
		mat= (EditText) findViewById(R.id.matResultInput);

		jour=dat.getDayOfMonth();
		mois= dat.getMonth()+1;
		annee= dat.getYear();
		final Calendar calendar = Calendar.getInstance();
		annee = calendar.get(Calendar.YEAR);
		mois = calendar.get(Calendar.MONTH);
		jour = calendar.get(Calendar.DAY_OF_MONTH);
		dat.init(annee, mois, jour, null);

		
		  Bundle  extras = getIntent().getExtras();
		    if(extras == null) {
		        newString= null;
		    } else {

		    	String source=getCallingActivity().getShortClassName(); 
		    	source=source.substring(1);
		    	
	    		newString= (String) extras.getString("cin");
	    		Toast.makeText(getApplicationContext(), newString, Toast.LENGTH_LONG).show();
	    		cin=newString;
	    		
		    }
		
		    resultBtn= (Button) findViewById(R.id.resultBtn);
		    resultBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					seaStr=sea.getText().toString();
					jour=dat.getDayOfMonth();
					mois= dat.getMonth()+1;
					annee= dat.getYear();
					datStr= jour+"-"+mois+"-"+annee;
					matStr=mat.getText().toString();
					
					 params.add(new BasicNameValuePair("cinEtudiant", cin));
		    		 params.add(new BasicNameValuePair("seance", seaStr));
		    		 params.add(new BasicNameValuePair("date", datStr));
		    		 params.add(new BasicNameValuePair("matiere", matStr));

		 		    DownloadTask dlTask = new DownloadTask();
		 		    dlTask.execute();
		 			}
			});
		    
		if (savedInstanceState == null) {
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resultat_test, menu);
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
			// TODO Auto-generated method stub
			String returnString = "";
			InputStream is = null;
			String result = "";
				// Envoie de la commande http
				try{
					HttpClient httpclient = new DefaultHttpClient();					
					setUrl("resultattest.php");
					HttpPost httppost = new HttpPost(strURL);
					httppost.setEntity(new UrlEncodedFormEntity(params));
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();
					is = entity.getContent();
				}
				catch(Exception e){
					Log.e("log_tag", "Error in http connection " + e.toString());
				}
				// Convertion de la requête en string
				try{
					BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
					StringBuilder sb = new StringBuilder();
					String line = null;
					while ((line = reader.readLine()) != null) {
						sb.append(line + "\n");
					}
					is.close();
					result=sb.toString();
				}
				catch(Exception e){
					Log.e("log_tag", "Error converting result to string " + e.toString());
				}
				//Parse les données JSON
				try{
					JSONArray jArray = new JSONArray(result);
					for(int i=0;i<jArray.length();i++){
						JSONObject json_data = jArray.getJSONObject(i);
						// Affichage ID_question et Nom_question dans le LogCat
						Log.i("log_tag","cin: "+json_data.getString("cin")
						);
						// Résultats de la requête
			returnString += "\n\t"+","+json_data.getString("numrepjust")+","+json_data.getString("mention")+","+json_data.getString("rapidite");
						}
					
				}
				
				catch(JSONException e){
				
					Log.e("log_tag", "Error parsing data " + e.toString());
				
				}
				
				return returnString; 
		}

		protected void onPostExecute(String returnString) {
			str= returnString;
			Intent i = new Intent(getApplicationContext(), ResultatActivity.class);
 			Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
 			i.putExtra("res", str);
 			startActivityForResult(i, 55);
 			ResultatTest.this.finish();
		
    		
		}
		
	}

}
