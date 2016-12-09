package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.damienjacques.cafesuspendu.R;

public class MenuClientActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.receptionClientId:
                goToReceptionClient();
                break;
            case R.id.historyId:
                goToHistory();
                break;
            case R.id.promotionId:
                goToPromotion();
                break;
            case R.id.optionsClientId:
                goToOptionClient();
                break;
            case R.id.disconactionId:
                goToDisconaction();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    //***********************COMMENTAIRE****************************
    //Redéfinition des méthodes pour correspondre à la vue actuelle
    //**************************************************************
    public void goToReceptionClient()
    {
        Intent intentReception = new Intent(MenuClientActivity.this,ReceptionClientActivity.class);
        startActivity(intentReception);
    }

    public void goToHistory()
    {
        Intent intentHistory = new Intent(MenuClientActivity.this,HistoryClientActivity.class);
        startActivity(intentHistory);
    }

    public void goToPromotion()
    {
        Intent intentPromotion = new Intent(MenuClientActivity.this,PromotionClientActivity.class);
        startActivity(intentPromotion);
    }

    public void goToOptionClient()
    {
        Intent intentOption = new Intent(MenuClientActivity.this,OptionClientActivity.class);
        startActivity(intentOption);
    }

    public void goToDisconaction()
    {
        Intent intentDisconnect = new Intent(MenuClientActivity.this,MainActivity.class);
        startActivity(intentDisconnect);
    }
}
