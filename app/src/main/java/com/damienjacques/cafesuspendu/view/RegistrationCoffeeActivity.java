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
    private TextView confirmationPasswordTextView;
    private TextView streetTextView;
    private TextView numberTextView;
    private TextView nbCoffeePromotionTextView;
    private TextView promotionValueTextView;
    private TextView coffeNameTextView;

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

        userNameTextView = (TextView)findViewById(R.id.userNameCoffeeEdit);
        passwordTextView = (TextView)findViewById(R.id.passwordCoffeeEdit);
        confirmationPasswordTextView = (TextView)findViewById(R.id.confirmationPasswordCoffeeEdit);
        streetTextView = (TextView)findViewById(R.id.streetCoffeeEdit);
        numberTextView = (TextView)findViewById(R.id.numberCoffeeEdit);
        nbCoffeePromotionTextView= (TextView)findViewById(R.id.promotionCoffeeEdit);
        promotionValueTextView = (TextView)findViewById(R.id.promotionValueEdit);
        coffeNameTextView = (TextView) findViewById(R.id.coffeeNameEdit);


        clickRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                newUserRegistration();
            }
        });
    }

    public void newUserRegistration()
    {
        Exception exception;

        String userName = userNameTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String confirmationPassword = confirmationPasswordTextView.getText().toString();
        String street = streetTextView.getText().toString();
        String number = numberTextView.getText().toString();
        String promotionAfter = nbCoffeePromotionTextView.getText().toString();
        String promoValue = promotionValueTextView.getText().toString();
        String coffeeName = coffeNameTextView.getText().toString();
        String userCoffee = "userCoffee";

        int intPromotionAfter = Integer.parseInt(promotionAfter);
        Float doublePromoValue = Float.parseFloat(promoValue);

        if (!password.equals(confirmationPassword))
        {
            Toast.makeText(RegistrationCoffeeActivity.this, "Les mot de passes tapés sont différents", Toast.LENGTH_LONG).show();
        }
        else
        {
            if(userName.equals("") || password.equals("") || confirmationPassword.equals("") || street.equals("") ||number.equals("") || promotionAfter.equals("") || promoValue.equals("") || coffeeName.equals(""))
            {
                Toast.makeText(RegistrationCoffeeActivity.this, "Tout les champs doivent être remplis obligatoirememnt", Toast.LENGTH_LONG).show();
            }
            else
            {
                User newUser = new User(coffeeName,userName,password,street,number,intPromotionAfter,doublePromoValue,userCoffee);
                //fonction pour ajouter une user
                Intent intentReservation = new Intent(RegistrationCoffeeActivity.this,MainActivity.class);
                startActivity(intentReservation);
                Toast.makeText(RegistrationCoffeeActivity.this, "L'inscription a bien été effectuée, vous pouvez maintenant vous connecter", Toast.LENGTH_LONG).show();
            }
        }
    }
}
