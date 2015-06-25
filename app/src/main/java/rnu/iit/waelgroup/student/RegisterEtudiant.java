package rnu.iit.waelgroup.student;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.util.ArrayList;

import rnu.iit.waelgroup.student.Models.JsonParserUpdateBD;


public class RegisterEtudiant extends ActionBarActivity {
    private TabHost myTabHost;
    private AsyncTaskParseJson dlTask;
    Button creercompte, viderInscrip;
    EditText nom, prenom, cin, email, motpasse, motpasse2, specialite, telephone, classe;
    Spinner grade;
    CheckBox showPass;
    String nomStr, prenomStr, cinStr, genreStr, emailStr, motpasseStr, motpasse2Str, gradeStr, specialiteStr, telephoneStr, classStr;
    //Building Parameters
    ArrayList<NameValuePair> dataCreate;
    RadioButton homme, femme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_etudiant);

        getMyComponents();

        showPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    motpasse.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    motpasse2.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    motpasse.setInputType(129);
                    motpasse2.setInputType(129);
                }
            }
        });

        creercompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                dataToUrl();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        viderInscrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viderChamps();
            }
        });



    }

    private void getMyComponents() {

        creercompte = (Button) findViewById(R.id.creercompteBtn);
        viderInscrip = (Button) findViewById(R.id.viderInscriBtn);
        nom = (EditText) findViewById(R.id.nomInput);
        prenom = (EditText) findViewById(R.id.prenomInput);
        cin = (EditText) findViewById(R.id.cinInput);
        motpasse = (EditText) findViewById(R.id.passwordInput);
        motpasse2 = (EditText) findViewById(R.id.repasswordInput);
        email = (EditText) findViewById(R.id.emailInput);
        grade = (Spinner) findViewById(R.id.gradeInput);
        specialite = (EditText) findViewById(R.id.specialiteInput);
        telephone = (EditText) findViewById(R.id.numTelInput);
        classe = (EditText) findViewById(R.id.classInput);
        homme = (RadioButton) findViewById(R.id.hommeRadio);
        femme = (RadioButton) findViewById(R.id.femmeRadio);
        showPass = (CheckBox) findViewById(R.id.showPass);
        dataCreate = new ArrayList<NameValuePair>();
        myTabHost = (TabHost) findViewById(R.id.tabHost);
        // Before adding tabs, it is imperative to call the method setup()
        myTabHost.setup();
        myTabHost.addTab(myTabHost.newTabSpec("tab_a").setIndicator("Step 1", getResources().getDrawable(android.R.drawable.ic_menu_view)).setContent(R.id.tab1));
        myTabHost.addTab(myTabHost.newTabSpec("tab_b").setIndicator("Step 2", getResources().getDrawable(android.R.drawable.ic_menu_view)).setContent(R.id.tab2));
        myTabHost.addTab(myTabHost.newTabSpec("tab_c").setIndicator("Step 3", getResources().getDrawable(android.R.drawable.ic_menu_view)).setContent(R.id.tab3));
    }

    private void getData() {
        nomStr = nom.getText().toString();
        prenomStr = prenom.getText().toString();
        cinStr = cin.getText().toString();
        if (homme.isChecked()) {
            genreStr = "homme";
        } else {
            genreStr = "femme";
        }
        emailStr = email.getText().toString();
        motpasseStr = motpasse.getText().toString();
        motpasse2Str = motpasse2.getText().toString();
        gradeStr = grade.getSelectedItem().toString();
        specialiteStr = specialite.getText().toString();
        telephoneStr = telephone.getText().toString();
    }

    private boolean checkedPassword(String pass1, String pass2) {

        if (pass1.equals(pass2)) {
            return true;
        } else
            return false;
    }

    private void dataToUrl() {
        if ((nomStr != null) && (nomStr.trim().length() > 0) && (prenomStr != null) && (prenomStr.trim().length() > 0)
                && (cinStr != null) && (cinStr.trim().length() > 0) && (genreStr != null) && (genreStr.trim().length() > 0)
                && (emailStr != null) && (emailStr.trim().length() > 0) && (motpasseStr != null) && (motpasse2Str != null) &&
                (motpasseStr.trim().length() > 0) && (motpasse2Str.trim().length() > 0) && (gradeStr != null) &&
                (gradeStr.trim().length() > 0) && (specialiteStr != null) && (specialiteStr.trim().length() > 0)
                && (telephoneStr != null) && (telephoneStr.trim().length() > 0) && checkedPassword(motpasseStr, motpasse2Str)) {
            dataCreate.add(new BasicNameValuePair("cin_etudiant", cin.getText().toString()));
            dataCreate.add(new BasicNameValuePair("nom", nom.getText().toString()));
            dataCreate.add(new BasicNameValuePair("prenom", prenom.getText().toString()));
            dataCreate.add(new BasicNameValuePair("inscription", specialite.getText().toString()));
            dataCreate.add(new BasicNameValuePair("genre", genreStr));
            dataCreate.add(new BasicNameValuePair("email", email.getText().toString()));
            dataCreate.add(new BasicNameValuePair("password", motpasse.getText().toString()));
            dataCreate.add(new BasicNameValuePair("niveau", grade.getSelectedItem().toString()));
            dataCreate.add(new BasicNameValuePair("telephone", telephone.getText().toString()));
            dataCreate.add(new BasicNameValuePair("num_classe", classe.getText().toString()));
            dlTask = new AsyncTaskParseJson();
            dlTask.execute();

        } else {
            Toast.makeText(getApplicationContext(), "All fields are required ! ", Toast.LENGTH_LONG).show();
        }
    }

    private void viderChamps() {
        nom.setText("");
        prenom.setText("");
        cin.setText("");
        email.setText("");
        motpasse.setText("");
        motpasse2.setText("");
        grade.setSelection(0);
        specialite.setText("");
        telephone.setText("");
        classe.setText("");
        showPass.setChecked(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_etudiant, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:

                startActivity(new Intent(getApplicationContext(), QuickPrefsActivity.class));

                break;

            case R.id.viderChamps:
                viderChamps();
                Toast.makeText(getApplicationContext(),"Les champs sont vident ",Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private class AsyncTaskParseJson extends AsyncTask<String, String, String> {

        final String TAG = "AsyncTaskParseJson.java";
        JSONArray dataJsonArr = null;

        @Override
        protected void onPreExecute() {
            //   updateBtn.setEnabled(false);
            //   showProgress(true);
        }

        @Override
        protected String doInBackground(String... arg0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            try {

                //   Log.i(dataUpdate.get(3).getName(), dataUpdate.get(3).getValue());
                // instantiate our json parser
                JsonParserUpdateBD jParser = new JsonParserUpdateBD();
                // get json string from url
                jParser.setUrlFromJson(getString(R.string.url_base_student) + "/inscrireEtudiant.php", dataCreate);
            } catch (Exception e) {
                Log.e("error update table", e.getMessage());
                //    showProgress(false);
            }
            return "";
        }

        @Override
        protected void onPostExecute(String str) {
            //load = false;
            dlTask = null;
            //    showProgress(false);
            Toast.makeText(getApplicationContext(), "your inscription is done", Toast.LENGTH_LONG).show();
            //  updateBtn.setEnabled(true);
        }
    }

}
