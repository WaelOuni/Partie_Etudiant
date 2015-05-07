
package rnu.iit.waelgroup.student;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rnu.iit.waelgroup.student.Adapters.MySubjectsAdapter;
import rnu.iit.waelgroup.student.Models.Course;
import rnu.iit.waelgroup.student.Models.JsonParser;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class CoursesFragment extends Fragment implements AbsListView.OnItemClickListener {

    private static boolean load=true;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static ArrayList<Course> courses = new ArrayList<Course>() ;


    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;
    //Building Parameters
  //  List<NameValuePair> params = new ArrayList<NameValuePair>();
    public ProgressBar pb;
    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private MySubjectsAdapter mAdapter ;
    public static String yourJsonStringUrl;

    // TODO: Rename and change types of parameters
    public static CoursesFragment newInstance(String param1, String param2) {
        CoursesFragment fragment = new CoursesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CoursesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // Set the adapter

        yourJsonStringUrl=getString(R.string.url_base_student)+"allcourses.php";

        if(load) {
            AsyncTaskParseJson dlTask = new AsyncTaskParseJson();

            dlTask.execute(LoginActivity.yourJsonStringUrl);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Log.i("log_tag", " successful connexion with Database ");

        view = inflater.inflate(R.layout.fragment_courses_list, container, false);
        Log.i("adapter test", courses.get(2).getDescription());
        // TODO: Change Adapter to display your content
        //     mAdapter = new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
        //           android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS);
        mAdapter = new MySubjectsAdapter(getActivity(),R.layout.item_subject, EspaceEtudiant.subjects);
        mListView = (AbsListView) view.findViewById(android.R.id.list);
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
           // mListener.onFragmentInteraction(Integer.parseInt(String.valueOf(EspaceEtudiant.subjects.get(position).getId())));
            Intent i = new Intent(getActivity(), ListCourses.class );
            i.putExtra("subject", EspaceEtudiant.subjects.get(position).getSubject_name());
            startActivity(i);
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
            ((TextView) emptyView).setText(emptyText);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(int id);
    }


    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {

        final String TAG = "AsyncTaskParseJson.java";
        JSONArray dataJsonArr = null;

        @Override
        protected void onPreExecute() {

        }

        int idCourse;
        String name;
        String description;
        String returnString="";
        String url;
        String teacher;
        String dateDepo;
        String subject;

        @Override
        protected String doInBackground(String... arg0) {

            try {

                // instantiate our json parser
                JsonParser jParser = new JsonParser();

                Log.i("test json object ", "test");
                // get json string from url
                JSONObject json = jParser.getJSONFromUrl(getString(R.string.url_base_student)+"/allcourses.php");
                dataJsonArr= json.getJSONArray("auth");
  //              Log.i("test json object ", "test");
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
                    if (idCourse!=0)
                        courses.add(new Course(idCourse,name,description, url,teacher,dateDepo,subject));

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
        }
    }



}
