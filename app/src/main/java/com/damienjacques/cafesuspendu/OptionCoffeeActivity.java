package com.damienjacques.cafesuspendu;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OptionCoffeeActivity extends AppCompatActivity {
    private Button clickModify;

    private TextView nbCoffeeTextView;
    private TextView promotionValueTextView;
    private TextView passwordTextView;
    private TextView confirmPasswordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optioncoffee);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_coffee, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.receptionCoffeeId:
                Intent intentReception = new Intent(OptionCoffeeActivity.this, ReceptionCoffeeActivity.class);
                startActivity(intentReception);
                break;
            case R.id.coffeeId:
                Intent intentCoffee = new Intent(OptionCoffeeActivity.this, OfferCoffeeActivity.class);
                startActivity(intentCoffee);
                break;
            case R.id.reservationId:
                Intent intentReservation = new Intent(OptionCoffeeActivity.this, ReservationCoffeeActivity.class);
                startActivity(intentReservation);
                break;
            case R.id.optionscoffeeId:
                Intent intentOption = new Intent(OptionCoffeeActivity.this, OptionCoffeeActivity.class);
                startActivity(intentOption);
                break;
            case R.id.disconactionId:
                Intent intentDisconnect = new Intent(OptionCoffeeActivity.this, MainActivity.class);
                startActivity(intentDisconnect);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_optioncoffee_land);
        } else {
            setContentView(R.layout.activity_optioncoffee);
        }

    }
}
