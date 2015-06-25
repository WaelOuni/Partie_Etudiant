package rnu.iit.waelgroup.student;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChargementTest extends ActionBarActivity {
Button start;
String newString="", newString2="";
TextView txt;

static String str="";
ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
String[]strs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chargement_test);
		
		Bundle  extras = getIntent().getExtras();
		    if(extras == null) {
		        newString= null;
		        newString2=null;
		    } else {
	    		newString= (String) extras.getString("test");

	    		newString2=(String) extras.getString("cin");
		    }
		
		
///////////// de test
		    newString="5"; 
		    newString2="14207208";
					    		
		    		params.add(new BasicNameValuePair("id", newString));
			 
txt=(TextView) findViewById(R.id.finchargeTxt);
txt.setVisibility(-1);
txt.setText("Fin de chargement de touts les qestions de test");
start=(Button) findViewById(R.id.startBtn);

start.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {

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
		getMenuInflater().inflate(R.menu.chargement_test, menu);
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
					setUrl("lanceTest.php");
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
						Log.i("log_tag","numquestchoisis: "+json_data.getString("numquestchoisis")
						);
						// Résultats de la requête
			returnString += "\n\t"+json_data.getString("numquestchoisis");
						}
				}
				
				catch(JSONException e){
					Log.e("log_tag", "Error parsing data " + e.toString());
				
				}
				
				return returnString; 
		}

		protected void onPostExecute(String returnString) {
		//	str= returnString;
 			txt.setVisibility(1);
strs=returnString.split("\n\t");
str=strs[1];
strs=str.split(",");
Toast.makeText(getApplicationContext(), strs[0], Toast.LENGTH_LONG).show();
Intent q1 = new Intent(getApplicationContext(), Q1.class);
	q1.putExtra("q1", str);
	q1.putExtra("cin", newString2);
	q1.putExtra("id", newString);
	
startActivityForResult(q1, 54);
ChargementTest.this.finish();
		}
		
	}
}
