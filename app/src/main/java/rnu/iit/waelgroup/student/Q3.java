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
import android.widget.EditText;
import android.widget.TextView;

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

public class Q3 extends ActionBarActivity {
	Button btn3;
	 String newString="",newString2=null, newString3="";
	 String[]strs;
	 static String str=" ";
	 ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
	 static int sommeRepJustes=0;
	 TextView q3txt;
	 EditText r;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_q3);
		q3txt=(TextView) findViewById(R.id.q3ennonce);
		r=(EditText) findViewById(R.id.q3reponseInput);
		//r.setText("");
		btn3=(Button) findViewById(R.id.suivant3Btn);
		btn3.setVisibility(View.INVISIBLE);
		Bundle  extras = getIntent().getExtras();
	    if(extras == null) {
	        newString= null;
	    } else {
    		newString= (String) extras.getString("q3");
    		newString2=(String) extras.getString("cin");
    		newString3= (String) extras.getString("id");
    		sommeRepJustes=(Integer)extras.getInt("somme");
    		//Toast.makeText(getApplicationContext(), String.valueOf(sommeRepJustes), Toast.LENGTH_LONG).show();

    		//Toast.makeText(getApplicationContext(), newString2, Toast.LENGTH_LONG).show();
    		//Toast.makeText(getApplicationContext(), newString3, Toast.LENGTH_LONG).show();
	    }
strs=newString.split(",");
	    params.add(new BasicNameValuePair("id", strs[2]));
	    DownloadTask dlTask = new DownloadTask();
	    dlTask.execute();
btn3.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {

		Intent q4 = new Intent(getApplicationContext(), Q4.class);
		q4.putExtra("q4", newString);
		q4.putExtra("cin", newString2);
		q4.putExtra("id", newString3);
		if(strs[1].contains(r.getText().toString())){
			sommeRepJustes++;
		}
		q4.putExtra("somme", sommeRepJustes);
		startActivityForResult(q4, 48);
			Q3.this.finish();
		
	}
});

		if (savedInstanceState == null) {
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.q3, menu);
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
					setUrl("question.php");
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
						Log.i("log_tag","ennonce: "+json_data.getString("enonce")
						);
						// Résultats de la requête
			returnString += "\n\t"+json_data.getString("enonce")+","+json_data.getString("reponse");
						}				
				}
				
				catch(JSONException e){
				
					Log.e("log_tag", "Error parsing data " + e.toString());
				
				}
				
				return returnString; 
		}

		protected void onPostExecute(String returnString) {
			strs=returnString.split("\n\t");
			str=strs[1];
			strs=str.split(",");
			
		//	Toast.makeText(getApplicationContext(), strs[0], Toast.LENGTH_LONG).show();
 			q3txt.setText(q3txt.getText().toString()+" "+strs[0]);
 			btn3.setVisibility(View.VISIBLE);
		}
		
	}
}
