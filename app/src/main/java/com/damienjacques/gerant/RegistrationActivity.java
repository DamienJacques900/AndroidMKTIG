package com.damienjacques.gerant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Damien Jacques on 16-10-16.
 */

public class RegistrationActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationclient);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        this.getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.receptionId:
                Intent intentReception = new Intent(RegistrationActivity.this,ReceptionActivity.class);
                startActivity(intentReception);
                return true; //indispensable
            case R.id.coffeeId:
                Intent intentCoffe = new Intent(RegistrationActivity.this,CoffeeActivity.class);
                startActivity(intentCoffe);
                return true;
            case R.id.reservationID:
                Intent intentReservation = new Intent(RegistrationActivity.this,ReservationActivity.class);
                startActivity(intentReservation);
                return true;
            case R.id.disconaction:
                Toast.makeText(RegistrationActivity.this,"Vous avez appuyer sur déconnexion", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
