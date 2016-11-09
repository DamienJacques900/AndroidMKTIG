package com.damienjacques.cafesuspendu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class OptionClientActivity extends AppCompatActivity
{
    private Button clickModify;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optionclient);

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
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.receptionClientId:
                Intent intentReception = new Intent(OptionClientActivity.this,ReceptionClientActivity.class);
                startActivity(intentReception);
                break;
            case R.id.historyId:
                Intent intentHistory = new Intent(OptionClientActivity.this,HistoryClientActivity.class);
                startActivity(intentHistory);
                break;
            case R.id.promotionId:
                Intent intentPromotion = new Intent(OptionClientActivity.this,PromotionClientActivity.class);
                startActivity(intentPromotion);
                break;
            case R.id.optionsClientId:
                Intent intentOption = new Intent(OptionClientActivity.this,OptionClientActivity.class);
                startActivity(intentOption);
                break;
            case R.id.disconactionId:
                Intent intentDisconnect = new Intent(OptionClientActivity.this,MainActivity.class);
                startActivity(intentDisconnect);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
