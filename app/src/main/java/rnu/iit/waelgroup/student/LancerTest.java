package rnu.iit.waelgroup.student;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class LancerTest extends ActionBarActivity {
TextView notice;
final String str1="GoodMorning my students, you have a test, during the next ";
final String str2="\n After that the result will be displayed automatically. \n Good luck :)";
String newString="", newString2="";
ProgressBar pbar;
boolean exe=false;
final Handler h = new Handler(){
	
	@Override
	public void handleMessage(Message msg){
		pbar.incrementProgressBy(10);
	}
};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lancer_test);
		pbar=(ProgressBar) findViewById(R.id.pBar1);
		
		
		Bundle  extras = getIntent().getExtras();
		    if(extras == null) {
		        newString= null;
		        newString2=null;
		    } else {
	    		newString= (String) extras.getString("test");
	    		newString2=(String) extras.getString("cin");
		    }
			  
		notice= (TextView) findViewById(R.id.noticeTxt);
		notice.setText(newString+"  "+str1+" 30 minutes."+str2);
		//Toast.makeText(getApplicationContext(), newString, Toast.LENGTH_LONG).show();
		    
		if (savedInstanceState == null) {
		}
	}
public void onStart(){
	 super.onStart();
	 pbar.setProgress(0);
	 // reinitialise la barre de progression.
	 // Creation d un thread d arriere-plan qui envoie un message au handler
	 // toutes les secondes.
	 Thread threadArrierePlan = new Thread(new Runnable() {
	 public void run() {
	 try {
	 for (int i = 0; exe && i < 10; ++i) {
	 // Mise en pause d une seconde
	 Thread.sleep(1000);
	 }
	 Intent test = new Intent(getApplicationContext(), ChargementTest.class);
		test.putExtra("test", newString);
		test.putExtra("cin", newString2);
		
		startActivityForResult(test,58);

			
	 finish();
	 } catch (Throwable t) { }
	 }
	 });
	 exe = true;
	 // Lancement du thread d arriere-plan
	 threadArrierePlan.start();
	 }
	 public void onStop() {
	 super.onStop();
	 exe = false;
	 }	
	 
	 @Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lancer_test, menu);
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
