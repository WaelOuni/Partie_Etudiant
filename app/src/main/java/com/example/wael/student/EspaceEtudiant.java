package com.example.wael.student;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class EspaceEtudiant extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, Profile.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener, Calendar.OnFragmentInteractionListener,
        NewsFragment.OnFragmentInteractionListener, ContactsFragment.OnFragmentInteractionListener,CoursesFragment.OnFragmentInteractionListener,
        TestsFragment.OnFragmentInteractionListener,ResultsFragment.OnFragmentInteractionListener,SignOutFragment.OnFragmentInteractionListener{

    final int PROFIL_FRAGENT_ID = 1000;
    final int HOME_FRAGENT_ID = 2000;
    final int CALENDAR_FRAGENT_ID = 3000;
    final int NEWS_FRAGENT_ID = 4000;
    final int CONTACT_FRAGENT_ID = 5000;
    final int COURSES_FRAGENT_ID = 6000;
    final int TESTS_FRAGENT_ID = 7000;
    final int RESULTS_FRAGENT_ID = 8000;
    final int SIGNOUT_FRAGENT_ID = 9000;

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
        setContentView(R.layout.activity_espace_etudiant);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        String str = "Student space";
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment objFragment = null;

        Toast toast = Toast.makeText(this, "Wheeeeeee!"+ position, Toast.LENGTH_SHORT);
        toast.show();
        switch (position){
            case 0:
                getSupportActionBar().setTitle("Profile Student");
                mTitle= getString(R.string.title_section1);
                objFragment = Profile.newInstance("Profile Student","");
                break;
            case 1:
                getSupportActionBar().setTitle("Homme");
                mTitle=getString(R.string.title_section2) ;
                objFragment = HomeFragment.newInstance("Home","");
                break;
            case 2:
                getSupportActionBar().setTitle("Calendar");
                mTitle=getString(R.string.title_section3);
                objFragment = Calendar.newInstance("Calendar","");
                break;
            case 3:
                getSupportActionBar().setTitle("News & alerts");
                mTitle=getString(R.string.title_section4);
                objFragment = NewsFragment.newInstance("News & alerts","");
                break;
            case 4:
                getSupportActionBar().setTitle("Contacts");
                mTitle=getString(R.string.title_section5);
                objFragment = ContactsFragment.newInstance("Contacts","");
                break;
            case 5:
                getSupportActionBar().setTitle("Courses");
                mTitle=getString(R.string.title_section6);
                objFragment = CoursesFragment.newInstance("Courses","");
                break;
            case 6:
                getSupportActionBar().setTitle("Tests");
                mTitle=getString(R.string.title_section7);
                objFragment = TestsFragment.newInstance("Tests","");
                break;
            case 7:

                getSupportActionBar().setTitle("Results");
                mTitle=getString(R.string.title_section8);
                objFragment = ResultsFragment.newInstance("Results","");
                break;
            case 8:

                getSupportActionBar().setTitle("Sign out");
                mTitle=getString(R.string.title_section9);
                objFragment = SignOutFragment.newInstance("Sign out","");
                break;

        }
        if (objFragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, objFragment).commit();

        }
    }

    public void onSectionAttached(int number) {

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
            return true;
        } else if (id==R.id.action_example){

       //     displayListView();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(int id) {

        Toast toast = Toast.makeText(this, "Wheeeeeee! "+ id, Toast.LENGTH_SHORT);
        toast.show();
    }


    //////////////////////    functionn to tes if a connection with the local database exist or not  //////////////////////////////
    protected boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo !=null && netInfo.isConnectedOrConnecting()){
            return  true;
        }else {
            return false;
        }
    }///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*


    @Override
    protected void onResume() {
        super.onResume();
        //Starts a new or restarts an existing Loader in this manager
        getLoaderManager().restartLoader(0, null, this);
    }

    private void displayListView() {


        // The desired columns to be bound
        String[] columns = new String[] {
                CourseDB.KEY_IDCOURSE,
                CourseDB.KEY_NAME,
                CourseDB.KEY_DESCRIPTION,
                CourseDB.KEY_URL,
                CourseDB.KEY_TEACHER,
                CourseDB.KEY_SUBJECT,
                CourseDB.KEY_DATEDEPO
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[] {
              *//*  R.id.,
                R.id.name,
                R.id.continent,*//*
        };

        // create an adapter from the SimpleCursorAdapter
        dataAdapter = new SimpleCursorAdapter(
                this,
                R.layout.country_info,
                null,
                columns,
                to,
                0);

        // get reference to the ListView
        ListView listView = (ListView) findViewById(R.id.countryList);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
        //Ensures a loader is initialized and active.
        getLoaderManager().initLoader(0, null, this);


        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                // display the selected country
                String countryCode =
                        cursor.getString(cursor.getColumnIndexOrThrow(CountriesDb.KEY_CODE));
                Toast.makeText(getApplicationContext(),
                        countryCode, Toast.LENGTH_SHORT).show();

                String rowId =
                        cursor.getString(cursor.getColumnIndexOrThrow(CountriesDb.KEY_ROWID));

                // starts a new Intent to update/delete a Country
                // pass in row Id to create the Content URI for a single row
                Intent countryEdit = new Intent(getBaseContext(), CountryEdit.class);
                Bundle bundle = new Bundle();
                bundle.putString("mode", "update");
                bundle.putString("rowId", rowId);
                countryEdit.putExtras(bundle);
                startActivity(countryEdit);

            }
        });

    }

    // This is called when a new Loader needs to be created.
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                CountriesDb.KEY_ROWID,
                CountriesDb.KEY_CODE,
                CountriesDb.KEY_NAME,
                CountriesDb.KEY_CONTINENT};
        CursorLoader cursorLoader = new CursorLoader(this,
                MyContentProvider.CONTENT_URI, projection, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)
        dataAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        dataAdapter.swapCursor(null);
    }*/
}
