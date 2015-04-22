package com.example.wael.partie_etudiant;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.wael.partie_etudiant.Adapters.MySubjectsAdapter;
import com.example.wael.partie_etudiant.Models.Course;
import com.example.wael.partie_etudiant.Models.JsonParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static  String[] DUMMY_CREDENTIALS;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public  ArrayList<Course> courses = new ArrayList<Course>() ;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;
    //Building Parameters
    List<NameValuePair> params = new ArrayList<NameValuePair>();
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



    }

    public View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Log.i("log_tag", " successful connexion with Database ");
        Log.i("log_tag", getString(R.string.url_base) );

        view = inflater.inflate(R.layout.fragment_courses_list, container, false);

        pb = (ProgressBar) view.findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);

        // TODO: Change Adapter to display your content
        //     mAdapter = new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
        //           android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS);
        mAdapter = new MySubjectsAdapter(getActivity(),R.layout.item_subject, courses);
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
            mListener.onFragmentInteraction(Integer.parseInt(String.valueOf(courses.get(position).getId())));

        Fragment objFragment=null;

            objFragment = ListCoursesFragment.newInstance("Courses'list","");
            if (objFragment != null) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, objFragment).commit();

            }
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


        yourJsonStringUrl=getString(R.string.url_base)+"allcourses.php";

        params.add(new BasicNameValuePair("idSubject", "5"));
        AsyncTaskParseJson dlTask = new AsyncTaskParseJson();

        dlTask.execute(LoginActivity.yourJsonStringUrl);
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

    public static int i=0;

    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {

        final String TAG = "AsyncTaskParseJson.java";

        // contacts JSONArray
        JSONArray dataJsonArr = null;

        @Override
        protected void onPreExecute() {
        //    pb.setVisibility(View.VISIBLE);
        }



        int idCourse;
        String name;
        String description;
        String returnString="";
        String url;
        String teacher;
        String dateDepo;
        int idSubject;

        @Override
        protected String doInBackground(String... arg0) {

            try {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // instantiate our json parser
                JsonParser jParser = new JsonParser();

                // get json string from url
                JSONObject json = jParser.getJSONFromUrl("http://10.0.3.2/MyProjectConnect/Etudiant/allcourses.php?idSubject=5");
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
                    idSubject = c.getInt("id_subject_course");
                    if (idCourse!=0)
                    courses.add(new Course(idCourse,name,description,
                            url,teacher,dateDepo,Integer.toString(idSubject)));


                    returnString += "\n\t"+name+":"+description+":"+url+":"+teacher+":"+dateDepo+":"+idSubject;
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return returnString;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String str) {
          //  pb.setVisibility(View.INVISIBLE);
            String[] strs;
            strs =returnString.split("\n\t");
            DUMMY_CREDENTIALS= new String[strs.length-1];
            DUMMY_CREDENTIALS=strs;
            int k=0;
            for (int d=1; d<strs.length;d++){
                DUMMY_CREDENTIALS[k]=strs[d];
                k++;
            }
            for (int j = 0; j <courses.size() ; j++) {
                Log.w("test","\n\t"+courses.get(CoursesFragment.i++).getDescription());
                j++;
            }
        }
    }



}
