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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.CharityDAO;
import com.damienjacques.cafesuspendu.dao.UserDAO;
import com.damienjacques.cafesuspendu.exception.EmptyInputException;
import com.damienjacques.cafesuspendu.exception.NbGreaterThanOneException;
import com.damienjacques.cafesuspendu.exception.TooMuchException;
import com.damienjacques.cafesuspendu.model.Charity;
import com.damienjacques.cafesuspendu.model.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OptionCoffeeActivity extends MenuCoffeeActivity
{
    private Button clickModify;

    private ProgressBar spinner;

    private TextView nbCoffeeTextView;
    private TextView promotionValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optioncoffee);
        createLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
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

        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        nbCoffeeTextView = (TextView) findViewById(R.id.numberCoffeOption);
        promotionValueTextView = (TextView) findViewById(R.id.valuePromoCoffeeOption);

        clickModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                spinner.setVisibility(View.VISIBLE);
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
        NbGreaterThanOneException nbGreaterThanOneException;
        EmptyInputException emptyInputException;
        TooMuchException tooMuchException;

        String nbCoffee = nbCoffeeTextView.getText().toString();
        String promotionValue = promotionValueTextView.getText().toString();
        @Override
        protected ArrayList<User> doInBackground(String... params)
        {
            UserDAO userDAO = new UserDAO();
            ArrayList<User> users = new ArrayList<>();
            try
            {
                if (nbCoffee.equals("") || promotionValue.equals(""))
                {
                    throw new EmptyInputException();
                }

                if (Integer.parseInt(nbCoffee)< 1 || Float.parseFloat(promotionValue) < 0.1)
                {
                    throw new NbGreaterThanOneException();
                }

                if (Integer.parseInt(nbCoffee)> 100 || Float.parseFloat(promotionValue) > 100.0)
                {
                    throw new TooMuchException();
                }

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

                String userName = pref.getString("userName",null);
                String token = pref.getString("token",null);

                users = userDAO.getAllUsersOption(token);

                //***********************COMMENTAIRE****************************
                //Permet de récupérer les valeurs de l'utilisateur que l'on
                //souhaite.
                //**************************************************************
                int i;
                for(i = 0 ; i< users.size() && !users.get(i).getUserName().equals(userName); i++)
                {

                }

                User userModified = users.get(i);

                userModified.setNbCoffeeRequiredForPromotion(Integer.parseInt(nbCoffee));
                userModified.setPromotionValue(Float.parseFloat(promotionValue));

                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("nbCoffeeRequiredForPromotion", Integer.parseInt(nbCoffee));
                editor.putFloat("promotionValue", Float.parseFloat(promotionValue));
                editor.commit();

                userDAO.putChangeOptionCoffee(token, userModified);
            }
            catch(NbGreaterThanOneException e)
            {
                nbGreaterThanOneException = e;
            }
            catch(TooMuchException e)
            {
                tooMuchException = e;
            }
            catch(EmptyInputException e)
            {
                emptyInputException = e;
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
                System.out.println(exception);
                Toast.makeText(OptionCoffeeActivity.this, "Erreur lors de l'enregistrement des modifications. Erreur de connexion.", Toast.LENGTH_LONG).show();
                goToDisconaction();
            }
            else
            {
                if(nbGreaterThanOneException != null)
                {
                    Toast.makeText(OptionCoffeeActivity.this, nbGreaterThanOneException.getMessage(), Toast.LENGTH_SHORT).show();
                    spinner.setVisibility(View.GONE);
                }
                else
                {
                    if(emptyInputException != null)
                    {
                        Toast.makeText(OptionCoffeeActivity.this, emptyInputException.getMessage(), Toast.LENGTH_SHORT).show();
                        spinner.setVisibility(View.GONE);
                    }
                    else
                    {
                        if(tooMuchException != null)
                        {
                            Toast.makeText(OptionCoffeeActivity.this, tooMuchException.getMessage(), Toast.LENGTH_SHORT).show();
                            spinner.setVisibility(View.GONE);
                        }
                        else
                        {
                            Toast.makeText(OptionCoffeeActivity.this, "Les valeurs ont bien été modifiées", Toast.LENGTH_LONG).show();
                            spinner.setVisibility(View.GONE);
                            Intent intentOption = new Intent(OptionCoffeeActivity.this, OptionCoffeeActivity.class);
                            startActivity(intentOption);
                        }
                    }
                }
            }
        }
    }
}
