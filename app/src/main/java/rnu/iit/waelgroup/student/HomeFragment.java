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
import android.widget.ListAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rnu.iit.waelgroup.student.Adapters.MyHomeAdapter;
import rnu.iit.waelgroup.student.Models.Course;
import rnu.iit.waelgroup.student.Models.Home;
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
public class HomeFragment extends Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static ArrayList<Home> homes = new ArrayList<Home>();
    static ArrayList<Course> courses;
    static ArrayList<Test> tests;
    private static boolean load=true;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private MyHomeAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HomeFragment() {
    }

    // TODO: Rename and change types of parameters
    public static HomeFragment newInstance(String param1, String param2) {

        courses = new ArrayList<Course>();
        tests = new ArrayList<Test>();
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        if(load) {
            AsyncTaskParseJson dlTask = new AsyncTaskParseJson();
            dlTask.execute(LoginActivity.yourJsonStringUrl);
        }

        // TODO: Change Adapter to display your content
        mAdapter = new MyHomeAdapter(getActivity(),R.layout.item_home, homes);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Set the adapter
        mListView = (AbsListView) view.findViewById(R.id.list_home);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

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

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {

        View emptyView = mListView.getEmptyView();
        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText("list is empty");
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(int id);
    }


    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {
        final String TAG = "AsyncTaskParseJson.java";
        JSONArray dataJsonArr = null;
        int idCourse;
        String name;
        String description;
        String returnString="";
        String url;
        String teacher;
        String dateDepo;
        String subject;
        int id_test;
        String level_test;
        String session_test;
        String date_test;
        int duration_test;
        String courses_test;
        int numquestchoisis;

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
                JSONObject json = jParser.getJSONFromUrl(getString(R.string.url_base_student) + "/allcourses.php", null);
                dataJsonArr= json.getJSONArray("auth");
                for(int i=0;i<dataJsonArr.length();i++){
                    JSONObject c = dataJsonArr.getJSONObject(i);
                    // Storing each json item in variable
                    idCourse = c.getInt("id_course");
                    name = c.getString("nom_course");
                    description = c.getString("description_course");
                    url = c.getString("url_course");
                    teacher = c.getString("teacher_course");
                    dateDepo = c.getString("date_depo_course");
                    subject = c.getString("name_subject");
/*                    id_test= c.getInt("id_test");
                    level_test= c.getString("level_test");
                    session_test= c.getString("session_test");
                    date_test= c.getString("date_test");
                    duration_test= c.getInt("duration_test");
                    courses_test= c.getString("courses_test");
                    numquestchoisis= c.getInt("numquestchoisis");*/
                    //`id_test`, `subject_test`, `teacher_test`, `level_test`, `session_test`, `date_test`, `duration_test`, `courses_test`, `numquestchoisis`
                    if (idCourse!=0)
                        courses.add(new Course(idCourse,name,description, url,teacher,dateDepo,subject));
                 /*   else
                        tests.add(new Test(id_test,subject,teacher,level_test,Integer.getInteger(session_test),date_test,Integer.toString(duration_test),courses_test));*/
                    returnString += "\n\t"+name+":"+description+":"+url+":"+teacher+":"+dateDepo+":"+subject;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return returnString;
        }

        @Override
        protected void onPostExecute(String str) {
            load = false;
            for (Course c : courses)
            homes.add(new Home(c,null));
        }
    }

}
