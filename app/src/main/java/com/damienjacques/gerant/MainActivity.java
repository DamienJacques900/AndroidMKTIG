package com.damienjacques.gerant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button clickRegistration,clickConnextion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickRegistration =(Button) findViewById(R.id.buttonRegistration);
        clickConnextion = (Button) findViewById(R.id.buttonConnexion);

        clickRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,ChooseRegistration.class);
                startActivity(intent);
            }
        });

        clickConnextion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,ReceptionActivity.class);
                startActivity(intent);
            }
        });
    }
}
