package rnu.iit.waelgroup.student;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultatActivity extends ActionBarActivity {
	String newString, mention="";
	String[] strs;
	int numrepjustes=0, rapidite=0, minute, seconde, note=0;
	Button terminer;
	TextView numrepjustesTxt,numrepfaussesTxt, mentionTxt, rapiditeTxt, succesechecTxt, noteTxt;
	ImageView im;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultat);
		noteTxt= (TextView) findViewById(R.id.noteInput);
		numrepjustesTxt= (TextView) findViewById(R.id.reponsejustInput);
		numrepfaussesTxt= (TextView) findViewById(R.id.reponsefauxInput);
		mentionTxt= (TextView) findViewById(R.id.mentionInput);
		rapiditeTxt= (TextView) findViewById(R.id.rapiditeInput);
		succesechecTxt= (TextView) findViewById(R.id.succesechecTxt);
		im =(ImageView) findViewById(R.id.succesechec);
		terminer=(Button) findViewById(R.id.terminer);
		  Bundle  extras = getIntent().getExtras();
		    if(extras == null) {
		        newString= null;
		    } else {

	    		newString= (String) extras.getString("res");
	    		Toast.makeText(getApplicationContext(), newString, Toast.LENGTH_LONG).show();
	    		strs=newString.split(",");
	    		//Toast.makeText(getApplicationContext(), strs[1], Toast.LENGTH_LONG).show();
	    		
	    		numrepjustes= Integer.parseInt(strs[1]);
	    		note = numrepjustes*4;
	    		noteTxt.setText(String.valueOf(note)+"/20");
	    		mention= strs[2];
	    		rapidite= Integer.parseInt(strs[3]);
	    		numrepjustesTxt.setText(strs[1]+ "/5");
	    		mentionTxt.setText(mention);
	    		numrepfaussesTxt.setText(String.valueOf((5-numrepjustes))+ "/5");
	    		minute= rapidite/60;
	    		seconde= rapidite%60;
	    		rapiditeTxt.setText(String.valueOf(minute)+ " minutes et "+String.valueOf(seconde)+ " secondes");
	    		
	    		if (numrepjustes>2){
	    			succesechecTxt.setText("Succes :)");
	    			im.setImageResource(R.drawable.succesicon);
	    		}else{ succesechecTxt.setText("Echec :(");
    			
	    			im.setImageResource(R.drawable.echecicon);}
		    
	    		terminer.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						ResultatActivity.this.finish();
					}
				});
		    }
		    

		if (savedInstanceState == null) {
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resultat, menu);
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
