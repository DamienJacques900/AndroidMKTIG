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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.CharityDAO;
import com.damienjacques.cafesuspendu.model.Charity;
import com.damienjacques.cafesuspendu.model.HistoryAdapter;
import com.damienjacques.cafesuspendu.model.HistoryLine;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoryClientActivity extends MenuClientActivity
{
    private ProgressBar spinner;

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
                Toast.makeText(HistoryClientActivity.this, "Erreur de connexion", Toast.LENGTH_LONG).show();
                goToDisconaction();
            }
            else
            {
                //***********************COMMENTAIRE****************************
                //Permet de pouvoir récuperer les données partout dans le code
                //par la suite en stockant les données dans un sharePreference
                //**************************************************************
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                ArrayList<Charity> charitiesClient = new ArrayList<Charity>();

                for (int i = 0; i < charities.size(); i++)
                {
                    //***********************COMMENTAIRE****************************
                    //Si le charity correspondent à l'utilisateur actuel, alors
                    //on ajoute ces données dans l'arrayList de l'utilisateur.
                    //**************************************************************
                    if (charities.get(i).getUserPerson().getUserName().equals(pref.getString("userName", null)))
                    {
                        charitiesClient.add(charities.get(i));
                    }
                }

                //***********************COMMENTAIRE****************************
                //Permet d'éditer le sharePreference
                //**************************************************************
                SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                for (int i = 1; i <= charitiesClient.size(); i++)
                {
                    editor.putString("coffeeName" + i, charitiesClient.get(i - 1).getUserCafe().getUserName());
                    editor.putInt("nbCoffeeOffered" + i, charitiesClient.get(i - 1).getNbCoffeeOffered());
                    String HistoryDate = new SimpleDateFormat("yyyy-MM-dd").format(charitiesClient.get(i - 1).getOfferingTime());
                    editor.putString("dateOffering" + i, HistoryDate);
                }

                editor.putInt("SizeCharities", charitiesClient.size());
                editor.commit();


                //***********************COMMENTAIRE****************************
                //Affichage de l'historique
                //**************************************************************
                ArrayList<HistoryLine> arrayHistoryLine = new ArrayList<HistoryLine>();
                ListView listHistory= (ListView) findViewById(R.id.listHistory);

                //***********************COMMENTAIRE****************************
                //Permet d'afficher les données dans une listView
                //**************************************************************
                for(int i = 1; i <= pref.getInt("SizeCharities",0); i++)
                {
                    int nbCoffeeOffered = pref.getInt("nbCoffeeOffered"+i,0);
                    String dateOffering = pref.getString("dateOffering"+i,null);

                    String coffeeName = pref.getString("coffeeName"+i,null);
                    String coffeeDescription = nbCoffeeOffered+" café(s) offert le "+dateOffering;

                    System.out.println("Description = "+coffeeDescription);

                    HistoryLine historyLine = new HistoryLine(coffeeName,coffeeDescription);

                    arrayHistoryLine.add(historyLine);
                }

                HistoryAdapter adapterHistory = new HistoryAdapter(HistoryClientActivity.this,arrayHistoryLine);
                listHistory.setAdapter(adapterHistory);
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
