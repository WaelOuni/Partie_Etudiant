package com.example.wael.partie_etudiant.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.wael.partie_etudiant.Models.Course;
import com.example.wael.partie_etudiant.R;

import java.util.List;

/**
 * Created by Wael on 16/04/2015.
 */
public class MyCoursesAdapter  extends ArrayAdapter<Course> {

    private Context context;
    private List<Course> courseList;

    public MyCoursesAdapter(Context context, int resource, List<Course> objects) {
        super(context, resource, objects);
        this.context = context;
        this.courseList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        final Course course = courseList.get(position);
        View view = inflater.inflate(R.layout.item_course, parent, false);
      /*  TextView tx =(TextView)view.findViewById(R.id.textView);
        TextView tx2 =(TextView)view.findViewById(R.id.textView2);
        tx.setText(course.getName());
        tx2.setText(course.getDescription());

        Button bt = (Button)view.findViewById(R.id.button2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("test inside item", course.getName().toString());
            }
        });
        ImageView im = (ImageView)view.findViewById(R.id.imageView);
        im.setImageBitmap(course.getBitmap());*/


     //   return view;
        return null;
    }
}