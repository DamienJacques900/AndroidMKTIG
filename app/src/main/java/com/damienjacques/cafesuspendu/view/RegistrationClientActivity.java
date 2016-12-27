package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.UserDAO;
import com.damienjacques.cafesuspendu.model.User;

import java.io.IOException;
import java.util.ArrayList;


public class RegistrationClientActivity extends AppCompatActivity
{
    private Button clickRegistration;
    private TextView userNameTextView;
    private TextView passwordTextView;
    private TextView nameTextView;
    private TextView firstNameTextView;
    private TextView mailTextView;
    private TextView phoneTextView;
    private TextView confirmationPasswordTextView;

    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationclient);
        createLayout();
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
            setContentView(R.layout.activity_registrationclient);
            createLayout();
        }
        else
        {
            setContentView(R.layout.activity_registrationclient);
            createLayout();
        }
    }

    //***********************COMMENTAIRE****************************
    //Permet de crééer le layout et de pouvoir le refaire pour si
    //il y a un changement d'orientation
    //**************************************************************
    private void createLayout()
    {
        userNameTextView = (TextView) findViewById(R.id.userNameClientEdit);
        passwordTextView = (TextView) findViewById(R.id.passwordClientEdit);
        confirmationPasswordTextView = (TextView) findViewById(R.id.confirmationClientEdit);
        nameTextView = (TextView) findViewById(R.id.nameClientEdit);
        firstNameTextView = (TextView) findViewById(R.id.firstNameClientEdit);
        mailTextView = (TextView) findViewById(R.id.mailClientEdit);
        phoneTextView = (TextView) findViewById(R.id.phoneClientEdit);

        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        clickRegistration = (Button) findViewById(R.id.buttonRegistrationClient);

        clickRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                spinner.setVisibility(View.VISIBLE);
                new LoadNewUser().execute();
            }
        });
    }

    private class LoadNewUser extends AsyncTask<String, Void, ArrayList<User>>
    {
        Exception exception;

        String userName = userNameTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String confirmationPassword = confirmationPasswordTextView.getText().toString();
        String name = nameTextView.getText().toString();
        String firstName = firstNameTextView.getText().toString();
        String email = mailTextView.getText().toString();
        String phoneNumber = phoneTextView.getText().toString();
        String userPerson ="userperson";

        User newPerson = new User(userName,password,confirmationPassword,firstName,name,email,phoneNumber,userPerson);

        @Override
        protected ArrayList<User> doInBackground(String... params)
        {
            UserDAO userDAO = new UserDAO();
            ArrayList<User> users = new ArrayList<>();
            try
            {
                userDAO.postNewRegistrationPerson(newPerson);
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
                if (!password.equals(confirmationPassword))
                {
                    System.out.println(exception);
                    Toast.makeText(RegistrationClientActivity.this, "Les mot de passes tapés sont différents", Toast.LENGTH_LONG).show();
                    spinner.setVisibility(View.GONE);
                }
                else
                {
                    if (userName.equals("") || password.equals("") || confirmationPassword.equals("") || name.equals("") || firstName.equals(""))
                    {
                        System.out.println(exception);
                        Toast.makeText(RegistrationClientActivity.this, "Tout les champs doivent être remplis obligatoirememnt sauf email et numéro de téléphone", Toast.LENGTH_LONG).show();
                        spinner.setVisibility(View.GONE);
                    }
                    else
                    {
                        System.out.println(exception);
                        Toast.makeText(RegistrationClientActivity.this, "Erreur lors de l'enregistrement de l'inscription.Erreur de connexion.", Toast.LENGTH_LONG).show();
                        spinner.setVisibility(View.GONE);
                    }
                }
            }
            else
            {
                Intent intentReservation = new Intent(RegistrationClientActivity.this, MainActivity.class);
                startActivity(intentReservation);
                Toast.makeText(RegistrationClientActivity.this, "L'inscription a bien été effectuée, vous pouvez maintenant vous connecter", Toast.LENGTH_LONG).show();
            }
        }
    }
}
