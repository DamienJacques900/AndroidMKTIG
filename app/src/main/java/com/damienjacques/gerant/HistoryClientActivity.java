package com.damienjacques.gerant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HistoryClientActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyclient);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        this.getMenuInflater().inflate(R.menu.menu_client,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.receptionId:
                Intent intentReception = new Intent(HistoryClientActivity.this, ReceptionCoffeeActivity.class);
                startActivity(intentReception);
                return true;
            case R.id.coffeeId:
                Intent intentCoffe = new Intent(HistoryClientActivity.this, OfferCoffeeActivity.class);
                startActivity(intentCoffe);
                return true;
            case R.id.reservationID:
                Intent intentReservation = new Intent(HistoryClientActivity.this, ReservationCoffeeActivity.class);
                startActivity(intentReservation);
                return true;
            case R.id.disconaction:
                Toast.makeText(HistoryClientActivity.this, "Vous avez appuyer sur déconnexion", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
