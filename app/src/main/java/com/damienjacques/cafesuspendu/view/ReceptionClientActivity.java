package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.CharityDAO;
import com.damienjacques.cafesuspendu.model.Charity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReceptionClientActivity extends MenuClientActivity
{
    private TextView textWelcome;

    private String welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptionclient);
        createLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        this.getMenuInflater().inflate(R.menu.menu_client,menu);
        return true;
    }

    //***********************COMMENTAIRE****************************
    //Redéfinition des méthodes pour correspondre à la vue actuelle
    //**************************************************************
    @Override
    public void goToReceptionClient()
    {
        Intent intentReception = new Intent(ReceptionClientActivity.this,ReceptionClientActivity.class);
        startActivity(intentReception);
    }

    @Override
    public void goToHistory()
    {
        Intent intentHistory = new Intent(ReceptionClientActivity.this,HistoryClientActivity.class);
        startActivity(intentHistory);
    }

    @Override
    public void goToPromotion()
    {
        Intent intentPromotion = new Intent(ReceptionClientActivity.this,PromotionClientActivity.class);
        startActivity(intentPromotion);
    }

    @Override
    public void goToOptionClient()
    {
        Intent intentOption = new Intent(ReceptionClientActivity.this,OptionClientActivity.class);
        startActivity(intentOption);
    }

    @Override
    public void goToDisconaction()
    {
        Intent intentDisconnect = new Intent(ReceptionClientActivity.this,MainActivity.class);
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
            setContentView(R.layout.activity_receptionclient);
            createLayout();
        }
        else
        {
            setContentView(R.layout.activity_receptionclient);
            createLayout();
        }
    }

    public void createLayout()
    {
        textWelcome = (TextView)findViewById(R.id.welcomeClient);
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
                nbCoffee = charityDAO.getNbCoffeeCharityPerson(pref.getString("token",null),pref.getString("userName",null));
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
                System.out.println(exception);
                Toast.makeText(ReceptionClientActivity.this, "Erreur de connexion", Toast.LENGTH_LONG).show();
                goToDisconaction();
            }

            welcome = "Grâce à vous, "+nbCoffee+" café(s) ont été offerts à des sans-abris.";
            textWelcome.setText(welcome);
        }
    }
}
