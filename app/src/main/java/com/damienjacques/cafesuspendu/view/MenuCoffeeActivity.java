package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.damienjacques.cafesuspendu.R;

public class MenuCoffeeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.receptionCoffeeId:
                goToReceptionCoffee();
                break;
            case R.id.coffeeId:
                goToCoffee();
                break;
            case R.id.reservationId:
                goToReservation();
                break;
            case R.id.optionscoffeeId:
                goToOptionCoffee();
                break;
            case R.id.disconactionId:
                goToDisconaction();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    //***********************COMMENTAIRE****************************
    //Redéfinition des méthodes pour correspondre à la vue actuelle
    //**************************************************************
    public void goToReceptionCoffee()
    {
        Intent intentReception = new Intent(MenuCoffeeActivity.this,ReceptionCoffeeActivity.class);
        startActivity(intentReception);
    }

    public void goToCoffee()
    {
        Intent intentCoffee = new Intent(MenuCoffeeActivity.this,OfferCoffeeActivity.class);
        startActivity(intentCoffee);
    }

    public void goToReservation()
    {
        Intent intentReservation = new Intent(MenuCoffeeActivity.this,ReservationCoffeeActivity.class);
        startActivity(intentReservation);
    }

    public void goToOptionCoffee()
    {
        Intent intentOption = new Intent(MenuCoffeeActivity.this,OptionCoffeeActivity.class);
        startActivity(intentOption);
    }

    public void goToDisconaction()
    {
        Intent intentDisconnect = new Intent(MenuCoffeeActivity.this,MainActivity.class);
        startActivity(intentDisconnect);
    }
}
