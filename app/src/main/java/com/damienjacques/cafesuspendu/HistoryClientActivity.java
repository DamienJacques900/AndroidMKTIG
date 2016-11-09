package com.damienjacques.cafesuspendu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class HistoryClientActivity extends AppCompatActivity
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
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.receptionClientId:
                Intent intentReception = new Intent(HistoryClientActivity.this,ReceptionClientActivity.class);
                startActivity(intentReception);
                break;
            case R.id.historyId:
                Intent intentHistory = new Intent(HistoryClientActivity.this,HistoryClientActivity.class);
                startActivity(intentHistory);
                break;
            case R.id.promotionId:
                Intent intentPromotion = new Intent(HistoryClientActivity.this,PromotionClientActivity.class);
                startActivity(intentPromotion);
                break;
            case R.id.optionsClientId:
                Intent intentOption = new Intent(HistoryClientActivity.this,OptionClientActivity.class);
                startActivity(intentOption);
                break;
            case R.id.disconactionId:
                Intent intentDisconnect = new Intent(HistoryClientActivity.this,MainActivity.class);
                startActivity(intentDisconnect);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}