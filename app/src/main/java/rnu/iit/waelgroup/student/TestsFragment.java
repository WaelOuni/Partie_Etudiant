package rnu.iit.waelgroup.student;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rnu.iit.waelgroup.student.Models.JsonParser;
import rnu.iit.waelgroup.student.Models.Test;
import rnu.iit.waelgroup.student.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class TestsFragment extends Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static ArrayList<Test> tests = new ArrayList<Test>();
    private static String yourJsonStringUrl;
    private static boolean load = true;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    rnu.iit.waelgroup.student.Adapters.ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    /**
     * The fragment's ListView/GridView.
     */
    private ListView mListView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TestsFragment() {
    }

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
//    private MyExpandableListItemAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static TestsFragment newInstance(String param1, String param2) {
        TestsFragment fragment = new TestsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        yourJsonStringUrl = getString(R.string.url_base_student) + "alltests.php";
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        if (load) {
            AsyncTaskParseJson dlTask = new AsyncTaskParseJson();
            dlTask.execute();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tests_list, container, false);
        // get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.expandableListView);

        return view;
    }
    /*
   * Preparing the list data
   */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        for (int j =0 ; j< tests.size(); j++) {
            listDataHeader.add("Test "+tests.get(j).getId_test()+" : "+tests.get(j).getSubject());
     //   Log.i("lala", tests.size()+"");
            String teacher_test = tests.get(j).getTeacher();
            String level_test = tests.get(j).getLevel();
            String session_test = Integer.toString(tests.get(j).getSession());
            String date_test = tests.get(j).getDate();
            String duration_test = tests.get(j).getDuration();
            String courses_test = tests.get(j).getCourses();
            List<String> childs = new ArrayList<String>();
            childs.add(teacher_test);
            childs.add(date_test);
            childs.add(session_test);
            childs.add(duration_test);
            childs.add(courses_test);
          //  Log.i("loulou", childs.size()+"");
            listDataChild.put("Test "+tests.get(j).getId_test()+" : "+tests.get(j).getSubject(), childs);
        }
        Log.i("loulou", listDataChild.size()+" :::: "+ listDataHeader.size());
        Log.i("loulou", listDataChild.toString()+" :::: "+ listDataHeader.toString());

/*

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);*/
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(Integer.parseInt(DummyContent.ITEMS.get(position).id));
        }
    }

    /*
            * The default content for this Fragment has a TextView that is shown when
    * the list is empty. If you would like to change the text, call this method
    * to supply the text it should use.
    */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(int id);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.menu_home_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:

                startActivity(new Intent(getActivity(), QuickPrefsActivity.class));
                break;

            case R.id.action_fragment:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, ResultsFragment.newInstance("Results", ""), ResultsFragment.class.getName()).commit();
                Toast.makeText(getActivity(), this.mParam1 + " is refreshed", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {
        final String TAG = "AsyncTaskParseJson.java";
        JSONArray dataJsonArr = null;
        int id_test;
        String subject_test;
        String teacher_test;
        String level_test;
        int session_test;
        String returnString = "";
        String date_test;
        String duration_test;
        String courses_test;

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
                JSONObject json = jParser.getJSONFromUrl(getString(R.string.url_base_student) + "/alltests.php", null);
                dataJsonArr = json.getJSONArray("auth");
                Log.i("test json object ", "test");
                for (int i = 0; i < dataJsonArr.length(); i++) {
                    JSONObject c = dataJsonArr.getJSONObject(i);
                    // Storing each json item in variable
                    id_test = c.getInt("id_test");
                    subject_test = c.getString("subject_test");
                    teacher_test = c.getString("teacher_test");
                    level_test = c.getString("level_test");
                    session_test = c.getInt("session_test");
                    date_test = c.getString("date_test");
                    duration_test = c.getString("duration_test");
                    courses_test = c.getString("courses_test");
                    // if (id_test!=0)
                    tests.add(new Test(id_test, subject_test, teacher_test, level_test, session_test,
                            date_test, duration_test, courses_test));
                    //   Log.i("test isert test", courses_test);
                    returnString += "\n\t" + subject_test + ":" + level_test;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return returnString;
        }


        @Override
        protected void onPostExecute(String str) {
            // preparing list data
            prepareListData();
            listAdapter = new rnu.iit.waelgroup.student.Adapters.ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
            // setting list adapter
            expListView.setAdapter(listAdapter);
            // Listview Group click listener


            expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                @Override
                public boolean onGroupClick(ExpandableListView parent, View v,
                                            int groupPosition, long id) {
                    // Toast.makeText(getApplicationContext(),
                    // "Group Clicked " + listDataHeader.get(groupPosition),
                    // Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

            // Listview Group expanded listener
            expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                @Override
                public void onGroupExpand(int groupPosition) {
                    Toast.makeText(getActivity(),
                            listDataHeader.get(groupPosition) + " Expanded",
                            Toast.LENGTH_SHORT).show();
                }
            });

            // Listview Group collasped listener
            expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                @Override
                public void onGroupCollapse(int groupPosition) {
                    Toast.makeText(getActivity(),
                            listDataHeader.get(groupPosition) + " Collapsed",
                            Toast.LENGTH_SHORT).show();

                }
            });

            // Listview on child click listener
            expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

                @Override
                public boolean onChildClick(ExpandableListView parent, View v,
                                            int groupPosition, int childPosition, long id) {
                    // TODO Auto-generated method stub
                    Toast.makeText(
                            getActivity(),
                            listDataHeader.get(groupPosition)
                                    + " : "
                                    + listDataChild.get(
                                    listDataHeader.get(groupPosition)).get(
                                    childPosition), Toast.LENGTH_SHORT)
                            .show();
                    return false;
                }
            });

            Log.i("test isert test", courses_test);
            load = false;
        }
    }

}