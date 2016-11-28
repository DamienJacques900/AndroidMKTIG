package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import com.damienjacques.cafesuspendu.R;

//La vue land et portrait bug, à régler
public class OfferCoffeeActivity extends MenuCoffeeActivity
{
    private Button offerButton;

    private TextView clientTextView;
    private TextView passwordTextView;
    private TextView nbCoffeeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offercoffee);

        createlayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        this.getMenuInflater().inflate(R.menu.menu_coffee,menu);
        return true;
    }

    @Override
    public void goToReceptionCoffee()
    {
        Intent intentReception = new Intent(OfferCoffeeActivity.this,ReceptionCoffeeActivity.class);
        startActivity(intentReception);
    }

    @Override
    public void goToCoffee()
    {
        Intent intentCoffee = new Intent(OfferCoffeeActivity.this,OfferCoffeeActivity.class);
        startActivity(intentCoffee);
    }

    @Override
    public void goToReservation()
    {
        Intent intentReservation = new Intent(OfferCoffeeActivity.this,ReservationCoffeeActivity.class);
        startActivity(intentReservation);
    }

    @Override
    public void goToOptionCoffee()
    {
        Intent intentOption = new Intent(OfferCoffeeActivity.this,OptionCoffeeActivity.class);
        startActivity(intentOption);
    }

    @Override
    public void goToDisconaction()
    {
        Intent intentDisconnect = new Intent(OfferCoffeeActivity.this,MainActivity.class);
        startActivity(intentDisconnect);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.activity_offercoffee);
            createlayout();
        }
        else
        {
            setContentView(R.layout.activity_offercoffee);
            createlayout();
        }
    }

    private void createlayout()
    {
        offerButton = (Button)findViewById(R.id.buttonOffer);

        clientTextView = (TextView)findViewById(R.id.userNameBidder);
        passwordTextView = (TextView)findViewById(R.id.passwordBidder);
        nbCoffeeTextView = (TextView)findViewById(R.id.numberCoffeeBidder);
    }
}
