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
import android.widget.Toast;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.CharityDAO;
import com.damienjacques.cafesuspendu.model.Charity;
import com.damienjacques.cafesuspendu.model.PromotionAdapter;
import com.damienjacques.cafesuspendu.model.PromotionLine;

import java.io.IOException;
import java.util.ArrayList;

public class PromotionClientActivity extends MenuClientActivity
{
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotionclient);
        creationLayout();
    }

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

    //***********************COMMENTAIRE****************************
    //Permet de gérer le changement d'orientation
    //**************************************************************
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.activity_promotionclient);
            creationLayout();
        }
        else
        {
            setContentView(R.layout.activity_promotionclient);
            creationLayout();
        }
    }

    //***********************COMMENTAIRE****************************
    //Permet de charger les données de l'API
    //**************************************************************
    public class LoadCharity extends AsyncTask<String, Void, ArrayList<Charity>>
    {
        Exception exception;

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

        @Override
        protected ArrayList<Charity> doInBackground(String... params)
        {
            CharityDAO charityDAO = new CharityDAO();
            ArrayList<Charity> charities = new ArrayList<>();
            try
            {
                charities = charityDAO.getAllCharities(pref.getString("token",null));
            }
            catch(Exception e)
            {
                exception = e;
            }

            return charities;
        }

        //***********************COMMENTAIRE****************************
        //Permet d'executer quelque chose après le chargement des données
        //**************************************************************
        @Override
        protected void onPostExecute(ArrayList<Charity> charities)
        {
            if (exception != null)
            {
                System.out.println(exception);
                Toast.makeText(PromotionClientActivity.this, "Erreur lors de l'affichage. Erreur de connexion.", Toast.LENGTH_LONG).show();
                goToDisconaction();
            }
            else
            {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

                ArrayList<Charity> charitiesClient = new ArrayList<Charity>();

                for (int i = 0; i < charities.size(); i++)
                {
                    //***********************COMMENTAIRE****************************
                    //Permet de récupérer les charity du client actuel.
                    //**************************************************************
                    if (charities.get(i).getUserPerson().getUserName().equals(pref.getString("userName", null)))
                    {
                        charitiesClient.add(charities.get(i));
                    }
                }

                SharedPreferences.Editor editor = pref.edit();
                for (int i = 1; i <= charitiesClient.size(); i++)
                {
                    editor.putString("charities" + i, charitiesClient.get(i - 1).getUserCafe().getUserName());
                    editor.putInt("nbCoffeeOffered" + i, charitiesClient.get(i - 1).getNbCoffeeOffered());
                    editor.putInt("nbCoffeeRequired" + i, charitiesClient.get(i - 1).getUserCafe().getNbCoffeeRequiredForPromotion());
                    editor.putFloat("costPromoCoffee" + i, charitiesClient.get(i - 1).getUserCafe().getPromotionValue());
                }
                editor.putInt("SizeCharities", charitiesClient.size());
                editor.commit();
                 //***********************COMMENTAIRE****************************
                //Affichage des promotions
                //**************************************************************
                ArrayList<PromotionLine> arrayPromoLine = new ArrayList<PromotionLine>();
                ListView listCoffee = (ListView) findViewById(R.id.listCoffee);

                //***********************COMMENTAIRE****************************
                //Permet d'afficher les données dans une listView
                //**************************************************************
                for (int i = 1; i <= pref.getInt("SizeCharities", 0); i++)
                {
                    int nbCoffeeRequired = pref.getInt("nbCoffeeRequired" + i, 0);
                    int nbCoffeeOffered = pref.getInt("nbCoffeeOffered" + i, 0);
                    int nbCoffeeForPromotion = nbCoffeeRequired - (nbCoffeeOffered % nbCoffeeRequired);
                    Float costPromo = pref.getFloat("costPromoCoffee" + i, 0);
                    String coffeeName = pref.getString("charities" + i, null);
                    String coffeedescription = "\nIl vous reste " + nbCoffeeForPromotion + " café(s) avant la promotion de " + (double) costPromo + " euro(s)!";

                    float progressStatus = (Math.round(((double) nbCoffeeOffered / nbCoffeeRequired) * 100)) % 100;

                    ProgressBar progressBar = new ProgressBar(getApplicationContext());

                    PromotionLine promo = new PromotionLine(coffeeName, coffeedescription, progressBar, (int) progressStatus);

                    arrayPromoLine.add(promo);
                }

                PromotionAdapter promoAdapter = new PromotionAdapter(PromotionClientActivity.this, arrayPromoLine);
                listCoffee.setAdapter(promoAdapter);
            }

            spinner.setVisibility(View.GONE);
        }
    }

    //***********************COMMENTAIRE****************************
    //Permet de crééer le layout et de pouvoir le refaire pour si
    //il y a un changement d'orientation
    //**************************************************************
    public void creationLayout()
    {
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);

        new LoadCharity().execute();
    }
}
