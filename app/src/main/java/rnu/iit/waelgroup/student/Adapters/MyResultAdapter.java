package rnu.iit.waelgroup.student.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import rnu.iit.waelgroup.student.Models.Course;
import rnu.iit.waelgroup.student.R;

/**
 * Created by Wael on 16/04/2015.
 */
public class MyResultAdapter extends ArrayAdapter<Course> {

    private Context context;
    private List<Course> CourseList;

    public MyResultAdapter(Context context, int resource, List<Course> objects) {
        super(context, resource, objects);
        this.context = context;
        this.CourseList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        final Course course = CourseList.get(position);
        View view = inflater.inflate(R.layout.item_subject, parent, false);

        TextView tx =(TextView)view.findViewById(R.id.subject_name);
        TextView tx2 =(TextView)view.findViewById(R.id.teacher_name);

        tx.setText(course.getSubject());
        tx2.setText(course.getTeacher());

        return view;
    }
}