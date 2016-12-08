package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.CharityDAO;
import com.damienjacques.cafesuspendu.model.Charity;

import java.util.ArrayList;

public class HistoryClientActivity extends MenuClientActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyclient);
        creationLayout();
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
            creationLayout();
        }
        else
        {
            setContentView(R.layout.activity_historyclient);
            creationLayout();
        }
    }

    public class LoadCharity extends AsyncTask<String, Void, ArrayList<Charity>>
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
                editor.putString("coffeeName"+i, charitiesClient.get(i-1).getUserCafe().getUserName());
                editor.putInt("nbCoffeeOffered"+i, charitiesClient.get(i-1).getNbCoffeeOffered());
                editor.putString("dateOffering"+i, charitiesClient.get(i-1).getOfferingTime().toString());
                System.out.println("Nom : "+charitiesClient.get(i-1).getUserCafe().getUserName()+" nbCoffe : "+charitiesClient.get(i-1).getNbCoffeeOffered()+" date : "+charitiesClient.get(i-1).getOfferingTime().toString());
            }
            editor.putInt("SizeCharities",charitiesClient.size());
            editor.commit();
        }
    }

    public void creationLayout()
    {
        new LoadCharity().execute();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

        ListView listHistory= (ListView) findViewById(R.id.listHistory);

        String[] listItemsHistory = new String[pref.getInt("SizeCharities",0)];

        for(int i = 1; i <= pref.getInt("SizeCharities",0); i++)
        {
            int nbCoffeeOffered = pref.getInt("nbCoffeeOffered"+i,0);
            String dateOffering = pref.getString("dateOffering"+i,null);
            String coffeeName = pref.getString("coffeeName"+i,null);
            coffeeName+="           "+nbCoffeeOffered+" le "+dateOffering;

            listItemsHistory[i-1] = coffeeName;
        }
// 4
        ArrayAdapter adapterCoffee = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItemsHistory);
        listHistory.setAdapter(adapterCoffee);
    }
}
