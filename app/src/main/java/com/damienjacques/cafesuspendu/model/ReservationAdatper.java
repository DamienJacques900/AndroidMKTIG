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

public class ReservationAdatper extends ArrayAdapter
{
    public ReservationAdatper(Context context, ArrayList<ReservationLine> history)
    {
        super(context,0,history);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ReservationLine reservationLine = (ReservationLine) getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.reservationline,parent,false);
        }

        TextView reservationName = (TextView) convertView.findViewById(R.id.reservationName);
        TextView dateReservation = (TextView) convertView.findViewById(R.id.textReservation);

        reservationName.setText(reservationLine.getReservationName());
        dateReservation.setText(reservationLine.getReservationDate());

        return convertView;
    }
}
