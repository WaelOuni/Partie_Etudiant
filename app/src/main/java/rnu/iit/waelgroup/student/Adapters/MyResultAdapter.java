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
        TextView tx1 = (TextView) view.findViewById(R.id.subjectTest);
        TextView tx2 = (TextView) view.findViewById(R.id.numRepJustes);
        TextView tx3 = (TextView) view.findViewById(R.id.mentionResult);
        ImageView imResult = (ImageView) view.findViewById(R.id.resultIcon);
        int questsNumber = resultat.getNumrepjust() + resultat.getNumrepfalse();
        if (resultat.getNumrepjust() > resultat.getNumrepfalse()) {
            imResult.setImageResource(R.drawable.success);
        } else
            imResult.setImageResource(R.drawable.faiulre);
        tx.setText(resultat.getNom() + " " + resultat.getPrenom());
        tx1.setText(resultat.getSubject_test());
        tx2.setText(resultat.getNumrepjust() + " / " + questsNumber);
        tx3.setText(resultat.getMention());
        return view;
    }
}