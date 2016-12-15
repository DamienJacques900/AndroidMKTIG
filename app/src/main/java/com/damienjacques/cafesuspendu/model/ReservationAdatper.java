package com.damienjacques.cafesuspendu.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.BookingDAO;
import com.damienjacques.cafesuspendu.exception.DeleteBookingException;
import com.damienjacques.cafesuspendu.view.ChooseRegistrationActivity;
import com.damienjacques.cafesuspendu.view.MainActivity;
import com.damienjacques.cafesuspendu.view.ReservationCoffeeActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ReservationAdatper extends ArrayAdapter
{
    public ReservationAdatper(Context context, ArrayList<ReservationLine> history)
    {
        super(context,0,history);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final ReservationLine reservationLine = (ReservationLine) getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.reservationline,parent,false);
        }

        TextView reservationName = (TextView) convertView.findViewById(R.id.reservationName);
        TextView dateReservation = (TextView) convertView.findViewById(R.id.textReservation);

        Button consumed = (Button) convertView.findViewById(R.id.button);
        Button notConsumed = (Button) convertView.findViewById(R.id.button2);

        final Integer idBooking = reservationLine.getIdBooking();

        consumed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                BookingDAO bookingDAO = new BookingDAO();
                Boolean consumedBool = true;
                try
                {
                    System.out.println("Valeur delete : "+consumedBool+" "+idBooking);
                    bookingDAO.deleteBooking(idBooking, consumedBool);
                }
                catch(Exception e)
                {
                    Toast.makeText(v.getContext(), "Erreur de connexion aux données durant la tentative de suppression de la réservation", Toast.LENGTH_LONG).show();
                }
            }
        });

        notConsumed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                BookingDAO bookingDAO = new BookingDAO();
                Boolean consumedBool = false;
                try
                {
                    System.out.println("Valeur delete : "+consumedBool+" "+idBooking);
                    bookingDAO.deleteBooking(idBooking, consumedBool);
                }
                catch(Exception e)
                {
                    Toast.makeText(v.getContext(), "Erreur de connexion aux données durant la tentative de suppression de la réservation", Toast.LENGTH_LONG).show();
                }
            }
        });

        reservationName.setText(reservationLine.getReservationName());
        dateReservation.setText(reservationLine.getReservationDate());

        return convertView;
    }

    public class LoadBookingDelete extends AsyncTask<String, Void, ArrayList<Booking>>
    {
        Exception exception;

        @Override
        protected ArrayList<Booking> doInBackground(String... params)
        {
            BookingDAO bookingDAO = new BookingDAO();
            ArrayList<Booking> bookings = new ArrayList<>();
            try
            {
                bookings = bookingDAO.getAllBooking();
            }
            catch(Exception e)
            {
                exception = e;
            }

            return bookings;
        }

        //***********************COMMENTAIRE****************************
        //Permet d'executer quelque chose après le chargement des données
        //**************************************************************
        @Override
        protected void onPostExecute(ArrayList<Booking> bookings)
        {

        }
    }
}
