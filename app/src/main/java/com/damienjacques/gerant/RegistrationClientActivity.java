package com.damienjacques.gerant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class RegistrationClientActivity extends AppCompatActivity
{
    private Button clickRegistration;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationclient);

        clickRegistration = (Button) findViewById(R.id.buttonRegistrationClient);

        clickRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(RegistrationClientActivity.this,ReceptionClientActivity.class);
                startActivity(intent);
            }
        });
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
            case R.id.receptionId:
                Intent intentReception = new Intent(RegistrationClientActivity.this,ReceptionCoffeeActivity.class);
                startActivity(intentReception);
                return true; //indispensable
            case R.id.coffeeId:
                Intent intentCoffe = new Intent(RegistrationClientActivity.this,OfferCoffeeActivity.class);
                startActivity(intentCoffe);
                return true;
            case R.id.reservationID:
                Intent intentReservation = new Intent(RegistrationClientActivity.this,ReservationCoffeeActivity.class);
                startActivity(intentReservation);
                return true;
            case R.id.disconaction:
                Toast.makeText(RegistrationClientActivity.this,"Vous avez appuyer sur déconnexion", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
