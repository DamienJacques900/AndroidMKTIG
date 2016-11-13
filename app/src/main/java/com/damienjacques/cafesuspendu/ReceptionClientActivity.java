package com.damienjacques.cafesuspendu;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class ReceptionClientActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptionclient);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        this.getMenuInflater().inflate(R.menu.menu_client,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.receptionClientId:
                Intent intentReception = new Intent(ReceptionClientActivity.this,ReceptionClientActivity.class);
                startActivity(intentReception);
                break;
            case R.id.historyId:
                Intent intentHistory = new Intent(ReceptionClientActivity.this,HistoryClientActivity.class);
                startActivity(intentHistory);
                break;
            case R.id.promotionId:
                Intent intentPromotion = new Intent(ReceptionClientActivity.this,PromotionClientActivity.class);
                startActivity(intentPromotion);
                break;
            case R.id.optionsClientId:
                Intent intentOption = new Intent(ReceptionClientActivity.this,OptionClientActivity.class);
                startActivity(intentOption);
                break;
            case R.id.disconactionId:
                Intent intentDisconnect = new Intent(ReceptionClientActivity.this,MainActivity.class);
                startActivity(intentDisconnect);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.activity_receptionclient_land);
        }
        else
        {
            setContentView(R.layout.activity_receptionclient);
        }
    }
}
