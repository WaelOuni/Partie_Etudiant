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
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rnu.iit.waelgroup.student.Adapters.MyResultAdapter;
import rnu.iit.waelgroup.student.Models.JsonParserSelectedQuery;
import rnu.iit.waelgroup.student.Models.Resultat;
import rnu.iit.waelgroup.student.dummy.DummyContent;
import rnu.iit.waelgroup.student.Util.OnlineChecker;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class ResultsFragment extends Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static ArrayList<Resultat> results = new ArrayList<Resultat>();
    private static boolean load = true;
    private static ArrayList<NameValuePair> data;
    private static String yourJsonStringUrl;
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
    private MyResultAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ResultsFragment() {
    }

    // TODO: Rename and change types of parameters
    public static ResultsFragment newInstance(String param1, String param2) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        yourJsonStringUrl=getString(R.string.url_base_student)+"resultattest.php";
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        OnlineChecker oc = new OnlineChecker();

        if (load) {
            if ( oc.isOnline(getActivity())) {
            data = new ArrayList<NameValuePair>();
            data.add(new BasicNameValuePair("cin_etudiant", EspaceEtudiant.cin_key));
            AsyncTaskParseJson dlTask = new AsyncTaskParseJson();
            dlTask.execute();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(getActivity(),"Network isn't available", Toast.LENGTH_LONG).show();
        }
    }

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
        String nom;
        String prenom;
        int numrepjust;
        int numrepfalse;
        String mention;
        int rapidite;
        String subject_test;
        String returnString = "";

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... arg0) {
            try {
                // instantiate our json parser `cin_etudiant`, `numrepjust`, `numrepfalse`, `mention`, `rapidite`, ``
                JsonParserSelectedQuery jParser = new JsonParserSelectedQuery();
                Log.i("test json object ", "test");
                // get json string from url
                JSONObject json = jParser.getJSONFromUrl(getString(R.string.url_base_student) + "/resultattestSecond.php", data);
                dataJsonArr = json.getJSONArray("auth");
                Log.i("test json object ", "test");
                for (int i = 0; i < dataJsonArr.length(); i++) {
                    JSONObject c = dataJsonArr.getJSONObject(i);
                    // Storing each json item in variable
                    nom = c.getString("nom");
                    prenom = c.getString("prenom");
                    numrepjust = c.getInt("numrepjust");
                    numrepfalse = c.getInt("numrepfalse");
                    mention = c.getString("mention");
                    rapidite = c.getInt("rapidite");
                    subject_test = c.getString("subject_test");
                    // if (id_test!=0)
                    results.add(new Resultat(nom, prenom, numrepjust, numrepfalse, mention, rapidite, subject_test));
                    //   Log.i("test isert test", courses_test);
                    returnString += "";
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return returnString;
        }

        @Override
        protected void onPostExecute(String str) {
            Log.i("cccc",""+ str);
            // Log.i("test isert test", courses_test);
            load = false;
            if (results.isEmpty()){
                Toast.makeText(getActivity(), "any results for you !",Toast.LENGTH_LONG).show();
            }else
            {

                mAdapter = new MyResultAdapter(getActivity(),
                        R.layout.item_resultat, results);
                ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
                // Set the adapter
            }
        }
    }


}
