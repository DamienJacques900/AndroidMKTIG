package com.damienjacques.cafesuspendu.dao;

import android.util.Log;

import com.damienjacques.cafesuspendu.model.*;
import org.json.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class BookingDAO
{
    //***********************COMMENTAIRE****************************
    //Permet de récupérer les valeurs dans l'API
    //**************************************************************
    public ArrayList<Booking> getAllBooking() throws Exception
    {
        //***********************COMMENTAIRE****************************
        //Permet d'établir la connexion
        //**************************************************************
        URL url = new URL("http://cafesuspenduappweb.azurewebsites.net/api/Bookings");
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
        return jsonToBookings(stringJSON);
    }

    //***********************COMMENTAIRE****************************
    //Permet de convertir le format JSON de l'API en arrayList
    //**************************************************************
    private ArrayList<Booking> jsonToBookings(String stringJSON) throws Exception
    {
        ArrayList<Booking> bookings = new ArrayList<>();
        Booking booking;
        JSONArray jsonArray = new JSONArray(stringJSON);
        //***********************COMMENTAIRE****************************
        //Tant que toutes les données du JSON ne sont pas parcourues
        //**************************************************************
        for(int i = 0; i < jsonArray.length();i++)
        {
            JSONObject jsonBooking = jsonArray.getJSONObject(i);
            //Log.i("Booking",jsonBooking.toString());
            JSONObject jsonUser = jsonBooking.getJSONObject("ApplicationUser");
            User userCafe = new User(jsonUser.getString("UserName"));

            SimpleDateFormat dateBooking = new SimpleDateFormat("YYYY-MM-DD");

            booking = new Booking(dateBooking.parse(jsonBooking.getString("DateBooking")),jsonBooking.getString("Name"),userCafe);
            bookings.add(booking);
        }
        return bookings;
    }
}
