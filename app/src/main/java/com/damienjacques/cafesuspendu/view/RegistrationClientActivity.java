package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import com.damienjacques.cafesuspendu.R;


public class RegistrationClientActivity extends AppCompatActivity
{
    private Button clickRegistration;
    private TextView userNameTextView;
    private TextView passwordTextView;
    private TextView nameTextView;
    private TextView firstNameTextView;
    private TextView mailTextView;
    private TextView phoneTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationclient);
        createLayout();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.activity_registrationclient);
            createLayout();
        }
        else
        {
            setContentView(R.layout.activity_registrationclient);
            createLayout();
        }
    }

    private void createLayout()
    {
        userNameTextView = (TextView) findViewById(R.id.userNameRegistrationClient);
        passwordTextView = (TextView) findViewById(R.id.passwordRegistrationClient);
        nameTextView = (TextView) findViewById(R.id.nameRegistrationClient);
        firstNameTextView = (TextView) findViewById(R.id.firstNameRegistrationClient);
        mailTextView = (TextView) findViewById(R.id.mailRegistrationClient);
        phoneTextView = (TextView) findViewById(R.id.phoneRegistrationClient) ;

        clickRegistration = (Button) findViewById(R.id.buttonRegistrationClient);

        clickRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(RegistrationClientActivity.this,ReceptionClientActivity.class);
                startActivity(intent);
            }
        });
    }
}
