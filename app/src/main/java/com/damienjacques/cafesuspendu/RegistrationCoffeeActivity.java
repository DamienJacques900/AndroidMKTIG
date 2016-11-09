package com.damienjacques.cafesuspendu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RegistrationCoffeeActivity extends AppCompatActivity
{
    private Button clickRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationcoffee);

        clickRegistration =(Button) findViewById(R.id.buttonRegistrationCoffee);

        clickRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(RegistrationCoffeeActivity.this,ReceptionCoffeeActivity.class);
                startActivity(intent);
            }
        });
    }
}
