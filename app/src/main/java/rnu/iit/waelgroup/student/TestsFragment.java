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
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

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
public class TestsFragment extends Fragment  implements AbsListView.OnItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static ArrayList<Test> tests = new ArrayList<Test>() ;
    private static String yourJsonStringUrl;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static boolean load=true;
    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private ExpandableListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static TestsFragment newInstance(String param1, String param2) {
        TestsFragment fragment = new TestsFragment();
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
    public TestsFragment() {
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
        // TODO: Change Adapter to display your content
        mAdapter = new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tests, container, false);


try{
        SimpleExpandableListAdapter expListAdapter;
    expListAdapter = new SimpleExpandableListAdapter(  getActivity(),    createGroupList(),
            R.layout.group_row,new String[] { "Group Item" }, new int[] { R.id.row_name },
            createChildList(),  R.layout.child_row, new String[] {"Sub Item"},
            new int[] { R.id.grp_child}	);

    mListView = (ExpandableListView) view.findViewById(R.id.expandableListView);


    mListView.setAdapter( expListAdapter );		// setting the adapter in the list.

    }catch(Exception e){
        System.out.println("Error " + e.getMessage());
    }
    return view;
}
// TestsFragment.tests.get(i).getSubject(), TestsFragment.tests.get(i).getLevel() );

    /* Creating the Hashmap for the row */
    @SuppressWarnings("unchecked")
    private List createGroupList() {
        ArrayList result = new ArrayList();
        for( int i = 0 ; i < TestsFragment.tests.size() ; ++i ) { // 15 groups........
            HashMap m = new HashMap();
            m.put( "Group Item","Test : "+TestsFragment.tests.get(i).getSubject() +"            Level :"+ TestsFragment.tests.get(i).getLevel() ); // the key and it's value.
            result.add( m );
        }


        return (List)result;
    }

    /* creatin the HashMap for the children */
    @SuppressWarnings("unchecked")
    private List createChildList() {

        ArrayList result = new ArrayList();
        for( int i = 0 ; i < TestsFragment.tests.size() ; ++i ) { // this -15 is the number of groups(Here it's fifteen)
    	  /* each group need each HashMap-Here for each group we have 3 subgroups */
            ArrayList secList = new ArrayList();

                HashMap child = new HashMap();
                child.put( "Sub Item", "By  "+TestsFragment.tests.get(i).getTeacher() + "\n"+ "In  "+TestsFragment.tests.get(i).getDate()  +
                        "At  "+TestsFragment.tests.get(i).getSession() + "\n"+TestsFragment.tests.get(i).getCourses() );

          //  child.put( "Sub Item2", "In"+TestsFragment.tests.get(i).getDate()  );
          //  child.put( "Sub Item3", "At"+TestsFragment.tests.get(i).getSession()  );
           child.put( "Sub Item4", ""+TestsFragment.tests.get(i).getCourses()  );
                secList.add( child );

            result.add( secList );
        }
        return result;
    }
    public void  onContentChanged  () {
        System.out.println("onContentChanged");
        View emptyView = getView().findViewById(android.R.id.empty);
        mListView = (ExpandableListView)getView().findViewById(R.id.expandableListView);
        if (mListView == null) {
            throw new RuntimeException(
                    "Your content must have a ExpandableListView whose id attribute is " +
                            "'android.R.id.list'");
        }
        if (emptyView != null) {
            mListView.setEmptyView(emptyView);
        }
    }
    /* This function is called on each child click */
    public boolean onChildClick( ExpandableListView parent, View v, int groupPosition,int childPosition,long id) {
        System.out.println("Inside onChildClick at groupPosition = " + groupPosition +" Child clicked at position " + childPosition);
        return true;
    }

    /* This function is called on expansion of the group */
    public void  onGroupExpand  (int groupPosition) {
        try{
            System.out.println("Group exapanding Listener => groupPosition = " + groupPosition);
        }catch(Exception e){
            System.out.println(" groupPosition Errrr +++ " + e.getMessage());
        }
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
            ((TextView) emptyView).setText(emptyText);
        }
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
        protected String doInBackground(String... arg0) {

            try {

                // instantiate our json parser
                JsonParser jParser = new JsonParser();

                Log.i("test json object ", "test");
                // get json string from url
                JSONObject json = jParser.getJSONFromUrl(getString(R.string.url_base_student)+"/alltests.php");
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
                        tests.add(new Test(id_test,subject_test, teacher_test, level_test,session_test,
                                date_test,duration_test,courses_test));
        Log.i("test isert test", courses_test);
                    returnString += "\n\t"+subject_test+":"+level_test;
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
