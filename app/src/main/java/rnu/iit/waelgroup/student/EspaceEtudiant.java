package rnu.iit.waelgroup.student;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rnu.iit.waelgroup.student.Models.JsonParser;
import rnu.iit.waelgroup.student.Models.Subject;
import rnu.iit.waelgroup.student.Util.OnlineChecker;


public class EspaceEtudiant extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks, Profile.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener,
Calendar.OnFragmentInteractionListener, NewsFragment.OnFragmentInteractionListener, ContactsFragment.OnFragmentInteractionListener,
CoursesFragment.OnFragmentInteractionListener,TestsFragment.OnFragmentInteractionListener,ResultsFragment.OnFragmentInteractionListener{

    public static ArrayList<Subject> subjects = new ArrayList<Subject>() ;
    public static String yourJsonStringUrl;
    public static String cin_key;
    private static boolean load = true;
    final int HOME_FRAGENT_ID = 1000;
    final int PROFIL_FRAGENT_ID = 2000;
    final int CALENDAR_FRAGENT_ID = 3000;
    final int NEWS_FRAGENT_ID = 4000;
    final int CONTACT_FRAGENT_ID = 5000;
    final int COURSES_FRAGENT_ID = 6000;
    final int TESTS_FRAGENT_ID = 7000;
    final int RESULTS_FRAGENT_ID = 8000;
    final int SIGNOUT_FRAGENT_ID = 9000;
    private ServerSocket serverSocket;

    Handler updateConversationHandler;

    Thread serverThread = null;

    public static final int SERVERPORT = 6000;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espace_etudiant2);
        updateConversationHandler = new Handler();

        serverThread = new Thread(new ServerThread());
        serverThread.start();
        yourJsonStringUrl=getString(R.string.url_base_student);
        cin_key = this.getIntent().getStringExtra("session");
        Toast.makeText(getApplicationContext(), cin_key, Toast.LENGTH_LONG).show();
        OnlineChecker oc = new OnlineChecker();

        for(String str : getConnectionDetails().values())
            Log.i("status connection :",""+str);


        if (load) {
            if ( oc.isOnline(this) ){
            AsyncTaskParseJson dlTask = new AsyncTaskParseJson();
            dlTask.execute(LoginActivity.yourJsonStringUrl);
        /*}
            else {
                Toast.makeText(this,"Network isn't available",
                        Toast.LENGTH_LONG).show();
                subjects.add(new Subject("test ","test"));
                Log.i("Hors ligne","Hors ligne");
            }*/
        }
    }
            mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
            mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }



    /**
     * Get all connection details 1. Status 2. Type: Mobile/wifi 3. Sub type 4.
     * Roaming status (Only for mobile network)
     *
     * @return Map<String, String>
     */
    private Map<String, String> getConnectionDetails() {
        Map<String, String> networkDetails = new HashMap<String, String>();
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiNetwork = connectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wifiNetwork != null && wifiNetwork.isConnected()) {

                networkDetails.put("Type", wifiNetwork.getTypeName());
                networkDetails.put("Sub type", wifiNetwork.getSubtypeName());
                networkDetails.put("State", wifiNetwork.getState().name());
            }

            NetworkInfo mobileNetwork = connectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mobileNetwork != null && mobileNetwork.isConnected()) {
                networkDetails.put("Type", mobileNetwork.getTypeName());
                networkDetails.put("Sub type", mobileNetwork.getSubtypeName());
                networkDetails.put("State", mobileNetwork.getState().name());
                if (mobileNetwork.isRoaming()) {
                    networkDetails.put("Roming", "YES");
                } else {
                    networkDetails.put("Roming", "NO");
                }
            }
        } catch (Exception e) {
            networkDetails.put("Status", e.getMessage());
        }
        return networkDetails;
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment objFragment = null;
     //   Toast toast = Toast.makeText(this, "Wheeeeeee!"+ position, Toast.LENGTH_SHORT);
     //   toast.show();
        switch (position){
            case 0:
                mTitle= getString(R.string.title_section1);
                objFragment = HomeFragment.newInstance("Home","");
                break;
            case 1:
                mTitle=getString(R.string.title_section2) ;
                objFragment = Profile.newInstance("Profile Student","");
                break;
            case 2:
                mTitle=getString(R.string.title_section3) ;
                objFragment = Calendar.newInstance("Calendar","");
                break;
            case 3:
                mTitle=getString(R.string.title_section4) ;
                objFragment = NewsFragment.newInstance("News & alerts","");
                break;
            case 4:
                mTitle=getString(R.string.title_section5) ;
                objFragment = ContactsFragment.newInstance("Contacts","");
                break;
            case 5:
                mTitle=getString(R.string.title_section6) ;
                objFragment = CoursesFragment.newInstance("Courses","");
                break;
            case 6:
                mTitle=getString(R.string.title_section7) ;
                objFragment = TestsFragment.newInstance("Tests","");
                break;
            case 7:
                mTitle=getString(R.string.title_section8) ;
                objFragment = ResultsFragment.newInstance("Results","");
                break;
            case 8:
                mTitle=getString(R.string.title_section9) ;
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                break;

        }
        if (objFragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, objFragment).commit();
        }
    }


    public void onSectionAttached(int number) {
        Toast toast = Toast.makeText(this, "Wheeeeeee!"+ number, Toast.LENGTH_SHORT);
        toast.show();
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
                case 4:
                    mTitle = getString(R.string.title_section4);
                    break;
                case 5:
                    mTitle = getString(R.string.title_section5);
                    break;
                case 6:
                    mTitle = getString(R.string.title_section6);
                    break;
                case 7:
                     mTitle = getString(R.string.title_section7);
                     break;
                case 8:
                     mTitle = getString(R.string.title_section8);
                     break;
                case 9:
                     mTitle = getString(R.string.title_section9);
                     break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.espace_etudiant, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            startActivity(new Intent(getApplicationContext(), QuickPrefsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(int id) {
        Toast toast = Toast.makeText(this, "Wheeeeeee! "+ id, Toast.LENGTH_SHORT);
        toast.show();
    }




    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {

        final String TAG = "AsyncTaskParseJson.java";
        JSONArray dataJsonArr = null;
        int idSubject;
        String teacher;
        String name;

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... arg0) {

            try {
                // instantiate our json parser
                JsonParser jParser = new JsonParser();
                Log.i("test json object ", "test");
                // get json string from url
                JSONObject json = jParser.getJSONFromUrl(yourJsonStringUrl + "/allsubjects.php", null);
                dataJsonArr= json.getJSONArray("auth");
                for(int i=0;i<dataJsonArr.length();i++){
                    JSONObject c = dataJsonArr.getJSONObject(i);
                    // Storing each json item in variable
                    idSubject = c.getInt("id_subject");
                    name = c.getString("name_subject");
                    teacher = c.getString("teacher_subject");
                    if (idSubject!=0)
                        subjects.add(new Subject(name ,teacher));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String str) {
            Toast.makeText(getApplicationContext(), "You are welcome :) ", Toast.LENGTH_SHORT).show();
            load = false;
        }
    }



    class ServerThread implements Runnable {

        public void run() {
            Socket socket = null;
            try {
                serverSocket = new ServerSocket(SERVERPORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (!Thread.currentThread().isInterrupted()) {

                try {

                    socket = serverSocket.accept();
                    System.out.println("en ecoute des sockets clients :) \n");
                    CommunicationThread commThread = new CommunicationThread(socket);
                    new Thread(commThread).start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class CommunicationThread implements Runnable {

        private Socket clientSocket;

        private BufferedReader input;

        public CommunicationThread(Socket clientSocket) {

            this.clientSocket = clientSocket;

            try {

                this.input = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {


            while (!Thread.currentThread().isInterrupted()) {

                try {

                    String read = input.readLine();

                    updateConversationHandler.post(new updateUIThread(read));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    class updateUIThread implements Runnable {
        private String msg;
        public updateUIThread(String str) {
            this.msg = str;
        }

        @Override
        public void run() {
            Log.i("ya rabi", msg);
            Intent test = new Intent(EspaceEtudiant.this.getApplicationContext(), LancerTest.class);
            test.putExtra("test", msg);
            test.putExtra("cin", cin_key);
            startActivityForResult(test, 50);
            //	onStop();

        }

    }


}
