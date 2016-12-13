package com.damienjacques.cafesuspendu.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.damienjacques.cafesuspendu.R;

import java.util.ArrayList;

/**
 * Created by Damien Jacques on 13-12-16.
 */

public class HistoryAdapter extends ArrayAdapter
{
    public HistoryAdapter(Context context, ArrayList<HistoryLine> history)
    {
        super(context,0,history);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        HistoryLine historyLine = (HistoryLine) getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.historyline,parent,false);
        }

        TextView nameCoffee = (TextView) convertView.findViewById(R.id.CoffeeNameHistory);
        TextView descriptionPromo = (TextView) convertView.findViewById(R.id.textHistory);

        nameCoffee.setText(historyLine.getCoffeeName());
        descriptionPromo.setText(historyLine.getDescription());

        return convertView;
    }
}
