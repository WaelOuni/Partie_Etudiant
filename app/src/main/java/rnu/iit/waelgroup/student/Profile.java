package rnu.iit.waelgroup.student;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rnu.iit.waelgroup.student.Models.Etudiant;
import rnu.iit.waelgroup.student.Models.JsonParser;
import rnu.iit.waelgroup.student.Models.JsonParserUpdateBD;
import rnu.iit.waelgroup.student.util.SendMail;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Profile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static ArrayList<NameValuePair> data, dataUpdate;
    private static Boolean update = false;
    private Etudiant etudiant = null;
    private AsyncTaskParseJson dlTask;
    private View linear;
    private ProgressBar pb;
    private TextView npValue, mailValue, passwordValue, levelValue, phoneValue, classValue;
    private EditText mailEdit, passwordEdit, levelEdit, phoneEdit, classEdit;
    private String npStr, mailStr, passwordStr, levelStr, phoneStr, classStr;
    private Button updateBtn;
    private ImageView btn;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private boolean load = true;

    private OnFragmentInteractionListener mListener;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
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
        loading();
    }

    private void loading() {
        if (load) {
            data = new ArrayList<NameValuePair>();
            data.add(new BasicNameValuePair("cin_etudiant", EspaceEtudiant.cin_key));
            GetDataAsyncTask dlTask = new GetDataAsyncTask();
            dlTask.execute(LoginActivity.yourJsonStringUrl);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void getInitData() {
        npValue.setText(etudiant.getPrenom() + " " + etudiant.getNom());
        mailValue.setText(etudiant.getEmail());
        passwordValue.setText(etudiant.getPassword());
        levelValue.setText(etudiant.getNiveau());
        phoneValue.setText(etudiant.getTelephone());
        classValue.setText(Integer.toString(etudiant.getClasse()));
        mailEdit.setText(etudiant.getEmail());
        passwordEdit.setText(etudiant.getPassword());
        levelEdit.setText(etudiant.getNiveau());
        phoneEdit.setText(etudiant.getTelephone());
        classEdit.setText(Integer.toString(etudiant.getClasse()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        getViews(view);
        return view;
    }

    private void getViews(View view) {
        linear = view.findViewById(R.id.profilestudentImg);
        pb = (ProgressBar) view.findViewById(R.id.pbUpdateProfile);
        npValue = (TextView) view.findViewById(R.id.NamePrenameTxt);
        mailValue = (TextView) view.findViewById(R.id.mailValue);
        passwordValue = (TextView) view.findViewById(R.id.passwordValue);
        levelValue = (TextView) view.findViewById(R.id.levelValue);
        phoneValue = (TextView) view.findViewById(R.id.phoneValue);
        classValue = (TextView) view.findViewById(R.id.classValue);
        mailEdit = (EditText) view.findViewById(R.id.mailEdit);
        passwordEdit = (EditText) view.findViewById(R.id.passwordEdit);
        levelEdit = (EditText) view.findViewById(R.id.levelEdit);
        phoneEdit = (EditText) view.findViewById(R.id.phoneEdit);
        classEdit = (EditText) view.findViewById(R.id.classEdit);
        btn = (ImageView) view.findViewById(R.id.mytof);
        updateBtn = (Button) view.findViewById(R.id.updateProfile);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int id) {
        if (mListener != null) {
            mListener.onFragmentInteraction(id);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // getInitData();
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (update) {
                    showProcess();
                    changeData();
                    load = true;
                    loading();
                    update = false;
                } else {

                    updatingProcess();
                    update = true;

                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity().getBaseContext(), "Hello student...", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void changeData() {
        dataUpdate = new ArrayList<NameValuePair>();
        dataUpdate.add(new BasicNameValuePair("cin_etudiant", Integer.toString(etudiant.getCin())));
        mailStr = mailEdit.getText().toString();
        passwordStr = passwordEdit.getText().toString();
        levelStr = levelEdit.getText().toString();
        phoneStr = phoneEdit.getText().toString();
        classStr = classEdit.getText().toString();
        dataUpdate.add(new BasicNameValuePair("email", mailStr));
        dataUpdate.add(new BasicNameValuePair("password", passwordStr));
        dataUpdate.add(new BasicNameValuePair("niveau", levelStr));
        dataUpdate.add(new BasicNameValuePair("telephone", phoneStr));
        dataUpdate.add(new BasicNameValuePair("num_classe", classStr));
        dlTask = new AsyncTaskParseJson();
        dlTask.execute(LoginActivity.yourJsonStringUrl);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            SendMail.createEmailIntent(getActivity(), mailStr, "PassExam : profile updated ", "Hi! \n" + npStr + " You are updated your profile Student succefully ");
        } catch (Exception ex) {
            Log.i("Mail send Exception :", ex.toString());
        }
    }

    private void showProcess() {
        mailValue.setVisibility(View.VISIBLE);
        passwordValue.setVisibility(View.VISIBLE);
        levelValue.setVisibility(View.VISIBLE);
        phoneValue.setVisibility(View.VISIBLE);
        classValue.setVisibility(View.VISIBLE);

//----------------------------------------------------------\\

        mailEdit.setVisibility(View.GONE);
        passwordEdit.setVisibility(View.GONE);
        levelEdit.setVisibility(View.GONE);
        phoneEdit.setVisibility(View.GONE);
        classEdit.setVisibility(View.GONE);
        updateBtn.setText("Make changes");
    }

    private void updatingProcess() {
        mailValue.setVisibility(View.INVISIBLE);
        passwordValue.setVisibility(View.INVISIBLE);
        levelValue.setVisibility(View.INVISIBLE);
        phoneValue.setVisibility(View.INVISIBLE);
        classValue.setVisibility(View.INVISIBLE);

        mailEdit.setVisibility(View.VISIBLE);
        passwordEdit.setVisibility(View.VISIBLE);
        levelEdit.setVisibility(View.VISIBLE);
        phoneEdit.setVisibility(View.VISIBLE);
        classEdit.setVisibility(View.VISIBLE);

        updateBtn.setText("Update");
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

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            linear.setVisibility(show ? View.GONE : View.VISIBLE);
            linear.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    linear.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            pb.setVisibility(show ? View.VISIBLE : View.GONE);
            pb.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    pb.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            pb.setVisibility(show ? View.VISIBLE : View.GONE);
            linear.setVisibility(show ? View.GONE : View.VISIBLE);
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

    private class AsyncTaskParseJson extends AsyncTask<String, String, String> {

        final String TAG = "AsyncTaskParseJson.java";
        JSONArray dataJsonArr = null;

        @Override
        protected void onPreExecute() {
            updateBtn.setEnabled(false);
            showProgress(true);
        }

        @Override
        protected String doInBackground(String... arg0) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }

            try {

                Log.i(dataUpdate.get(3).getName(), dataUpdate.get(3).getValue());
                // instantiate our json parser
                JsonParserUpdateBD jParser = new JsonParserUpdateBD();
                // get json string from url
                jParser.setUrlFromJson(getString(R.string.url_base_student) + "/updateProfile.php", dataUpdate);
            } catch (Exception e) {
                Log.e("error update table", e.getMessage());
                showProgress(false);
            }
            return "";
        }

        @Override
        protected void onPostExecute(String str) {
            //load = false;
            getInitData();
            dlTask = null;
            showProgress(false);
            Toast.makeText(getActivity(), "Update profile with success", Toast.LENGTH_LONG).show();
            updateBtn.setEnabled(true);
        }
    }

    public class GetDataAsyncTask extends AsyncTask<String, String, String> {
        final String TAG = "AsyncTaskParseJson.java";
        JSONArray dataJsonArr = null;
        private int cin;
        private String nom;
        private String prenom;
        private int inscription;
        private String genre;
        private String email;
        private String password;
        private String niveau;
        private String telephone;
        private int num_classe;

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... arg0) {

            //`cin_etudiant`, `nom`, `prenom`, `inscription`, `genre`, `email`, `password`, `niveau`, `telephone`, `num_classe`
            try {

                // instantiate our json parser
                JsonParser jParser = new JsonParser();
                // get json string from url
                JSONObject json = jParser.getJSONFromUrl(getString(R.string.url_base_student) + "/etudiants.php", data);
                dataJsonArr = json.getJSONArray("auth");
                for (int i = 0; i < dataJsonArr.length(); i++) {
                    JSONObject c = dataJsonArr.getJSONObject(i);
                    // Storing each json item in variable
                    cin = c.getInt("cin_etudiant");
                    nom = c.getString("nom");
                    prenom = c.getString("prenom");
                    inscription = c.getInt("inscription");
                    genre = c.getString("genre");
                    email = c.getString("email");
                    password = c.getString("password");
                    niveau = c.getString("niveau");
                    telephone = c.getString("telephone");
                    num_classe = c.getInt("num_classe");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String str) {
            etudiant = new Etudiant(cin, nom, prenom, inscription, genre, email, password, niveau, telephone, num_classe);
            load = false;
            getInitData();
        }
    }
}
