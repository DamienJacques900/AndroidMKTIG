package com.damienjacques.cafesuspendu.dao;

import android.util.Log;

import com.damienjacques.cafesuspendu.model.*;
import org.json.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class BookingDAO extends ModifyDBDAO
{

    //***********************COMMENTAIRE****************************
    //Permet de supprimer les réservations
    //**************************************************************
    public void deleteBooking(Integer id, Boolean consumed, String token) throws Exception
    {
        String urlForDelete = "http://cafesuspenduappweb.azurewebsites.net/api/Bookings?id=" + id + "&isCoffeeConsumed=" + consumed;
        deleteJsonStringWithURL(token,urlForDelete);
    }
    //***********************COMMENTAIRE****************************
    //Permet de récupérer les valeurs dans l'API
    //**************************************************************
    public ArrayList<Booking> getAllBooking(String token) throws Exception
    {
        String stringJSON = getJsonStringWithURL(token,"http://cafesuspenduappweb.azurewebsites.net/api/Bookings");
        return jsonToBookings(stringJSON);
        /*
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
        return jsonToBookings(stringJSON);*/
    }

    //***********************COMMENTAIRE****************************
    //Permet de convertir le format JSON de l'API en arrayList et
    //de renvoyer les valeurs obtenues.
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

            //***********************COMMENTAIRE****************************
            //On créé un autre objet JSON pour pouvoir récupérer dans le
            //booking les valeurs correspondant à un utilisateur.
            //**************************************************************
            JSONObject jsonUser = jsonBooking.getJSONObject("ApplicationUser");
            User userCafe = new User(jsonUser.getString("UserName"));

            SimpleDateFormat dateBooking = new SimpleDateFormat("yyyy-MM-dd");

            //***********************COMMENTAIRE****************************
            //On va récuperer les valeurs du fichier JSON grâce au nom de
            //la valeur que l'on veut récupérer du format JSON.
            //**************************************************************
            booking = new Booking(dateBooking.parse(jsonBooking.getString("DateBooking")),jsonBooking.getString("Name"),userCafe,jsonBooking.getInt("BookingID"));
            bookings.add(booking);
        }
        return bookings;
    }
}
