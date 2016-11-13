package com.damienjacques.cafesuspendu;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class ReceptionCoffeeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptioncoffee);
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
                Intent intentReception = new Intent(ReceptionCoffeeActivity.this,ReceptionCoffeeActivity.class);
                startActivity(intentReception);
                break;
            case R.id.coffeeId:
                Intent intentCoffee = new Intent(ReceptionCoffeeActivity.this,OfferCoffeeActivity.class);
                startActivity(intentCoffee);
                break;
            case R.id.reservationId:
                Intent intentReservation = new Intent(ReceptionCoffeeActivity.this,ReservationCoffeeActivity.class);
                startActivity(intentReservation);
                break;
            case R.id.optionscoffeeId:
                Intent intentOption = new Intent(ReceptionCoffeeActivity.this,OptionCoffeeActivity.class);
                startActivity(intentOption);
                break;
            case R.id.disconactionId:
                Intent intentDisconnect = new Intent(ReceptionCoffeeActivity.this,MainActivity.class);
                startActivity(intentDisconnect);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.activity_receptioncoffee_land);
        }
        else
        {
            setContentView(R.layout.activity_receptioncoffee);
        }
    }
}
