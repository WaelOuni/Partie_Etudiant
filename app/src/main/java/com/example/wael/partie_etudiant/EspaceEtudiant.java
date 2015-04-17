package com.example.wael.partie_etudiant;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
        setContentView(R.layout.activity_espace_etudiant2);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        String str = "My space";
        mTitle = str.subSequence(0,4);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(int id) {

        Toast toast = Toast.makeText(this, "Wheeeeeee! "+ id, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_espace_etudiant2, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((EspaceEtudiant) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
