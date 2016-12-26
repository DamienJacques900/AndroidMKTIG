package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.BookingDAO;
import com.damienjacques.cafesuspendu.model.Booking;
import com.damienjacques.cafesuspendu.model.ReservationAdatper;
import com.damienjacques.cafesuspendu.model.ReservationLine;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class ReservationCoffeeActivity extends MenuCoffeeActivity
{
    private ProgressBar spinner;

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

    //***********************COMMENTAIRE****************************
    //Redéfinition des méthodes pour correspondre à la vue actuelle
    //**************************************************************
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

    //***********************COMMENTAIRE****************************
    //Permet de gérer le changement d'orientation
    //**************************************************************
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

    //***********************COMMENTAIRE****************************
    //Permet de charger les données de l'API
    //**************************************************************
    public class LoadBooking extends AsyncTask<String, Void, ArrayList<Booking>>
    {
        Exception exception;
        Exception ioException;

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

        @Override
        protected ArrayList<Booking> doInBackground(String... params)
        {
            BookingDAO bookingDAO = new BookingDAO();
            ArrayList<Booking> bookings = new ArrayList<>();
            try
            {
                bookings = bookingDAO.getAllBooking(pref.getString("token",null));
            }
            catch(IOException e)
            {
                ioException = e;
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
                Toast.makeText(ReservationCoffeeActivity.this, "Erreur de connexion", Toast.LENGTH_LONG).show();
            }
            else
            {
                if(ioException != null)
                {
                    Toast.makeText(ReservationCoffeeActivity.this, "Erreur de connexion", Toast.LENGTH_LONG).show();
                    goToDisconaction();
                }
                else
                {
                    //***********************COMMENTAIRE****************************
                    //Permet de pouvoir récuperer les données partout dans le code
                    //par la suite en stockant les données dans un sharePreference
                    //**************************************************************
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                    ArrayList<Booking> bookingsCoffee = new ArrayList<Booking>();

                    System.out.println("Taille booking : " + bookings.size());
                    for (int i = 0; i < bookings.size(); i++) {
                        if (bookings.get(i).getUserCafe().getUserName().equals(pref.getString("userName", null))) {
                            bookingsCoffee.add(bookings.get(i));
                        }
                    }

                    SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
                    //***********************COMMENTAIRE****************************
                    //Permet d'éditer le sharePreference
                    //**************************************************************
                    SharedPreferences.Editor editor = prefs.edit();

                    for (int i = 1; i <= bookingsCoffee.size(); i++) {
                        editor.putInt("idBooking" + i, bookingsCoffee.get(i - 1).getBookingId());
                        editor.putString("nameOffering" + i, bookingsCoffee.get(i - 1).getName());

                        String ResDate = new SimpleDateFormat("yyyy-MM-dd").format(bookingsCoffee.get(i - 1).getDateBooking());

                        editor.putString("dateReservation" + i, ResDate);
                    }
                    editor.putInt("SizeBooking", bookingsCoffee.size());
                    editor.commit();

                    //***********************COMMENTAIRE****************************
                    //Affichage des réservations
                    //**************************************************************
                    ArrayList<ReservationLine> arrayReservationLine = new ArrayList<ReservationLine>();
                    ListView listBooking = (ListView) findViewById(R.id.listBooking);

                    //***********************COMMENTAIRE****************************
                    //Permet d'afficher les données dans une listView
                    //**************************************************************
                    for (int i = 1; i <= pref.getInt("SizeBooking", 0); i++) {
                        Integer idBooking = pref.getInt("idBooking" + i, 0);
                        String coffeeName = pref.getString("nameOffering" + i, null);
                        String description = "Reservation faites le " + pref.getString("dateReservation" + i, null);

                        ReservationLine reservationLine = new ReservationLine(coffeeName, description, idBooking);

                        arrayReservationLine.add(reservationLine);
                    }
// 4
                    ReservationAdatper adatperReservation = new ReservationAdatper(ReservationCoffeeActivity.this, arrayReservationLine);
                    listBooking.setAdapter(adatperReservation);

                    spinner.setVisibility(View.GONE);
                }
            }
        }
    }

    //***********************COMMENTAIRE****************************
    //Permet de crééer le layout et de pouvoir le refaire pour si
    //il y a un changement d'orientation
    //**************************************************************
    public void creationLayout()
    {
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);

        new LoadBooking().execute();
    }
}
