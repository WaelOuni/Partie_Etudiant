package rnu.iit.waelgroup.student.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import rnu.iit.waelgroup.student.Models.Resultat;
import rnu.iit.waelgroup.student.R;

/**
 * Created by Wael on 16/04/2015.
 */
public class MyResultAdapter extends ArrayAdapter<Resultat> {

    private Context context;
    private List<Resultat> ResultatList;

    public MyResultAdapter(Context context, int resource, List<Resultat> objects) {
        super(context, resource, objects);
        this.context = context;
        this.ResultatList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        final Resultat resultat = ResultatList.get(position);
        View view = inflater.inflate(R.layout.item_resultat, parent, false);

        TextView tx =(TextView)view.findViewById(R.id.nomStudent);
        TextView tx2 =(TextView)view.findViewById(R.id.mention);

        tx.setText(String.valueOf(resultat.getCin()));
        tx2.setText(resultat.getMention());

        return view;
    }
}