package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.CharityDAO;
import com.damienjacques.cafesuspendu.model.Charity;

import java.util.ArrayList;

public class PromotionClientActivity extends MenuClientActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        new LoadCharity().execute();
        setContentView(R.layout.activity_promotionclient);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

        ProgressBar progressCoffee= (ProgressBar) findViewById(R.id.progressBarCoffe1);

        int nbCoffeeRequiredTest = pref.getInt("nbCoffeeRequired1",0);
        int nbCoffeeOfferedTest = pref.getInt("nbCoffeeOffered1",0);
        long progressStatusCoffee = Math.round(((double)nbCoffeeOfferedTest/nbCoffeeRequiredTest)*100);

        progressCoffee.setProgress((int)progressStatusCoffee);

        ListView listCoffee= (ListView) findViewById(R.id.listCoffee);

        ProgressBar[] listProgressBarCoffee = new ProgressBar[pref.getInt("SizeCharities",0)];

        String[] listItemsCoffee = new String[pref.getInt("SizeCharities",0)];

        for(int i = 1; i <= pref.getInt("SizeCharities",0); i++){
            int nbCoffeeRequired = pref.getInt("nbCoffeeRequired"+i,0);
            int nbCoffeeOffered = pref.getInt("nbCoffeeOffered"+i,0);
            int nbCoffeeForPromotion = nbCoffeeRequired-nbCoffeeOffered;
            long costPromo = pref.getLong("costPromo"+i,0);
            String coffeeName = pref.getString("charities"+i,null);
            coffeeName += "\nIl vous reste "+nbCoffeeForPromotion+" café(s) avant la promotion de "+(double)costPromo+" euro(s)!";
            listItemsCoffee[i-1] = coffeeName;

            long progressStatus = Math.round(((double)nbCoffeeOffered/nbCoffeeRequired)*100);
            progressCoffee.setProgress((int)progressStatus);
            listProgressBarCoffee[i-1] = progressCoffee;
        }
// 4
        ArrayAdapter adapterCoffee = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItemsCoffee);
        listCoffee.setAdapter(adapterCoffee);

        ArrayAdapter adapterProgressBar = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listProgressBarCoffee);
        listCoffee.setAdapter(adapterProgressBar);

       /* LinearLayout linearLayout = (LinearLayout) findViewById(R.id.displayCoffee);
        TextView coffee = new TextView(PromotionClientActivity.this);
        coffee.setText(pref.getString("charities1",null));
        linearLayout.addView(coffee);*/
        /*for(int i=1; i <= pref.getInt("SizeCharities",0); i++)
        {
            LinearLayout childLayout = new LinearLayout(PromotionClientActivity.this);
            TextView coffee = new TextView(PromotionClientActivity.this);
            coffee.setText(pref.getString("charities"+i,null));
            System.out.println(coffee.getText());
            childLayout.addView(coffee);
            linearLayout.addView(childLayout);
        }*/
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
        Intent intentReception = new Intent(PromotionClientActivity.this,ReceptionClientActivity.class);
        startActivity(intentReception);
    }

    @Override
    public void goToHistory()
    {
        Intent intentHistory = new Intent(PromotionClientActivity.this,HistoryClientActivity.class);
        startActivity(intentHistory);
    }

    @Override
    public void goToPromotion()
    {
        Intent intentPromotion = new Intent(PromotionClientActivity.this,PromotionClientActivity.class);
        startActivity(intentPromotion);
    }

    @Override
    public void goToOptionClient()
    {
        Intent intentOption = new Intent(PromotionClientActivity.this,OptionClientActivity.class);
        startActivity(intentOption);
    }

    @Override
    public void goToDisconaction()
    {
        Intent intentDisconnect = new Intent(PromotionClientActivity.this,MainActivity.class);
        startActivity(intentDisconnect);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.activity_promotionclient);
        }
        else
        {
            setContentView(R.layout.activity_promotionclient);
        }
    }

    private class LoadCharity extends AsyncTask<String, Void, ArrayList<Charity>>
    {
        @Override
        protected ArrayList<Charity> doInBackground(String... params)
        {
            CharityDAO charityDAO = new CharityDAO();
            ArrayList<Charity> charities = new ArrayList<>();
            try
            {
                charities = charityDAO.getAllCharities();
            }
            catch(Exception e)
            {
                return charities;
            }

            return charities;
        }

        @Override
        protected void onPostExecute(ArrayList<Charity> charities)
        {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            ArrayList<Charity> charitiesClient = new ArrayList<Charity>();

            //System.out.println("Taille charities : "+charities.size());
            for(int i = 0 ; i < charities.size(); i++)
            {
                if(charities.get(i).getUserPerson().getUserName().equals(pref.getString("userName",null)))
                {
                    charitiesClient.add(charities.get(i));
                }
            }

            SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            //System.out.println("Taille charities client : "+charitiesClient.size());
            for(int i = 1; i <= charitiesClient.size(); i++)
            {
                editor.putString("charities"+i, charitiesClient.get(i-1).getUserCafe().getUserName());
                editor.putInt("nbCoffeeOffered"+i, charitiesClient.get(i-1).getNbCoffeeOffered());
                editor.putInt("nbCoffeeRequired"+i, charitiesClient.get(i-1).getUserCafe().getNbCoffeeRequiredForPromotion());
                editor.putLong("costPromo"+i, charitiesClient.get(i-1).getUserCafe().getPromotionValue());
                System.out.println("Cout ! = "+charitiesClient.get(i-1).getUserCafe().getPromotionValue());
            }
            editor.putInt("SizeCharities",charitiesClient.size());
            editor.commit();
        }
    }
}
