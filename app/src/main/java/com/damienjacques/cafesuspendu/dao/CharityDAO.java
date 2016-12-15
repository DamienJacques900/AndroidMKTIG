package com.damienjacques.cafesuspendu.dao;

import android.util.Log;

import com.damienjacques.cafesuspendu.model.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CharityDAO
{

    public void newChairty(Integer nbCoffee, String userName, String password)
    {

    }

    public ArrayList<Charity> getAllCharities() throws Exception
    {
        //***********************COMMENTAIRE****************************
        //Permet d'établir la connexion
        //**************************************************************
        URL url = new URL("http://cafesuspenduappweb.azurewebsites.net/api/charities");
        URLConnection connection = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String stringJSON = "",line;
        //***********************COMMENTAIRE****************************
        //Tant que toutes les données de l'API ne sont pas parcourues
        //**************************************************************
        while((line=br.readLine())!=null)
        {
            sb.append(line);
        }
        br.close();
        stringJSON = sb.toString();
        return jsonToCharities(stringJSON);
    }

    //***********************COMMENTAIRE****************************
    //Permet de convertir le format JSON de l'API en arrayList
    //**************************************************************
    private ArrayList<Charity> jsonToCharities(String stringJSON) throws Exception
    {
        ArrayList<Charity> charities = new ArrayList<>();
        Charity charity;
        JSONArray jsonArray = new JSONArray(stringJSON);
        //***********************COMMENTAIRE****************************
        //Tant que toutes les données du JSON ne sont pas parcourues
        //**************************************************************
        for(int i = 0; i < jsonArray.length();i++)
        {
            JSONObject jsonCharity = jsonArray.getJSONObject(i);
            //Log.i("Charities",jsonCharity.toString());
            JSONObject jsonUserCient = jsonCharity.getJSONObject("ApplicationUserPerson");
            User userClient = new User(jsonUserCient.getString("UserName"),0,(float)0.0);

            JSONObject jsonUserCoffee = jsonCharity.getJSONObject("ApplicationUserCoffee");
            User userCoffee = new User(jsonUserCoffee.getString("UserName"),jsonUserCoffee.getInt("NbCoffeeRequiredForPromotion"),(float)jsonUserCoffee.getDouble("PromotionValue"));

            SimpleDateFormat dateOffering = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOff = dateOffering.parse(jsonCharity.getString("OfferingTime"));
            charity = new Charity(jsonCharity.getInt("NbCoffeeOffered"),jsonCharity.getInt("NbCoffeeConsumed"),dateOff,userClient,userCoffee);
            charities.add(charity);
        }
        return charities;
    }
}
