package com.damienjacques.cafesuspendu.dao;

import com.damienjacques.cafesuspendu.model.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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
        System.out.println("Charity : "+jsonArray.toString());
        for(int i = 0; i < jsonArray.length();i++)
        {
            JSONObject jsonCharity = jsonArray.getJSONObject(i);
            User userCafe = new User();
            User user = new User();
            Date dateOffering = new Date();
            charity = new Charity(jsonCharity.getInt("nbCoffeeOffered"),jsonCharity.getInt("nbCoffeeConsumed"),dateOffering,userCafe,user);
            charities.add(charity);
        }
        return charities;
    }
}
