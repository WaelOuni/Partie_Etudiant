package rnu.iit.waelgroup.student.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import rnu.iit.waelgroup.student.Models.Subject;
import rnu.iit.waelgroup.student.R;

/**
 * Created by Wael on 16/04/2015.
 */
public class MySubjectsAdapter extends ArrayAdapter<Subject> {

    private Context context;
    private List<Subject> SubjectList;

    public MySubjectsAdapter(Context context, int resource, List<Subject> objects) {
        super(context, resource, objects);
        this.context = context;
        this.SubjectList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        final Subject subject = SubjectList.get(position);
        View view = inflater.inflate(R.layout.item_subject, parent, false);
        ImageView img = (ImageView) view.findViewById(R.id.imageView);

        TextView tx4 = (TextView) view.findViewById(R.id.subject_icon);
        TextView tx = (TextView) view.findViewById(R.id.subject_name);
        TextView tx2 = (TextView) view.findViewById(R.id.teacher_name);

        tx4.setText(subject.getSubject_name().substring(0,2));
        tx.setText(subject.getSubject_name());
        tx2.setText(subject.getSubject_teacher());

       /* switch (subject.getSubject_name()) {

            case "math":
                img.setImageResource(R.drawable.math);
                break;

            case "physics":

                img.setImageResource(R.drawable.basicphysics);
                break;

            case "android":

                img.setImageResource(R.drawable.ic_launcher);
                break;

            case "french":

                img.setImageResource(R.drawable.french);
                break;

            case "english":

                img.setImageResource(R.drawable.english);
                break;
        }
*/

        return view;
    }
}