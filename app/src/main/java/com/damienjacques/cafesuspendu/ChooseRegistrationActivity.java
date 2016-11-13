package com.damienjacques.cafesuspendu;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ChooseRegistrationActivity extends AppCompatActivity
{
    private Button clickCoffee;
    private Button clickClient;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        clickCoffee = (Button) findViewById(R.id.buttonChoiceCoffee);

        clickCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ChooseRegistrationActivity.this,RegistrationCoffeeActivity.class);
                startActivity(intent);
            }
        });

        clickClient = (Button) findViewById(R.id.buttonChoiceClient);

        clickClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ChooseRegistrationActivity.this,RegistrationClientActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.activity_choose_land);
        }
        else
        {
            setContentView(R.layout.activity_choose);
        }
    }
}
