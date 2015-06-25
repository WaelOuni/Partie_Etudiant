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
    private final int NEW_HOME_ITEM=0;
    private final int OLD_HOME_ITEM=1;

    private Context context;
    private List<Home> HomeList;

    public MyHomeAdapter(Context context, int resource, List<Home> objects) {
        super(context, resource, objects);
        this.context = context;
        this.HomeList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        int layoutId = -1;
        if (viewType == NEW_HOME_ITEM){layoutId= R.layout.item_home_fresh;}
        else if (viewType==OLD_HOME_ITEM) layoutId = R.layout.item_home;

        final Home  home = HomeList.get(position);
        LayoutInflater inflater = (LayoutInflater)context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(layoutId, parent, false);


        TextView tx =(TextView)view.findViewById(R.id.home_name);
        TextView tx1 =(TextView)view.findViewById(R.id.home_prof);
        TextView tx2 =(TextView)view.findViewById(R.id.home_date);
        TextView tx3 =(TextView)view.findViewById(R.id.home_desc);
        tx.setText(home.getC().getName());
        tx1.setText(home.getC().getTeacher());
        tx2.setText("downloaded at : "+home.getC().getDateDepo());
    //    tx3.setText("description");
        return view;
    }

    public int getItemViewType(int position) {
        return (position==0)?NEW_HOME_ITEM:OLD_HOME_ITEM;
    }
    public int getViewTypeCount() {
        return 2;
    }



}