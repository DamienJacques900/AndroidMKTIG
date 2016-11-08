package com.damienjacques.gerant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private Button clickRegistration;
    private Button clickConnection;
    private Button clickPourClient;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickRegistration = (Button)findViewById(R.id.buttonRegistrationMain);
        clickConnection = (Button)findViewById(R.id.buttonConnection);
        clickPourClient = (Button)findViewById(R.id.buttonInutile);

        clickRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,ChooseRegistrationActivity.class);
                startActivity(intent);
            }
        });

        clickConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,ReceptionCoffeeActivity.class);
                startActivity(intent);
            }
        });

        clickPourClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,ReceptionClientActivity.class);
                startActivity(intent);
            }
        });
    }
}
