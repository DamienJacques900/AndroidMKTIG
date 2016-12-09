package com.damienjacques.cafesuspendu.dao;

import android.util.Log;

import com.damienjacques.cafesuspendu.model.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class UserDAO
{

    public String getUserWithUserNameAndPw(String userName, String password) throws Exception
    {
        //***********************COMMENTAIRE****************************
        //Permet d'établir la connexion pour récuper le token
        //**************************************************************
        URL url = new URL("http://cafesuspenduappweb.azurewebsites.net/token");
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        //***********************COMMENTAIRE****************************
        //Permet de dire qu'on va faire une requête POST avec la propriété
        //qui suit
        //**************************************************************
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-type", "x-www-form-urlencoded");
        urlConnection.setDoInput(true);


        //***********************COMMENTAIRE****************************
        //Permet d'écrire dans la requête(comme dans fiddler)
        //**************************************************************
        OutputStream out = urlConnection.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out);
        urlConnection.connect();

        //***********************COMMENTAIRE****************************
        //C'est la requête pour récupérer le token
        //**************************************************************
        writer.write("username="+userName+"&password="+password+"&grant_type=password");
        writer.flush();
        BufferedReader br;
        if (200 <= urlConnection.getResponseCode() && urlConnection.getResponseCode() <= 299)
        {
            br = new BufferedReader(new InputStreamReader((urlConnection.getInputStream())));
        } else
        {
            br = new BufferedReader(new InputStreamReader((urlConnection.getErrorStream())));
        }

        StringBuilder sb = new StringBuilder();
        String stringJSON = "",line;
        while((line = br.readLine()) != null)
        {
            sb.append(line);
        }
        br.close();
        stringJSON = sb.toString();

        writer.close();
        out.close();

        urlConnection.disconnect();
        return jsonToToken(stringJSON);
    }


    public ArrayList<User> getAllUsers() throws Exception
    {
        //***********************COMMENTAIRE****************************
        //Permet d'établir la connexion
        //**************************************************************
        URL url = new URL("http://cafesuspenduappweb.azurewebsites.net/api/accounts/Users");
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
        System.out.println(stringJSON.toString());
        return jsonToUsers(stringJSON);
    }

    //***********************COMMENTAIRE****************************
    //Permet de convertir le format JSON de l'API en arrayList
    //**************************************************************
    private ArrayList<User> jsonToUsers(String stringJSON) throws Exception
    {
        ArrayList<User> users = new ArrayList<>();
        User user;
        JSONArray jsonArray = new JSONArray(stringJSON);
        //***********************COMMENTAIRE****************************
        //Tant que toutes les données du JSON ne sont pas parcourues
        //**************************************************************
        for(int i = 0; i < jsonArray.length();i++)
        {
            JSONObject jsonUser = jsonArray.getJSONObject(i);
            //ArrayList<Booking> bookings;
            JSONArray roles = jsonUser.getJSONArray("roles");
            if(roles.getString(0).equals("userperson"))
            {
                user = new User(jsonUser.getString("userName"), roles.getString(0), jsonUser.getString("email"), jsonUser.getString("phoneNumber"), 0, (float)jsonUser.getDouble("promotionValue"));
            }
            else
            {
                user = new User(jsonUser.getString("userName"),roles.getString(0),jsonUser.getString("email"),jsonUser.getString("phoneNumber"),jsonUser.getInt("nbCoffeeRequiredForPromotion"),(float)jsonUser.getDouble("promotionValue"));
            }
            users.add(user);
        }
        return users;
    }

    //***********************COMMENTAIRE****************************
    //Permet de récupérer le token
    //**************************************************************
    private String jsonToToken(String stringJSON) throws Exception
    {
        String test = new JSONObject(stringJSON).getString("access_token");
        return test;
    }

}
