package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.damienjacques.cafesuspendu.R;

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
        passwordTextView = (TextView) findViewById(R.id.passwordCoffeeOption);
        confirmPasswordTextView = (TextView) findViewById(R.id.confirmationPasswordCoffeeOption);

        clickModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionCoffeeActivity.this, OptionCoffeeActivity.class);
                startActivity(intent);
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
}
