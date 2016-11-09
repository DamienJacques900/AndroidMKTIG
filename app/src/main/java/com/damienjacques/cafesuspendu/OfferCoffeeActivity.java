package com.damienjacques.cafesuspendu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class OfferCoffeeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offercoffee);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        this.getMenuInflater().inflate(R.menu.menu_coffee,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.receptionCoffeeId:
                Intent intentReception = new Intent(OfferCoffeeActivity.this,ReceptionCoffeeActivity.class);
                startActivity(intentReception);
                break;
            case R.id.coffeeId:
                Intent intentCoffee = new Intent(OfferCoffeeActivity.this,OfferCoffeeActivity.class);
                startActivity(intentCoffee);
                break;
            case R.id.reservationId:
                Intent intentReservation = new Intent(OfferCoffeeActivity.this,ReservationCoffeeActivity.class);
                startActivity(intentReservation);
                break;
            case R.id.optionscoffeeId:
                Intent intentOption = new Intent(OfferCoffeeActivity.this,OptionCoffeeActivity.class);
                startActivity(intentOption);
                break;
            case R.id.disconactionId:
                Intent intentDisconnect = new Intent(OfferCoffeeActivity.this,MainActivity.class);
                startActivity(intentDisconnect);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
