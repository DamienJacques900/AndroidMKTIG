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

public class CharityDAO extends ModifyDBDAO
{

    //***********************COMMENTAIRE****************************
    //Permet d'ajouter une nouvelle charitée
    //**************************************************************
    public void newCharity(Charity charity, String token) throws Exception
    {
        String JSONCharity = charityToJson(charity);
        postJsonStringWithURL(token, JSONCharity, "http://cafesuspenduappweb.azurewebsites.net/api/Charities");
    }

    private String charityToJson(Charity charity) throws Exception
    {
        JSONObject jsonCharity = new JSONObject();

        //***********************COMMENTAIRE****************************
        //Permet de créer un objet JSON et d'ajouter des valeurs à
        //l'intérieur en leur donnant un nom en première argument et
        //la valeur au deuxième.
        //**************************************************************
        SimpleDateFormat dateCharity = new SimpleDateFormat("yyyy-MM-dd");
        jsonCharity.accumulate("NbCoffeeOffered", charity.getNbCoffeeOffered());
        jsonCharity.accumulate("NbCoffeeConsumed", charity.getNbCoffeeConsumed());
        jsonCharity.accumulate("OfferingTime", dateCharity.format(charity.getOfferingTime())+"T00:00:00");
        jsonCharity.accumulate("ApplicationUserPersonEmail", charity.getUserPerson().getEmail());
        jsonCharity.accumulate("ApplicationUserCoffeeEmail", charity.getUserCafe().getEmail());

        return jsonCharity.toString();
    }

    //***********************COMMENTAIRE****************************
    //Permet de récupérer le nombre de charitées pour un café.
    //**************************************************************
    public Integer getNbCoffeeCharity(String token, String userName) throws Exception
    {
        String stringJSON = getJsonStringWithURL(token,"http://cafesuspenduappweb.azurewebsites.net/api/accounts/getNbCoffeeForCafe?userName="+userName);
        return jsonToNbCoffeeCharities(stringJSON);
    }

    private Integer jsonToNbCoffeeCharities(String stringJSON) throws Exception
    {
        JSONArray jsonArray = new JSONArray(stringJSON);
        Integer nbCoffeeCharity = jsonArray.getInt(0);

        return nbCoffeeCharity;
    }

    //***********************COMMENTAIRE****************************
    //Permet de récupérer le nombre de charités pour un personne
    //**************************************************************
    public Integer getNbCoffeeCharityPerson(String token, String userName) throws Exception
    {
        String stringJSON = getJsonStringWithURL(token,"http://cafesuspenduappweb.azurewebsites.net/api/accounts/getNbCoffeeForPerson?userName="+userName);
        return jsonToNbCoffeeCharitiesPerson(stringJSON);
    }

    private Integer jsonToNbCoffeeCharitiesPerson(String stringJSON) throws Exception
    {
        JSONArray jsonArray = new JSONArray(stringJSON);
        Integer nbCoffeeCharity = jsonArray.getInt(0);

        return nbCoffeeCharity;
    }




    public ArrayList<Charity> getAllCharities(String token) throws Exception
    {
        String stringJSON = getJsonStringWithURL(token,"http://cafesuspenduappweb.azurewebsites.net/api/charities");
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

            //***********************COMMENTAIRE****************************
            //Permet de récuperer les valeurs pour une personne
            //**************************************************************
            JSONObject jsonUserCient = jsonCharity.getJSONObject("ApplicationUserPerson");
            User userClient = new User(jsonUserCient.getString("UserName"),0,(float)0.0);

            //***********************COMMENTAIRE****************************
            //Permet de récuperer les valeurs pour un café
            //**************************************************************
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
