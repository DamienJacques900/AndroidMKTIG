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
        String optionJSON = optionToJSON(userCoffee);
        putJsonStringWithURL(token,optionJSON, "http://cafesuspenduappweb.azurewebsites.net/api/Accounts/updatePromotionInformations?cafeId="+userCoffee.getId()+"&nbCoffeeRequiredForPromotion="+userCoffee.getNbCoffeeRequiredForPromotion()+"&promotionValue="+userCoffee.getPromotionValue());

    }

    public String optionToJSON(User user) throws Exception
    {
        JSONObject optionCoffee = new JSONObject();

        optionCoffee.accumulate("id",user.getId());
        optionCoffee.accumulate("userName", user.getUserName());
        optionCoffee.accumulate("cafeName", user.getCafeName());
        optionCoffee.accumulate("street", user.getStreet());
        optionCoffee.accumulate("number", user.getNumber());
        optionCoffee.accumulate("nbCoffeeRequiredForPromotion", user.getNbCoffeeRequiredForPromotion());
        optionCoffee.accumulate("promotionValue", user.getPromotionValue());
        optionCoffee.accumulate("bookings", user.getBookings());
        optionCoffee.accumulate("timeTables", user.getTimesTables());
        optionCoffee.accumulate("firstName", user.getFirstName());
        optionCoffee.accumulate("lastName", user.getLastName());
        optionCoffee.accumulate("email", user.getEmail());
        optionCoffee.accumulate("emailConfirmed", user.getEmailConfirmed());
        optionCoffee.accumulate("phoneNumber", user.getPhoneNumber());
        optionCoffee.accumulate("roles", user.getRoles());

        return optionCoffee.toString();
    }

    public void putChangeOptionPersonPhone(String token, User userPerson) throws Exception
    {
        String optionJSON = optionToJSON(userPerson);
        postJsonStringWithURL(token,optionJSON, "http://cafesuspenduappweb.azurewebsites.net/api/Accounts/ChangePhoneNumber?userId="+userPerson.getId()+"&phoneNumber="+userPerson.getPhoneNumber());
    }

    public void putChangeOptionPersonEmail(String token, User userPerson) throws Exception
    {
        String optionJSON = optionToJSON(userPerson);
        postJsonStringWithURL(token,optionJSON, "http://cafesuspenduappweb.azurewebsites.net/api/Accounts/ChangeEmail?userId="+userPerson.getId()+"&email="+userPerson.getEmail());
    }

    public void postNewRegistrationCoffee(User userCoffee) throws Exception
    {
        String registrationJSON = registratinCoffeeToJSON(userCoffee);
        postJsonStringRegistrationWithURL(registrationJSON, "http://cafesuspenduappweb.azurewebsites.net/api/Accounts/Users");
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

    public void postNewRegistrationPerson(User userPerson) throws Exception
    {
        String registrationJSON = registrationPersonToJSON(userPerson);
        postJsonStringRegistrationWithURL(registrationJSON, "http://cafesuspenduappweb.azurewebsites.net/api/Accounts/Users");
    }

    public String registrationPersonToJSON(User userPerson) throws Exception
    {
        JSONObject newPerson = new JSONObject();
        JSONArray timeTable = new JSONArray();
        JSONArray rolesArray = new JSONArray();
        rolesArray.put(userPerson.getRoles());

        newPerson.accumulate("userName",userPerson.getUserName());
        newPerson.accumulate("cafeName",null);
        newPerson.accumulate("street",null);
        newPerson.accumulate("number",null);
        newPerson.accumulate("nbCoffeeRequiredForPromotion",null);
        newPerson.accumulate("promotionValue",0.0);
        newPerson.accumulate("bookings",null);
        newPerson.accumulate("password",userPerson.getPassword());
        newPerson.accumulate("confirmPassword",userPerson.getConfirmPassword());
        newPerson.accumulate("timeTables",timeTable);
        newPerson.accumulate("firstName",userPerson.getFirstName());
        newPerson.accumulate("lastName",userPerson.getLastName());
        newPerson.accumulate("email",userPerson.getEmail());
        newPerson.accumulate("phoneNumber",userPerson.getPhoneNumber());
        newPerson.accumulate("roles",rolesArray);

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
