package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.*;

import com.damienjacques.cafesuspendu.R;

public class OptionClientActivity extends MenuClientActivity
{
    private TextView mailTextView;
    private TextView phoneTextView;
    private TextView passwordTextView;
    private TextView confirmationPasswordTextView;

    private Button clickModify;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optionclient);

        mailTextView = (TextView) findViewById(R.id.mailOptionClient);
        phoneTextView = (TextView) findViewById(R.id.phoneOptionClient);
        passwordTextView = (TextView) findViewById(R.id.passwordOptionClient);
        confirmationPasswordTextView = (TextView) findViewById(R.id.confirmationPasswordOptionClient);

        clickModify = (Button) findViewById(R.id.buttonModifyClientOption);

        clickModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(OptionClientActivity.this,OptionClientActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        this.getMenuInflater().inflate(R.menu.menu_client,menu);
        return true;
    }

    @Override
    public void goToReceptionClient()
    {
        Intent intentReception = new Intent(OptionClientActivity.this,ReceptionClientActivity.class);
        startActivity(intentReception);
    }

    @Override
    public void goToHistory()
    {
        Intent intentHistory = new Intent(OptionClientActivity.this,HistoryClientActivity.class);
        startActivity(intentHistory);
    }

    @Override
    public void goToPromotion()
    {
        Intent intentPromotion = new Intent(OptionClientActivity.this,PromotionClientActivity.class);
        startActivity(intentPromotion);
    }

    @Override
    public void goToOptionClient()
    {
        Intent intentOption = new Intent(OptionClientActivity.this,OptionClientActivity.class);
        startActivity(intentOption);
    }

    @Override
    public void goToDisconaction()
    {
        Intent intentDisconnect = new Intent(OptionClientActivity.this,MainActivity.class);
        startActivity(intentDisconnect);
    }
}
