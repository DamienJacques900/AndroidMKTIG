package com.damienjacques.gerant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class ReservationCoffeeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservationcoffee);
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
                Intent intentReception = new Intent(ReservationCoffeeActivity.this,ReceptionCoffeeActivity.class);
                startActivity(intentReception);
                return true;
            case R.id.coffeeId:
                Intent intentCoffe = new Intent(ReservationCoffeeActivity.this,OfferCoffeeActivity.class);
                startActivity(intentCoffe);
                return true;
            case R.id.reservationId:
                Intent intentReservation = new Intent(ReservationCoffeeActivity.this,ReservationCoffeeActivity.class);
                startActivity(intentReservation);
                return true;
            case R.id.optionscoffeeId:
                Intent intentOption = new Intent(ReservationCoffeeActivity.this,OptionCoffeeActivity.class);
                startActivity(intentOption);
                return true;
            case R.id.disconactionId:
                Toast.makeText(ReservationCoffeeActivity.this,"Vous avez appuyer sur déconnexion", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
