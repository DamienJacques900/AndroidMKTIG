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
        userNameTextView = (TextView) findViewById(R.id.userNameRegistrationClient);
        passwordTextView = (TextView) findViewById(R.id.passwordRegistrationClient);
        confirmationPasswordTextView = (TextView) findViewById(R.id.confirmationPassword);
        nameTextView = (TextView) findViewById(R.id.nameRegistrationClient);
        firstNameTextView = (TextView) findViewById(R.id.firstNameRegistrationClient);
        mailTextView = (TextView) findViewById(R.id.mailRegistrationClient);
        phoneTextView = (TextView) findViewById(R.id.phoneRegistrationClient) ;

        clickRegistration = (Button) findViewById(R.id.buttonRegistrationClient);

        clickRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new LoadUserRegistration().execute();
            }
        });
    }

    public class LoadUserRegistration extends AsyncTask<String, Void, ArrayList<User>>
    {
        Exception exception;

        String userName = userNameTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String confirmationPassword = confirmationPasswordTextView.getText().toString();
        String name = nameTextView.getText().toString();
        String firstName = firstNameTextView.getText().toString();
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
                Toast.makeText(RegistrationClientActivity.this, "Vous devez remplir tout les champs pour effectuer une modification", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Intent intent = new Intent(RegistrationClientActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
