package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.*;
import com.damienjacques.cafesuspendu.model.*;

import java.io.IOException;
import java.net.HttpRetryException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity
{
    private Button clickRegistration;
    private Button clickConnection;

    private ProgressBar spinner;

    private TextView userNameTextView;
    private TextView passwordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connectionregistration);

        creationLayout();
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
            setContentView(R.layout.activity_connectionregistration);
            creationLayout();
        }
        else
        {
            setContentView(R.layout.activity_connectionregistration);
            creationLayout();
        }
    }

    //***********************COMMENTAIRE****************************
    //Permet de crééer le layout et de pouvoir le refaire pour si
    //il y a un changement d'orientation
    //**************************************************************
    private void creationLayout()
    {
        clickRegistration = (Button)findViewById(R.id.RegistrationMainButton);
        clickConnection = (Button)findViewById(R.id.ConnectionMainButton);
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        userNameTextView = (TextView)findViewById(R.id.UserNameMainEdit);
        passwordTextView = (TextView)findViewById(R.id.PasswordMainEdit);

        clickRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,ChooseRegistrationActivity.class);
                startActivity(intent);
            }
        });

        clickConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                spinner.setVisibility(View.VISIBLE);
                new LoadUser().execute();
            }
        });
    }

    //***********************COMMENTAIRE****************************
    //Permet de charger les données de l'API
    //**************************************************************
    private class LoadUser extends AsyncTask<String, Void, ArrayList<User>>
    {
        Exception exception;
        Exception connectionException;

        String userName = userNameTextView.getText().toString();
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
                editor.putString("token", token);
                editor.commit();
            }
            catch(java.net.UnknownHostException e )
            {
                connectionException = e;
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
                    Toast.makeText(MainActivity.this, "Vous devez remplir les champs identifiants et mot de passe pour pouvoir accèder à votre compte", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    System.out.println(exception);
                    Toast.makeText(MainActivity.this, "Identifiant ou mot de passe incorrect", Toast.LENGTH_LONG).show();
                }
                spinner.setVisibility(View.GONE);
            }
            else
            {
                if(connectionException != null)
                {
                    System.out.println(connectionException);
                    spinner.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Erreur de connexion, impossible de se connecter au compte", Toast.LENGTH_LONG).show();
                }
                else
                {
                    //***********************COMMENTAIRE****************************
                    //Permet d'aller chercher l'utilisateur que l'on souhaite
                    //**************************************************************
                    int i;
                    for (i = 0; i < users.size() && !users.get(i).getUserName().equals(userName); i++) {
                    }
                    //***********************COMMENTAIRE****************************
                    //Permet de pouvoir récuperer les données partout dans le code
                    //par la suite en stockant les données dans un sharePreference
                    //**************************************************************
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                    //***********************COMMENTAIRE****************************
                    //Permet d'éditer le sharePreference
                    //**************************************************************
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("userName", users.get(i).getUserName().toString());
                    editor.putString("role", users.get(i).getRoles().toString());
                    editor.putString("email", users.get(i).getEmail().toString());
                    editor.putString("phoneNumber", users.get(i).getPhoneNumber().toString());
                    editor.putInt("nbCoffeeRequiredForPromotion", users.get(i).getNbCoffeeRequiredForPromotion());
                    editor.putFloat("promotionValue", users.get(i).getPromotionValue());
                    editor.commit();

                    if (users.get(i).getRoles().equals("userperson"))
                    {//User
                        spinner.setVisibility(View.GONE);
                        Intent intent = new Intent(MainActivity.this, ReceptionClientActivity.class);
                        startActivity(intent);
                    }
                    else
                    {//Coffee
                        if (users.get(i).getRoles().equals("usercafe"))
                        {
                            spinner.setVisibility(View.GONE);
                            Intent intent = new Intent(MainActivity.this, ReceptionCoffeeActivity.class);
                            startActivity(intent);
                        } else
                        {
                            Toast.makeText(MainActivity.this, "Aucune role pour cet utilisateur", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }
}
