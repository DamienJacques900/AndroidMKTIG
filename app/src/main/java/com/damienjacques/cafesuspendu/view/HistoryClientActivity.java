package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

import com.damienjacques.cafesuspendu.R;

public class HistoryClientActivity extends MenuClientActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyclient);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) //pour que le menu s'affiche
    {
        this.getMenuInflater().inflate(R.menu.menu_client,menu);
        return true;
    }

    @Override
    public void goToReceptionClient()
    {
        Intent intentReception = new Intent(HistoryClientActivity.this,ReceptionClientActivity.class);
        startActivity(intentReception);
    }

    @Override
    public void goToHistory()
    {
        Intent intentHistory = new Intent(HistoryClientActivity.this,HistoryClientActivity.class);
        startActivity(intentHistory);
    }

    @Override
    public void goToPromotion()
    {
        Intent intentPromotion = new Intent(HistoryClientActivity.this,PromotionClientActivity.class);
        startActivity(intentPromotion);
    }

    @Override
    public void goToOptionClient()
    {
        Intent intentOption = new Intent(HistoryClientActivity.this,OptionClientActivity.class);
        startActivity(intentOption);
    }

    @Override
    public void goToDisconaction()
    {
        Intent intentDisconnect = new Intent(HistoryClientActivity.this,MainActivity.class);
        startActivity(intentDisconnect);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.activity_historyclient);
        }
        else
        {
            setContentView(R.layout.activity_historyclient);
        }
    }
}
