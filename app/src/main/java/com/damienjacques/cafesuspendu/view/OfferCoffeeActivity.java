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
import com.damienjacques.cafesuspendu.dao.UserDAO;
import com.damienjacques.cafesuspendu.model.Charity;
import com.damienjacques.cafesuspendu.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
                new LoadUser().execute();
            }
        });
    }

    public class LoadCharity extends AsyncTask<String, Void, Integer>
    {
        Exception exception;

        String userName = clientTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String nbCoffee = nbCoffeeTextView.getText().toString();
        Integer intNbCoffee;

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

        @Override
        protected Integer doInBackground(String... params)
        {
            if(nbCoffee.equals(""))
                intNbCoffee = 0;
            else
                intNbCoffee = Integer.parseInt(nbCoffee);

            Date dateCharity = new Date();
            String tokenCoffee = pref.getString("token",null);

            CharityDAO charityDAO = new CharityDAO();

            //***********************COMMENTAIRE****************************
            //Permet d'utiliser les préférences pour récuperer des objets.
            //**************************************************************
            Gson gson = new Gson();
            String jsonCoffee = pref.getString("userCoffee", "");
            String jsonPerson = pref.getString("userPerson", "");
            User userCoffee = gson.fromJson(jsonCoffee, User.class);
            User userPerson = gson.fromJson(jsonPerson, User.class);

            try
            {
                Charity charity = new Charity(dateCharity,userPerson,userCoffee,intNbCoffee,0);
                charityDAO.newCharity(charity,tokenCoffee);
            }
            catch(Exception e)
            {
                exception = e;
            }

            return 0;
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
                Toast.makeText(OfferCoffeeActivity.this, "Erreur lors de l'enregistrement de l'offre", Toast.LENGTH_SHORT).show();
            }

            if(userName.equals("") || password.equals("") || intNbCoffee==0)
            {
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

    private class LoadUser extends AsyncTask<String, Void, ArrayList<User>>
    {
        Exception exception;

        String userName = clientTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        @Override
        protected ArrayList<User> doInBackground(String... params)
        {
            UserDAO userDAO = new UserDAO();
            ArrayList<User> users = new ArrayList<>();
            try
            {
                users = userDAO.getAllUsers();
                String token = userDAO.getUserWithUserNameAndPw(userName, password);
                //***********************COMMENTAIRE****************************
                //Il faut sauver le token pour la session pour pouvoir faire
                //des modifications et autre
                //**************************************************************
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("tokenPerson", token);
                editor.commit();
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
                if (userName.equals("") || password.equals(""))
                {
                    Toast.makeText(OfferCoffeeActivity.this, "Vous devez remplir les champs identifiants et mot de passe pour pouvoir offrir un café", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    System.out.println(exception);
                    Toast.makeText(OfferCoffeeActivity.this, "Identifiant ou mot de passe incorrect", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                String coffeeName = pref.getString("userName",null);

                //***********************COMMENTAIRE****************************
                //Permet de récupérer le café que l'on souhaite.
                //**************************************************************
                int i;
                for (i = 0; i < users.size() && !users.get(i).getUserName().equals(coffeeName); i++)
                {
                }

                User userCoffee = users.get(i);

                SharedPreferences.Editor editor = pref.edit();
                //***********************COMMENTAIRE****************************
                //Permet de sauvergarder un objet de les préférences.
                //**************************************************************
                Gson gsonCoffee = new Gson();
                String jsonCoffee = gsonCoffee.toJson(userCoffee);
                editor.putString("userCoffee", jsonCoffee);
                editor.commit();

                //***********************COMMENTAIRE****************************
                //Permet de récupérer l'utilisateur que l'on souhaite.
                //**************************************************************
                for (i = 0; i < users.size() && !users.get(i).getUserName().equals(userName); i++)
                {
                }

                User userPerson = users.get(i);

                //***********************COMMENTAIRE****************************
                //Permet de sauvergarder un objet de les préférences.
                //**************************************************************
                Gson gsonPerson = new Gson();
                String jsonPerson = gsonPerson.toJson(userPerson);
                editor.putString("userPerson", jsonPerson);
                editor.commit();

                new LoadCharity().execute();
            }
        }
    }
}
