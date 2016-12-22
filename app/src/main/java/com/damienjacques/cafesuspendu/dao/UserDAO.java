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

public class UserDAO extends ModifyDBDAO
{

    public void putChangeOptionCoffee(String token, User userCoffee) throws Exception
    {
        putJsonStringWithURL(token, "http://cafesuspenduappweb.azurewebsites.net/api/accounts/"+userCoffee.getUserName());

    }

    /*public String optionCoffeeToJSON(User userCoffee) throws Exception
    {
        JSONObject optionCoffee = new JSONObject();

        //optionCoffee.accumulate("id",userCoffee);
        optionCoffee.accumulate("userName", userCoffee.getUserName());
        optionCoffee.accumulate("cafeName", userCoffee.getCafeName());
        optionCoffee.accumulate("street", userCoffee.getStreet());
        optionCoffee.accumulate("number", userCoffee.getNumber());
        optionCoffee.accumulate("nbCoffeeRequiredForPromotion", userCoffee.getNbCoffeeRequiredForPromotion());
        optionCoffee.accumulate("promotionValue", userCoffee.getPromotionValue());
        optionCoffee.accumulate("bookings", userCoffee.getBookings());
        optionCoffee.accumulate("timeTables", userCoffee.getTimesTables());
        optionCoffee.accumulate("firstName", userCoffee.getFirstName());
        optionCoffee.accumulate("lastName", userCoffee.getLastName());
        optionCoffee.accumulate("email", userCoffee.getEmail());
        optionCoffee.accumulate("emailConfirmed", userCoffee.getEmailConfirmed());
        optionCoffee.accumulate("phoneNumber", userCoffee.getPhoneNumber());
        optionCoffee.accumulate("roles", userCoffee.getRoles());
        //optionCoffee.accumulate("claims",userCoffee);

        return optionCoffee.toString();
    }*/

    public void putChangeOptionPersonPhone(String token, User userPerson) throws Exception
    {
        putJsonStringWithURL(token, "http://cafesuspenduappweb.azurewebsites.net/api/Accounts/changePhoneNumber,userId="+userPerson.getId()+"&phoneNumber="+userPerson.getPhoneNumber());
    }

    public void putChangeOptionPersonEmail(String token, User userPerson) throws Exception
    {
        putJsonStringWithURL(token, "http://cafesuspenduappweb.azurewebsites.net/api/Accounts/changePhoneNumber,userId="+userPerson.getId()+"&email="+userPerson.getEmail());
    }

    /*public String optionPersonToJSON(User userPerson) throws Exception
    {
        JSONObject optionPerson = new JSONObject();

        optionPerson.accumulate("id",userPerson.getId());
        optionPerson.accumulate("userName", userPerson.getUserName());
        optionPerson.accumulate("cafeName", userPerson.getCafeName());
        optionPerson.accumulate("street", userPerson.getStreet());
        optionPerson.accumulate("number", userPerson.getNumber());
        optionPerson.accumulate("nbCoffeeRequiredForPromotion", userPerson.getNbCoffeeRequiredForPromotion());
        optionPerson.accumulate("promotionValue", userPerson.getPromotionValue());
        optionPerson.accumulate("bookings", userPerson.getBookings());
        optionPerson.accumulate("timeTables", userPerson.getTimesTables());
        optionPerson.accumulate("firstName", userPerson.getFirstName());
        optionPerson.accumulate("lastName", userPerson.getLastName());
        optionPerson.accumulate("email", userPerson.getEmail());
        optionPerson.accumulate("emailConfirmed", userPerson.getEmailConfirmed());
        optionPerson.accumulate("phoneNumber", userPerson.getPhoneNumber());
        optionPerson.accumulate("roles", userPerson.getRoles());
        //optionPerson.accumulate("claims",userPerson);

        return optionPerson.toString();
    }*/

    public void postNewRegistrationCoffee(String token, User userCoffee) throws Exception
    {
        String registrationJSON = registratinCoffeeToJSON(userCoffee);
        postJsonStringWithURL(token, registrationJSON, "http://cafesuspenduappweb.azurewebsites.net/api/Accounts/Users");
    }

    public String registratinCoffeeToJSON(User userCoffee) throws Exception
    {
        JSONObject newCoffee = new JSONObject();

        //newCoffee.accumulate("id",userCoffee);
        newCoffee.accumulate("userName", userCoffee.getUserName());
        newCoffee.accumulate("cafeName", userCoffee.getCafeName());
        newCoffee.accumulate("street", userCoffee.getStreet());
        newCoffee.accumulate("number", userCoffee.getNumber());
        newCoffee.accumulate("nbCoffeeRequiredForPromotion", userCoffee.getNbCoffeeRequiredForPromotion());
        newCoffee.accumulate("promotionValue", userCoffee.getPromotionValue());
        newCoffee.accumulate("bookings", userCoffee.getBookings());
        newCoffee.accumulate("timeTables", userCoffee.getTimesTables());
        newCoffee.accumulate("firstName", userCoffee.getFirstName());
        newCoffee.accumulate("lastName", userCoffee.getLastName());
        newCoffee.accumulate("email", userCoffee.getEmail());
        newCoffee.accumulate("emailConfirmed", userCoffee.getEmailConfirmed());
        newCoffee.accumulate("phoneNumber", userCoffee.getPhoneNumber());
        newCoffee.accumulate("roles", userCoffee.getRoles());
        //newCoffee.accumulate("claims",userCoffee);

        return newCoffee.toString();
    }

    public void postNewRegistrationPerson(String token, User userPerson) throws Exception
    {
        String registrationJSON = registrationPersonToJSON(userPerson);
        postJsonStringWithURL(token, registrationJSON, "http://cafesuspenduappweb.azurewebsites.net/api/Accounts/Users");
    }

    public String registrationPersonToJSON(User userPerson) throws Exception
    {
        JSONObject newPerson = new JSONObject();

        newPerson.accumulate("userName",userPerson.getUserName());
        newPerson.accumulate("cafeName",userPerson.getCafeName());
        newPerson.accumulate("street",userPerson.getStreet());
        newPerson.accumulate("number",userPerson.getNumber());
        newPerson.accumulate("nbCoffeeRequiredForPromotion",userPerson.getNbCoffeeRequiredForPromotion());
        newPerson.accumulate("promotionValue",userPerson.getPromotionValue());
        newPerson.accumulate("bookings",userPerson.getBookings());
        newPerson.accumulate("timeTables",userPerson.getTimesTables());
        newPerson.accumulate("firstName",userPerson.getFirstName());
        newPerson.accumulate("lastName",userPerson.getLastName());
        newPerson.accumulate("email",userPerson.getEmail());
        newPerson.accumulate("emailConfirmed",userPerson.getEmailConfirmed());
        newPerson.accumulate("phoneNumber",userPerson.getPhoneNumber());
        newPerson.accumulate("roles",userPerson.getRoles());

        return newPerson.toString();
    }

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
            //Log.i("Users",jsonUser.toString());
            JSONArray roles = jsonUser.getJSONArray("roles");
            if(roles.getString(0).equals("userperson"))
            {
                user = new User(jsonUser.getString("userName"), roles.getString(0), jsonUser.getString("email"), jsonUser.getString("phoneNumber"), 0, (float)jsonUser.getDouble("promotionValue"),jsonUser.getString("id"));
            }
            else
            {
                user = new User(jsonUser.getString("userName"),roles.getString(0),jsonUser.getString("email"),jsonUser.getString("phoneNumber"),jsonUser.getInt("nbCoffeeRequiredForPromotion"),(float)jsonUser.getDouble("promotionValue"),jsonUser.getString("id"));
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
