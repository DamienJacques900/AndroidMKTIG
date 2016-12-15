package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.*;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.UserDAO;
import com.damienjacques.cafesuspendu.model.User;

import java.util.ArrayList;

public class OptionClientActivity extends MenuClientActivity
{
    private TextView mailTextView;
    private TextView phoneTextView;
    private TextView passwordTextView;
    private TextView confirmationPasswordTextView;

    private Button clickModify;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optionclient);
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
        Intent intentReception = new Intent(OptionClientActivity.this,ReceptionClientActivity.class);
        startActivity(intentReception);
    }

    @Override
    public void goToHistory()
    {
        Intent intentHistory = new Intent(OptionClientActivity.this,HistoryClientActivity.class);
        startActivity(intentHistory);
    }

    @Override
    public void goToPromotion()
    {
        Intent intentPromotion = new Intent(OptionClientActivity.this,PromotionClientActivity.class);
        startActivity(intentPromotion);
    }

    @Override
    public void goToOptionClient()
    {
        Intent intentOption = new Intent(OptionClientActivity.this,OptionClientActivity.class);
        startActivity(intentOption);
    }

    @Override
    public void goToDisconaction()
    {
        Intent intentDisconnect = new Intent(OptionClientActivity.this,MainActivity.class);
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
            setContentView(R.layout.activity_optionclient);
            createLayout();
        }
        else
        {
            setContentView(R.layout.activity_optionclient);
            createLayout();
        }
    }

    //***********************COMMENTAIRE****************************
    //Permet de crééer le layout et de pouvoir le refaire pour si
    //il y a un changement d'orientation
    //**************************************************************
    private void createLayout()
    {
        mailTextView = (TextView) findViewById(R.id.mailOptionClient);
        phoneTextView = (TextView) findViewById(R.id.phoneOptionClient);
        passwordTextView = (TextView) findViewById(R.id.passwordOptionClient);
        confirmationPasswordTextView = (TextView) findViewById(R.id.confirmationPasswordOptionClient);

        clickModify = (Button) findViewById(R.id.buttonModifyClientOption);

        clickModify.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new LoadUserOptionCoffee().execute();
            }
        });

        displayDataOption();
    }

    //***********************COMMENTAIRE****************************
    //Permet de récupérer les valeurs de la préférence et de le mettre
    //où l'on veut
    //**************************************************************
    private void displayDataOption()
    {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String email = pref.getString("email",null);
        mailTextView.setText(email);
        String phoneNumber = pref.getString("phoneNumber",null);
        phoneTextView.setText(phoneNumber);
    }

    public class LoadUserOptionCoffee extends AsyncTask<String, Void, ArrayList<User>>
    {
        Exception exception;

        String email = mailTextView.getText().toString();
        String phoneNumber = phoneTextView.getText().toString();

        @Override
        protected ArrayList<User> doInBackground(String... params)
        {
            UserDAO userDAO = new UserDAO();
            ArrayList<User> users = new ArrayList<>();
            try
            {
                users = userDAO.getAllUsers();
                userDAO.changeOptionPerson(email,phoneNumber);
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
                if (email.equals("") || phoneNumber.equals(""))
                {
                    Toast.makeText(OptionClientActivity.this, "ous devez remplir tout les champs pour effectuer une modification", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(OptionClientActivity.this, "Erreur lors de l'enregistrement des modifications", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(OptionClientActivity.this, "Les valeurs ont bien été modifiées", Toast.LENGTH_LONG).show();
                Intent intentOption = new Intent(OptionClientActivity.this,OptionClientActivity.class);
                startActivity(intentOption);
            }
        }
    }
}
