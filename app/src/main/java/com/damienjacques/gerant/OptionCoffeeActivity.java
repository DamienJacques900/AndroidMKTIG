package com.damienjacques.gerant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
}
