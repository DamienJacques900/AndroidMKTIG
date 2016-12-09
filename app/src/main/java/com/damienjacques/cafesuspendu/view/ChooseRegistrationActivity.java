package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

import com.damienjacques.cafesuspendu.R;

public class ChooseRegistrationActivity extends AppCompatActivity
{
    private SwitchCompat clientOrCafe;
    private Button registrationButton;
    private ImageView registrationImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        createLayout();
    }

    //***********************COMMENTAIRE****************************
    //Permet de gérer le changement d'orientation
    //**************************************************************
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.activity_choose);
            createLayout();
        }
        else
        {
            setContentView(R.layout.activity_choose);
            createLayout();
        }
    }

    //***********************COMMENTAIRE****************************
    //Permet de crééer le layout et de pouvoir le refaire pour si
    //il y a un changement d'orientation
    //**************************************************************
    private void createLayout()
    {
        registrationButton = (Button) findViewById(R.id.registrationButton);
        clientOrCafe = (SwitchCompat) findViewById(R.id.switch3);
        registrationImage = (ImageView) findViewById(R.id.registrationImage);

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent;

                if(clientOrCafe.isChecked())
                    intent = new Intent(ChooseRegistrationActivity.this,RegistrationCoffeeActivity.class);
                else
                    intent = new Intent(ChooseRegistrationActivity.this,RegistrationClientActivity.class);

                startActivity(intent);
            }
        });

        clientOrCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(((SwitchCompat)v).isChecked())
                    registrationImage.setImageResource(R.drawable.inscriptioncafe);
                else
                    registrationImage.setImageResource(R.drawable.inscriptionclient);

            }
        });
    }
}
