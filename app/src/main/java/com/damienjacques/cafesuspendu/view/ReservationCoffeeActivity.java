package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

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
        setContentView(R.layout.activity_reservationcoffee);
        creationLayout();
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
            creationLayout();
        }
        else
        {
            setContentView(R.layout.activity_reservationcoffee);
            creationLayout();
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

            SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            for(int i = 1; i <= bookingsCoffee.size(); i++)
            {
                editor.putString("nameOffering"+i, bookingsCoffee.get(i-1).getName());
                editor.putString("date"+i, bookingsCoffee.get(i-1).getDateBooking().toString());
            }
            editor.putInt("SizeBooking",bookingsCoffee.size());
            editor.commit();
        }
    }

    public void creationLayout()
    {
        new LoadBooking().execute();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

        ListView listBooking= (ListView) findViewById(R.id.listBooking);

        String[] listItemsBookings = new String[pref.getInt("SizeBooking",0)];

        for(int i = 1; i <= pref.getInt("SizeBooking",0); i++){

            String coffeeName = pref.getString("nameOffering"+i,null);
            coffeeName += "      "+pref.getString("date"+i,null);

            listItemsBookings[i-1] = coffeeName;
        }
// 4
        ArrayAdapter adapterCoffee = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItemsBookings);
        listBooking.setAdapter(adapterCoffee);
    }
}
