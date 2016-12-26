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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.BookingDAO;
import com.damienjacques.cafesuspendu.exception.DeleteBookingException;
import com.damienjacques.cafesuspendu.view.ChooseRegistrationActivity;
import com.damienjacques.cafesuspendu.view.MainActivity;
import com.damienjacques.cafesuspendu.view.ReceptionClientActivity;
import com.damienjacques.cafesuspendu.view.ReceptionCoffeeActivity;
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


        consumed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Boolean consumedBool = true;
                Integer idBooking = reservationLine.getIdBooking();
                SharedPreferences pref = getContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                //***********************COMMENTAIRE****************************
                //Permet d'éditer le sharePreference
                //**************************************************************
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("idBooking", idBooking);
                editor.putBoolean("consumedBool", consumedBool);
                editor.commit();
                new LoadBooking().execute();
            }
        });

        notConsumed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Boolean consumedBool = false;

                Integer idBooking = reservationLine.getIdBooking();

                SharedPreferences pref = getContext().getSharedPreferences("MyPref", MODE_PRIVATE);

                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("idBooking", idBooking);
                editor.putBoolean("consumedBool", consumedBool);
                editor.commit();
                new LoadBooking().execute();

            }
        });

        reservationName.setText(reservationLine.getReservationName());
        dateReservation.setText(reservationLine.getReservationDate());

        return convertView;
    }

    public class LoadBooking extends AsyncTask<String, Void, ArrayList<Booking>>
    {
        Exception exception;

        @Override
        protected ArrayList<Booking> doInBackground(String... params)
        {
            BookingDAO bookingDAO = new BookingDAO();
            ArrayList<Booking> bookings = new ArrayList<>();
            try
            {
                SharedPreferences pref = getContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                String token = pref.getString("token",null);
                Integer idBooking = pref.getInt("idBooking",0);
                Boolean consumedBool = pref.getBoolean("consumedBool",false);
                bookingDAO.deleteBooking(idBooking, consumedBool,token);
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
            if (exception != null)
            {
                Toast.makeText(getContext(), "Erreur lors de la suppression", Toast.LENGTH_LONG).show();
                System.out.println(exception);
            }
            else
            {
                SharedPreferences pref = getContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                Boolean consumedBool = pref.getBoolean("consumedBool",false);
                if(consumedBool)
                    Toast.makeText(getContext(), "La suppression c'est bien effectué car le sans-abri a pris son café", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getContext(), "La suppression c'est bien effectué car personne n'est venu prendre le café", Toast.LENGTH_LONG).show();
                /*Intent intentReservation = new Intent(getContext(),ReservationCoffeeActivity.class);
                startActivity(intentReservation);*/
            }
        }
    }
}
