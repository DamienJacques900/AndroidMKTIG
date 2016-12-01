package com.damienjacques.cafesuspendu.dao;

import android.util.Log;

import com.damienjacques.cafesuspendu.model.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class UserDAO
{
    public ArrayList<User> getAllUsers() throws Exception
    {
        URL url = new URL("http://cafesuspenduappweb.azurewebsites.net/api/accounts/Users");
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
        return jsonToUsers(stringJSON);
    }

    private ArrayList<User> jsonToUsers(String stringJSON) throws Exception
    {
        ArrayList<User> users = new ArrayList<>();
        User user;
        JSONArray jsonArray = new JSONArray(stringJSON);
        System.out.println("User : "+jsonArray.toString());
        for(int i = 0; i < jsonArray.length();i++)
        {
            //
            JSONObject jsonUser = jsonArray.getJSONObject(i);
            //List<Booking> bookings;
            user = new User(jsonUser.getString("userName"),jsonUser.getString("cafeName"),jsonUser.getString("street"),jsonUser.getString("number"),jsonUser.getInt("nbCoffeeRequiredForPromotion"),jsonUser.getDouble("promotionValue"),jsonUser.getString("userPersonId"),jsonUser.getString("firstName"),jsonUser.getString("lastName"),jsonUser.getString("email"),jsonUser.getString("phoneNumber"));
            users.add(user);
        }
        return users;
    }
}
