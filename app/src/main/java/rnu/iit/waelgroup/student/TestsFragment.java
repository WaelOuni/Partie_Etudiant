package rnu.iit.waelgroup.student;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
public class TestsFragment extends Fragment  implements AbsListView.OnItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static ArrayList<Test> tests = new ArrayList<Test>() ;
    private static String yourJsonStringUrl;
    private static boolean load = true;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

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
        yourJsonStringUrl=getString(R.string.url_base_student)+"alltests.php";

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

        Log.i("sbe77777777", tests.get(1).getCourses());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tests_list, container, false);
/*
        Log.i("log_tag", " successful connexion with Database ");

        mAdapter = new MyTestAdapter(getActivity(), tests);
       // mAdapter.getContentView()
        mListView = (AbsListView) view.findViewById(R.id.expandableListView);

        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(mAdapter);

        Log.i("ccccccccccccccccccccc", "test");
        alphaInAnimationAdapter.setAbsListView(mListView);
      //  mAdapter.(mListView);
        ((AdapterView<ListAdapter>) mListView).setAdapter(alphaInAnimationAdapter);
        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);
*/

        /*
         mExpandableListItemAdapter = new MyExpandableListItemAdapter(this);
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(mExpandableListItemAdapter);
        alphaInAnimationAdapter.setAbsListView(getListView());

        assert alphaInAnimationAdapter.getViewAnimator() != null;
        alphaInAnimationAdapter.getViewAnimator().setInitialDelayMillis(INITIAL_DELAY_MILLIS);

        getListView().setAdapter(alphaInAnimationAdapter);

        * */


      /*  mListView = (ListView)  view.findViewById(R.id.expandableListView);



        mAdapter = new MyExpandableListItemAdapter(getActivity());
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(mAdapter);
   //    Log.i("test", "jjjjj");
        alphaInAnimationAdapter.setAbsListView(mListView);
        //assert alphaInAnimationAdapter.getViewAnimator() != null;
        alphaInAnimationAdapter.getViewAnimator().setInitialDelayMillis(500);
       Log.i("test", "jjjjj");

        mListView.setAdapter(alphaInAnimationAdapter);*/
        Log.i("test", "jjjjj");

        // mListView.setOnItemClickListener(this);

        return view;
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

    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {
        final String TAG = "AsyncTaskParseJson.java";
        JSONArray dataJsonArr = null;
        int id_test;
        String subject_test;
        String teacher_test;
        String level_test;
        int session_test;
        String returnString="";
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
                dataJsonArr= json.getJSONArray("auth");
                Log.i("test json object ", "test");
                for(int i=0;i<dataJsonArr.length();i++){
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
                    returnString += "\n\t"+subject_test+":"+level_test;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return returnString;
        }


        @Override
        protected void onPostExecute(String str) {
            Log.i("test isert test", courses_test);
            load = false;
        }
    }

}
