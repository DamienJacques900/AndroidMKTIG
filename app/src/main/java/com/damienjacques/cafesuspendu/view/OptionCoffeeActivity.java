package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.CharityDAO;
import com.damienjacques.cafesuspendu.dao.UserDAO;
import com.damienjacques.cafesuspendu.model.Charity;
import com.damienjacques.cafesuspendu.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OptionCoffeeActivity extends MenuCoffeeActivity
{
    private Button clickModify;

    private TextView nbCoffeeTextView;
    private TextView promotionValueTextView;
    private TextView passwordTextView;
    private TextView confirmPasswordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optioncoffee);
        createLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_coffee, menu);
        return true;
    }

    //***********************COMMENTAIRE****************************
    //Redéfinition des méthodes pour correspondre à la vue actuelle
    //**************************************************************
    @Override
    public void goToReceptionCoffee()
    {
        Intent intentReception = new Intent(OptionCoffeeActivity.this,ReceptionCoffeeActivity.class);
        startActivity(intentReception);
    }

    @Override
    public void goToCoffee()
    {
        Intent intentCoffee = new Intent(OptionCoffeeActivity.this,OfferCoffeeActivity.class);
        startActivity(intentCoffee);
    }

    @Override
    public void goToReservation()
    {
        Intent intentReservation = new Intent(OptionCoffeeActivity.this,ReservationCoffeeActivity.class);
        startActivity(intentReservation);
    }

    @Override
    public void goToOptionCoffee()
    {
        Intent intentOption = new Intent(OptionCoffeeActivity.this,OptionCoffeeActivity.class);
        startActivity(intentOption);
    }

    @Override
    public void goToDisconaction()
    {
        Intent intentDisconnect = new Intent(OptionCoffeeActivity.this,MainActivity.class);
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
            setContentView(R.layout.activity_optioncoffee);
            createLayout();
        }
        else
        {
            setContentView(R.layout.activity_optioncoffee);
            createLayout();
        }
    }

    //***********************COMMENTAIRE****************************
    //Permet de crééer le layout et de pouvoir le refaire pour si
    //il y a un changement d'orientation
    //**************************************************************
    private void createLayout()
    {
        clickModify = (Button) findViewById(R.id.modiftyButtonCoffeeOption);

        nbCoffeeTextView = (TextView) findViewById(R.id.numberCoffeOption);
        promotionValueTextView = (TextView) findViewById(R.id.valuePromoCoffeeOption);
        //passwordTextView = (TextView) findViewById(R.id.passwordCoffeeOption);
        //confirmPasswordTextView = (TextView) findViewById(R.id.confirmationPasswordCoffeeOption);

        clickModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new LoadUserModify().execute();
            }
        });

        displayDataOption();
    }

    //***********************COMMENTAIRE****************************
    //Permet de récupérer les valeurs de la préférence et de le mettre
    //où l'on veut
    //**************************************************************
    public void displayDataOption()
    {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        Integer nbCoffeeRequiredForPromotion = pref.getInt("nbCoffeeRequiredForPromotion",0);
        nbCoffeeTextView.setText(nbCoffeeRequiredForPromotion.toString());
        Float promotionValue = pref.getFloat("promotionValue",0);
        promotionValueTextView.setText(promotionValue.toString());
    }

    private class LoadUserModify extends AsyncTask<String, Void, ArrayList<User>>
    {
        Exception exception;

        String nbCoffee = nbCoffeeTextView.getText().toString();
        String promotionValue = promotionValueTextView.getText().toString();
        @Override
        protected ArrayList<User> doInBackground(String... params)
        {
            UserDAO userDAO = new UserDAO();
            ArrayList<User> users = new ArrayList<>();
            try
            {
                users = userDAO.getAllUsers();
            }
            catch(Exception e)
            {
                exception = e;
            }

            return users;
        }

        //***********************COMMENTAIRE****************************
        //Permet d'executer quelque chose après le chargement des données
        //**************************************************************
        @Override
        protected void onPostExecute(ArrayList<User> users)
        {
            if (exception != null)
            {
                if (nbCoffee.equals("") || promotionValue.equals(""))
                {
                    Toast.makeText(OptionCoffeeActivity.this, "Vous devez remplir tout les champs pour effectuer une modification", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(OptionCoffeeActivity.this, "Erreur lors de l'enregistrement des modifications", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

                String userName = pref.getString("userName",null);
                String token = pref.getString("token",null);

                int i;
                for(i = 0 ; i< users.size() && !users.get(i).getUserName().equals(userName); i++)
                {

                }

                User userModified = users.get(i);
                UserDAO userDAO = new UserDAO();

                //***********************COMMENTAIRE****************************
                //On va regarder si au moins un champ a été modifié
                //**************************************************************
                if(!nbCoffeeTextView.getText().toString().equals(nbCoffee) || !promotionValueTextView.getText().toString().equals(promotionValue))
                {
                    userModified.setNbCoffeeRequiredForPromotion(Integer.parseInt(nbCoffee));
                    userModified.setPromotionValue(Float.parseFloat(promotionValue));

                    try
                    {
                        userDAO.putChangeOptionPerson(token, userModified);
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(OptionCoffeeActivity.this, "Erreur lors de la modification", Toast.LENGTH_LONG).show();
                    }

                    Toast.makeText(OptionCoffeeActivity.this, "Les valeurs ont bien été modifiées", Toast.LENGTH_LONG).show();
                    Intent intentOption = new Intent(OptionCoffeeActivity.this,OptionClientActivity.class);
                    startActivity(intentOption);
                }

                Toast.makeText(OptionCoffeeActivity.this, "Aucune valeur n'a été modifiées, il faut des valeurs différentes", Toast.LENGTH_LONG).show();
                Intent intentOption = new Intent(OptionCoffeeActivity.this,OptionClientActivity.class);
                startActivity(intentOption);
            }
        }
    }
}
