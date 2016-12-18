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
        userNameTextView = (TextView) findViewById(R.id.userNameClientEdit);
        passwordTextView = (TextView) findViewById(R.id.passwordClientEdit);
        confirmationPasswordTextView = (TextView) findViewById(R.id.confirmationClientEdit);
        nameTextView = (TextView) findViewById(R.id.nameClientEdit);
        firstNameTextView = (TextView) findViewById(R.id.firstNameClientEdit);
        mailTextView = (TextView) findViewById(R.id.mailClientEdit);
        phoneTextView = (TextView) findViewById(R.id.phoneClientEdit) ;

        clickRegistration = (Button) findViewById(R.id.buttonRegistrationClient);

        clickRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                newUserRegistration();
            }
        });
    }

    public void newUserRegistration () {
        Exception exception;

        String userName = userNameTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String confirmationPassword = confirmationPasswordTextView.getText().toString();
        String name = nameTextView.getText().toString();
        String firstName = firstNameTextView.getText().toString();
        String email = mailTextView.getText().toString();
        String phoneNumber = phoneTextView.getText().toString();
        String userPerson ="userPeron";

        if (password.equals(confirmationPassword))
        {
            User newUser = new User(userName,phoneNumber,email,name,firstName,password,userPerson);
        }
    }
}
