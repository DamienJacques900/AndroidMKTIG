package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.BookingDAO;
import com.damienjacques.cafesuspendu.model.Booking;

import java.util.ArrayList;


public class ReservationCoffeeActivity extends MenuCoffeeActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        new LoadBooking().execute();
        setContentView(R.layout.activity_reservationcoffee);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        this.getMenuInflater().inflate(R.menu.menu_coffee,menu);
        return true;
    }

    @Override
    public void goToReceptionCoffee()
    {
        Intent intentReception = new Intent(ReservationCoffeeActivity.this,ReceptionCoffeeActivity.class);
        startActivity(intentReception);
    }

    @Override
    public void goToCoffee()
    {
        Intent intentCoffee = new Intent(ReservationCoffeeActivity.this,OfferCoffeeActivity.class);
        startActivity(intentCoffee);
    }

    @Override
    public void goToReservation()
    {
        Intent intentReservation = new Intent(ReservationCoffeeActivity.this,ReservationCoffeeActivity.class);
        startActivity(intentReservation);
    }

    @Override
    public void goToOptionCoffee()
    {
        Intent intentOption = new Intent(ReservationCoffeeActivity.this,OptionCoffeeActivity.class);
        startActivity(intentOption);
    }

    @Override
    public void goToDisconaction()
    {
        Intent intentDisconnect = new Intent(ReservationCoffeeActivity.this,MainActivity.class);
        startActivity(intentDisconnect);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.activity_reservationcoffee);
        }
        else
        {
            setContentView(R.layout.activity_reservationcoffee);
        }
    }

    private class LoadBooking extends AsyncTask<String, Void, ArrayList<Booking>>
    {
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
                return bookings;
            }

            return bookings;
        }

        @Override
        protected void onPostExecute(ArrayList<Booking> bookings)
        {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            ArrayList<Booking> bookingsCoffee = new ArrayList<Booking>();

            System.out.println("Taille booking : "+bookings.size());
            for(int i = 0 ; i < bookings.size(); i++)
            {
                if(bookings.get(i).getUserCafe().getUserName().equals(pref.getString("userName",null)))
                {
                    bookingsCoffee.add(bookings.get(i));
                }
            }

            Log.i("ValeurCoffeeBok", bookingsCoffee.toString());
        }
    }
}
