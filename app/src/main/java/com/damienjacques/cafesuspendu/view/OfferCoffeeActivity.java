package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.CharityDAO;
import com.damienjacques.cafesuspendu.model.Charity;

import java.util.ArrayList;

//La vue land et portrait bug, à régler
public class OfferCoffeeActivity extends MenuCoffeeActivity
{
    private Button offerButton;

    private TextView clientTextView;
    private TextView passwordTextView;
    private TextView nbCoffeeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offercoffee);

        createlayout();
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
        Intent intentReception = new Intent(OfferCoffeeActivity.this,ReceptionCoffeeActivity.class);
        startActivity(intentReception);
    }

    @Override
    public void goToCoffee()
    {
        Intent intentCoffee = new Intent(OfferCoffeeActivity.this,OfferCoffeeActivity.class);
        startActivity(intentCoffee);
    }

    @Override
    public void goToReservation()
    {
        Intent intentReservation = new Intent(OfferCoffeeActivity.this,ReservationCoffeeActivity.class);
        startActivity(intentReservation);
    }

    @Override
    public void goToOptionCoffee()
    {
        Intent intentOption = new Intent(OfferCoffeeActivity.this,OptionCoffeeActivity.class);
        startActivity(intentOption);
    }

    @Override
    public void goToDisconaction()
    {
        Intent intentDisconnect = new Intent(OfferCoffeeActivity.this,MainActivity.class);
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
            setContentView(R.layout.activity_offercoffee);
            createlayout();
        }
        else
        {
            setContentView(R.layout.activity_offercoffee);
            createlayout();
        }
    }

    //***********************COMMENTAIRE****************************
    //Permet de crééer le layout et de pouvoir le refaire pour si
    //il y a un changement d'orientation
    //**************************************************************
    private void createlayout()
    {
        offerButton = (Button)findViewById(R.id.buttonOffer);

        clientTextView = (TextView)findViewById(R.id.userNameBidder);
        passwordTextView = (TextView)findViewById(R.id.passwordBidder);
        nbCoffeeTextView = (TextView)findViewById(R.id.numberCoffeeBidder);

        offerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new LoadCharityAdd().execute();
            }
        });
    }

    public class LoadCharityAdd extends AsyncTask<String, Void, ArrayList<Charity>>
    {
        Exception exception;

        String userName = clientTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String nbCoffee = nbCoffeeTextView.getText().toString();
        Integer intNbCoffee;

        @Override
        protected ArrayList<Charity> doInBackground(String... params)
        {

            if(nbCoffee.equals(""))
                intNbCoffee = 0;
            else
                intNbCoffee = Integer.parseInt(nbCoffee);

            CharityDAO charityDAO = new CharityDAO();
            ArrayList<Charity> charities = new ArrayList<>();
            try
            {
                charities = charityDAO.getAllCharities();
                charityDAO.newCharity(intNbCoffee,userName,password);
            }
            catch(Exception e)
            {
                exception = e;
            }

            return charities;
        }

        //***********************COMMENTAIRE****************************
        //Permet d'executer quelque chose après le chargement des données
        //**************************************************************
        @Override
        protected void onPostExecute(ArrayList<Charity> charities)
        {
            if (exception != null)
            {
                Toast.makeText(OfferCoffeeActivity.this, "Erreur lors de l'enregistrement de l'offre", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(userName.equals("") || password.equals("") || intNbCoffee==0)
                {//A bouger quand l'ajout dans le charity sera fait, remettre dans exception
                    Toast.makeText(OfferCoffeeActivity.this, "Vous devez Remplir tout les champs", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(OfferCoffeeActivity.this, "L'ajout de votre café a bien été enregistré", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OfferCoffeeActivity.this, ReceptionCoffeeActivity.class);
                    startActivity(intent);
                }
            }

        }
    }
}
