package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.damienjacques.cafesuspendu.R;

//Remarque général : Les vues n'affichent pas exactement ce qu'on fait dans android studio, à régler?

public class MainActivity extends AppCompatActivity
{
    private Button clickRegistration;
    private Button clickConnection;
    private Button clickPourClient;

    private TextView userNameTextView;
    private TextView passwordTextView;

    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickRegistration = (Button)findViewById(R.id.buttonRegistrationMain);
        clickConnection = (Button)findViewById(R.id.buttonConnection);
        clickPourClient = (Button)findViewById(R.id.buttonInutile);

        userNameTextView = (TextView)findViewById(R.id.userNameConnection);
        passwordTextView = (TextView)findViewById(R.id.passwordConnection);



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

       /* FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.buttonMain_fragment, new FragmentButtonMain());

        transaction.addToBackStack(null);

        transaction.commit();*/
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.activity_main);
        }
        else
        {
            setContentView(R.layout.activity_main);
        }
    }
/*
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(userNameTextView.getText(), userName);
        outState.putString(passwordTextView.getText(), password);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        userName = savedInstanceState.getString(userNameTextView.getText());
        password = savedInstanceState.getString(passwordTextView.getText());
    }
    */
}
