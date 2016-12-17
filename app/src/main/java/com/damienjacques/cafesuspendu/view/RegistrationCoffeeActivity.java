package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
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

public class RegistrationCoffeeActivity extends AppCompatActivity
{
    private Button clickRegistration;
    private TextView userNameTextView;
    private TextView passwordTextView;
    private TextView streetTextView;
    private TextView numberTextView;
    private TextView nbCoffeePromotionTextView;
    private TextView promotionValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationcoffee);
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
            setContentView(R.layout.activity_registrationcoffee);
            createLayout();
        }
        else
        {
            setContentView(R.layout.activity_registrationcoffee);
            createLayout();
        }
    }

    //***********************COMMENTAIRE****************************
    //Permet de crééer le layout et de pouvoir le refaire pour si
    //il y a un changement d'orientation
    //**************************************************************
    private void createLayout()
    {
        clickRegistration = (Button)findViewById(R.id.buttonRegistrationCoffee);

        userNameTextView = (TextView)findViewById(R.id.userNameRegistrationCoffee);
        passwordTextView = (TextView)findViewById(R.id.passwordRegistrationCoffee);
        streetTextView = (TextView)findViewById(R.id.streetRegistrationCoffee);
        numberTextView = (TextView)findViewById(R.id.numberRegistrationCoffee);
        nbCoffeePromotionTextView= (TextView)findViewById(R.id.numberCoffeePromoRegistrationCoffee);
        promotionValueTextView = (TextView)findViewById(R.id.valuePromoRegistrationCoffee);


        clickRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    public void newUserRegistration()
    {
        Exception exception;

        String userName = userNameTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        //String confirmationPassword = confirmationPasswordTextView.getText().toString();
        String street = streetTextView.getText().toString();
        String number = numberTextView.getText().toString();
        String promotionAfter = nbCoffeePromotionTextView.getText().toString();
        String promoValue = promotionValueTextView.getText().toString();

    }
}
