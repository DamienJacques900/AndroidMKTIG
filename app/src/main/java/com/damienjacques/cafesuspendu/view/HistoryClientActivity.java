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

    //***********************COMMENTAIRE****************************
    //Permet dl'affichage du menu
    //**************************************************************
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        this.getMenuInflater().inflate(R.menu.menu_client,menu);
        return true;
    }

    //***********************COMMENTAIRE****************************
    //Redéfinition des méthodes pour correspondre à la vue actuelle
    //**************************************************************
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

    //***********************COMMENTAIRE****************************
    //Permet de gérer le changement d'orientation
    //**************************************************************
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

    //***********************COMMENTAIRE****************************
    //Permet de charger les données de l'API
    //**************************************************************
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

        //***********************COMMENTAIRE****************************
        //Permet d'executer quelque chose après le chargement des données
        //**************************************************************
        @Override
        protected void onPostExecute(ArrayList<Charity> charities)
        {
            //***********************COMMENTAIRE****************************
            //Permet de pouvoir récuperer les données partout dans le code
            //par la suite en stockant les données dans un sharePreference
            //**************************************************************
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            ArrayList<Charity> charitiesClient = new ArrayList<Charity>();

            for(int i = 0 ; i < charities.size(); i++)
            {
                if(charities.get(i).getUserPerson().getUserName().equals(pref.getString("userName",null)))
                {
                    charitiesClient.add(charities.get(i));
                }
            }

            //***********************COMMENTAIRE****************************
            //Permet d'éditer le sharePreference
            //**************************************************************
            SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

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

    //***********************COMMENTAIRE****************************
    //Permet de crééer le layout et de pouvoir le refaire pour si
    //il y a un changement d'orientation
    //**************************************************************
    public void creationLayout()
    {
        new LoadCharity().execute();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

        ListView listHistory= (ListView) findViewById(R.id.listHistory);

        String[] listItemsHistory = new String[pref.getInt("SizeCharities",0)];

        //***********************COMMENTAIRE****************************
        //Permet d'afficher les données dans une listView
        //**************************************************************
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
