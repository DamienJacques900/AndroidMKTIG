package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.CharityDAO;
import com.damienjacques.cafesuspendu.model.Charity;

import java.util.ArrayList;


public class ReceptionCoffeeActivity extends MenuCoffeeActivity
{
    private String welcome;

    private TextView textWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptioncoffee);
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
        Intent intentReception = new Intent(ReceptionCoffeeActivity.this,ReceptionCoffeeActivity.class);
        startActivity(intentReception);
    }

    @Override
    public void goToCoffee()
    {
        Intent intentCoffee = new Intent(ReceptionCoffeeActivity.this,OfferCoffeeActivity.class);
        startActivity(intentCoffee);
    }

    @Override
    public void goToReservation()
    {
        Intent intentReservation = new Intent(ReceptionCoffeeActivity.this,ReservationCoffeeActivity.class);
        startActivity(intentReservation);
    }

    @Override
    public void goToOptionCoffee()
    {
        Intent intentOption = new Intent(ReceptionCoffeeActivity.this,OptionCoffeeActivity.class);
        startActivity(intentOption);
    }

    @Override
    public void goToDisconaction()
    {
        Intent intentDisconnect = new Intent(ReceptionCoffeeActivity.this,MainActivity.class);
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
            setContentView(R.layout.activity_receptioncoffee);
            creationLayout();
        }
        else
        {
            setContentView(R.layout.activity_receptioncoffee);
            creationLayout();
        }
    }

    public void creationLayout()
    {
        textWelcome = (TextView)findViewById(R.id.welcomeCoffee);
        new LoadCharity().execute();
    }

    public class LoadCharity extends AsyncTask<String, Void, Integer>
    {
        Exception exception;

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

        @Override
        protected Integer doInBackground(String... params)
        {
            CharityDAO charityDAO = new CharityDAO();
            Integer nbCoffee = 0;
            try
            {
                nbCoffee = charityDAO.getNbCoffeeCharity(pref.getString("userName",null));
            }
            catch(Exception e)
            {
                exception = e;
            }

            return nbCoffee;
        }

        //***********************COMMENTAIRE****************************
        //Permet d'executer quelque chose après le chargement des données
        //**************************************************************
        @Override
        protected void onPostExecute(Integer nbCoffee)
        {
            if (exception != null)
            {
                Toast.makeText(ReceptionCoffeeActivity.this, "Erreur de connexion", Toast.LENGTH_LONG).show();
                goToDisconaction();
            }

            welcome = "Grâce à vous, "+nbCoffee+" café(s) suspendus ont été offerts.";
            textWelcome.setText(welcome);
        }
    }
}
