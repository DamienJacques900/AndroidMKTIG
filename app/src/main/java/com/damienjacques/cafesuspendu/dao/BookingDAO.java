package com.damienjacques.cafesuspendu.dao;

import com.damienjacques.cafesuspendu.model.*;
import org.json.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class BookingDAO
{
    public ArrayList<Booking> getAllBooking() throws Exception
    {
        URL url = new URL("http://cafesuspenduappweb.azurewebsites.net/api/Bookings");
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
        return jsonToBookings(stringJSON);
    }

    private ArrayList<Booking> jsonToBookings(String stringJSON) throws Exception
    {
        ArrayList<Booking> bookings = new ArrayList<>();
        Booking booking;
        JSONArray jsonArray = new JSONArray(stringJSON);
        System.out.println("Booking : "+jsonArray.toString());
        for(int i = 0; i < jsonArray.length();i++)
        {
            JSONObject jsonBooking = jsonArray.getJSONObject(i);
            Date dateBooking = new Date();
            User userCafe = new User();
            booking = new Booking(dateBooking,jsonBooking.getString("Name"),userCafe);
            bookings.add(booking);
        }
        return bookings;
    }
}
