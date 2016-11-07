package com.damienjacques.gerant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OptionCoffeeActivity extends AppCompatActivity
{
    private Button clickModify;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optioncoffee);

        clickModify =(Button) findViewById(R.id.modiftyButtonCoffeeOption);

        clickModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(OptionCoffeeActivity.this,OptionCoffeeActivity.class);
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
            case R.id.receptionCoffeeId:
                Intent intentReception = new Intent(OptionCoffeeActivity.this,ReceptionCoffeeActivity.class);
                startActivity(intentReception);
                return true;
            case R.id.coffeeId:
                Intent intentCoffe = new Intent(OptionCoffeeActivity.this,OfferCoffeeActivity.class);
                startActivity(intentCoffe);
                return true;
            case R.id.reservationId:
                Intent intentReservation = new Intent(OptionCoffeeActivity.this,ReservationCoffeeActivity.class);
                startActivity(intentReservation);
                return true;
            case R.id.optionscoffeeId:
                Intent intentOption = new Intent(OptionCoffeeActivity.this,OptionCoffeeActivity.class);
                startActivity(intentOption);
                return true;
            case R.id.disconactionId:
                Toast.makeText(OptionCoffeeActivity.this,"Vous avez appuyer sur déconnexion", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
