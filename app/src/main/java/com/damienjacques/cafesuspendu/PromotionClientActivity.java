package com.damienjacques.cafesuspendu;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class PromotionClientActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotionclient);
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
                Intent intentReception = new Intent(PromotionClientActivity.this,ReceptionClientActivity.class);
                startActivity(intentReception);
                break;
            case R.id.historyId:
                Intent intentHistory = new Intent(PromotionClientActivity.this,HistoryClientActivity.class);
                startActivity(intentHistory);
                break;
            case R.id.promotionId:
                Intent intentPromotion = new Intent(PromotionClientActivity.this,PromotionClientActivity.class);
                startActivity(intentPromotion);
                break;
            case R.id.optionsClientId:
                Intent intentOption = new Intent(PromotionClientActivity.this,OptionClientActivity.class);
                startActivity(intentOption);
                break;
            case R.id.disconactionId:
                Intent intentDisconnect = new Intent(PromotionClientActivity.this,MainActivity.class);
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
            setContentView(R.layout.activity_promotionclient_land);
        }
        else
        {
            setContentView(R.layout.activity_promotionclient);
        }
    }
}
