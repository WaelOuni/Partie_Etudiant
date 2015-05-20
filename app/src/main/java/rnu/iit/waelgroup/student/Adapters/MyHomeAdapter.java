package rnu.iit.waelgroup.student.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import rnu.iit.waelgroup.student.Models.Home;
import rnu.iit.waelgroup.student.R;


/**
 * Created by Wael on 16/04/2015.
 */
public class MyHomeAdapter extends ArrayAdapter<Home> {

    private Context context;
    private List<Home> HomeList;

    public MyHomeAdapter(Context context, int resource, List<Home> objects) {
        super(context, resource, objects);
        this.context = context;
        this.HomeList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final Home  home = HomeList.get(position);
        View view = inflater.inflate(R.layout.item_home, parent, false);

        TextView tx =(TextView)view.findViewById(R.id.home_name);
        TextView tx1 =(TextView)view.findViewById(R.id.home_prof);
        TextView tx2 =(TextView)view.findViewById(R.id.home_date);

        tx.setText(home.getC().getName());
        tx1.setText(home.getC().getTeacher());
        tx2.setText("downloaded at : "+home.getC().getDateDepo());

        return view;
    }
}