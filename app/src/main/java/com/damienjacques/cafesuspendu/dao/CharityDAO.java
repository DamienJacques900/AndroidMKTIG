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
    public ArrayList<Charity> getAllCharities() throws Exception
    {
        URL url = new URL("http://cafesuspenduappweb.azurewebsites.net/api/charities");
        URLConnection connection = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String stringJSON = "",line;
        while((line=br.readLine())!=null)
        {
            sb.append(line);
        }
        br.close();
        stringJSON = sb.toString();
        return jsonToCharities(stringJSON);
    }

    private ArrayList<Charity> jsonToCharities(String stringJSON) throws Exception
    {
        ArrayList<Charity> charities = new ArrayList<>();
        Charity charity;
        JSONArray jsonArray = new JSONArray(stringJSON);
        for(int i = 0; i < jsonArray.length();i++)
        {
            JSONObject jsonCharity = jsonArray.getJSONObject(i);
            Log.i("Charities",jsonCharity.toString());
            JSONObject jsonUserCient = jsonCharity.getJSONObject("ApplicationUserPerson");
            User userClient = new User(jsonUserCient.getString("UserName"),0,0);

            JSONObject jsonUserCoffee = jsonCharity.getJSONObject("ApplicationUserCoffee");
            User userCoffee = new User(jsonUserCoffee.getString("UserName"),jsonUserCoffee.getInt("NbCoffeeRequiredForPromotion"),jsonUserCoffee.getLong("PromotionValue"));

            System.out.println("Coutè"+(double)jsonUserCoffee.getLong("PromotionValue"));

            SimpleDateFormat dateOffering = new SimpleDateFormat("YYYY-MM-DD");
            charity = new Charity(jsonCharity.getInt("NbCoffeeOffered"),jsonCharity.getInt("NbCoffeeConsumed"),dateOffering.parse(jsonCharity.getString("OfferingTime")),userClient,userCoffee);
            charities.add(charity);
        }
        return charities;
    }
}
