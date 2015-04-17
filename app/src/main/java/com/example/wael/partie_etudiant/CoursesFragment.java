package com.example.wael.partie_etudiant;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.wael.partie_etudiant.Adapters.MyCoursesAdapter;
import com.example.wael.partie_etudiant.Models.Course;

import java.util.ArrayList;

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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<Course> courses ;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private MyCoursesAdapter mAdapter ;

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



        // TODO: Change Adapter to display your content
   //     mAdapter = new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
     //           android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS);

        courses = new ArrayList<Course>();


        Course c1 = new Course(0,"pytaghore", "math pour bacc" , "http://math.bac", "math niveau bacc etudiants stupides", "abdlbasst eben hamdouaaaaa");

        Course c2 = new Course(1,"pytaghore", "math pour bacc" , "http://math.bac", "math niveau  fjkfhfkqhfqkqjf", "imen benn aabdallah lbehi");

        Course c3 = new Course(2,"pytaghore", "math pour bacc" , "http://arab.bac", "arab", "wael ben salaha loool");

        Course c4 = new Course(3,"pytaghore", "math pour bacc" , "http://math.bac", "physique stupid us info", "hechh tfachhh kachhhhh ");

        Course c5 = new Course(0,"pytaghore", "math pour bacc" , "http://math.bac", "math niveau bacc etudiants stupides", "abdlbasst eben hamdouaaaaa");

        Course c6 = new Course(1,"pytaghore", "math pour bacc" , "http://math.bac", "math niveau  fjkfhfkqhfqkqjf", "imen benn aabdallah lbehi");

        Course c7 = new Course(2,"pytaghore", "math pour bacc" , "http://arab.bac", "arab", "wael ben salaha loool");

        Course c8 = new Course(3,"pytaghore", "math pour bacc" , "http://math.bac", "physique stupid us info", "hechh tfachhh kachhhhh ");

        Course c9 = new Course(0,"pytaghore", "math pour bacc" , "http://math.bac", "math niveau bacc etudiants stupides", "abdlbasst eben hamdouaaaaa");

        Course c10 = new Course(1,"pytaghore", "math pour bacc" , "http://math.bac", "math niveau  fjkfhfkqhfqkqjf", "imen benn aabdallah lbehi");

        Course c11 = new Course(2,"pytaghore", "math pour bacc" , "http://arab.bac", "arab", "wael ben salaha loool");

        Course c12 = new Course(3,"pytaghore", "math pour bacc" , "http://math.bac", "physique stupid us info", "hechh tfachhh kachhhhh ");

        courses.add(c1);
        courses.add(c2);
        courses.add(c3);
        courses.add(c4);

        courses.add(c5);
        courses.add(c6);
        courses.add(c7);
        courses.add(c8);

        courses.add(c9);
        courses.add(c10);
        courses.add(c11);
        courses.add(c12);

        mAdapter = new MyCoursesAdapter(getActivity(),R.layout.item_course,courses);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        // Set the adapter
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

}
