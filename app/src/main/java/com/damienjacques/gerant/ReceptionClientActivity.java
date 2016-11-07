package com.damienjacques.gerant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
                return true;
            case R.id.historyId:
                Intent intentHistory = new Intent(ReceptionClientActivity.this,HistoryClientActivity.class);
                startActivity(intentHistory);
                return true;
            case R.id.promotionId:
                Intent intentPromotion = new Intent(ReceptionClientActivity.this,PromotionClientActivity.class);
                startActivity(intentPromotion);
                return true;
            case R.id.optionsClientId:
                Intent intentOption = new Intent(ReceptionClientActivity.this,OptionClientActivity.class);
                startActivity(intentOption);
                return true;
            case R.id.disconactionId:
                Intent intentDisconect = new Intent(ReceptionClientActivity.this,MainActivity.class);
                startActivity(intentDisconect);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
