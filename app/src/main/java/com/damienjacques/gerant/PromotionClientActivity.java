package com.damienjacques.gerant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
                return true;
            case R.id.historyId:
                Intent intentHistory = new Intent(PromotionClientActivity.this,HistoryClientActivity.class);
                startActivity(intentHistory);
                return true;
            case R.id.promotionId:
                Intent intentPromotion = new Intent(PromotionClientActivity.this,PromotionClientActivity.class);
                startActivity(intentPromotion);
                return true;
            case R.id.optionsClientId:
                Intent intentOption = new Intent(PromotionClientActivity.this,OptionClientActivity.class);
                startActivity(intentOption);
                return true;
            case R.id.disconactionId:
                Intent intentDisconect = new Intent(PromotionClientActivity.this,MainActivity.class);
                startActivity(intentDisconect);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
