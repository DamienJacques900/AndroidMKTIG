package com.damienjacques.cafesuspendu.dao;

import android.util.Log;


import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ModifyDBDAO
{
    public void deleteJsonStringWithURL(String token, String urlAress) throws Exception
    {
        URL url = new URL(urlAress);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("DELETE");
        urlConnection.setRequestProperty("Content-type", "application/json");
        urlConnection.setRequestProperty("Authorization", token);
        urlConnection.setDoOutput(true);

        OutputStream out = urlConnection.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out);
        urlConnection.connect();


        if (200 <= urlConnection.getResponseCode() && urlConnection.getResponseCode() <= 299) {
            Log.i("Test", "Url connection bonne");
        }

        else {
            Log.i("Test", "URL connection : " + urlConnection.getResponseMessage() + " " + urlConnection.getResponseCode());
        }

        out.close();
        urlConnection.disconnect();
    }

    public void putJsonStringWithURL(String token, String jsonString, String urlAress) throws Exception
    {
        URL url = new URL(urlAress);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("PUT");
        urlConnection.setRequestProperty("Content-type", "application/json");
        urlConnection.setRequestProperty("Authorization", token);
        urlConnection.setDoOutput(true);

        OutputStream out = urlConnection.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out);
        urlConnection.connect();


        writer.write(jsonString);
        writer.flush();

        if (200 <= urlConnection.getResponseCode() && urlConnection.getResponseCode() <= 299) {
            Log.i("Test", "Url connection bonne");
        }

        else {
            Log.i("Test", "URL connection : " + urlConnection.getResponseMessage() + " " + urlConnection.getResponseCode());
        }

        writer.close();
        out.close();
        urlConnection.disconnect();
    }

    public void postJsonStringWithURL(String token, String jsonString, String urlAdress) throws Exception
    {
        URL url = new URL(urlAdress);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-type", "application/json");
        urlConnection.setRequestProperty("Authorization", token);
        urlConnection.setDoOutput(true);

        OutputStream out = urlConnection.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out);
        urlConnection.connect();


        writer.write(jsonString);
        writer.flush();

        if (200 <= urlConnection.getResponseCode() && urlConnection.getResponseCode() <= 299) {
            Log.i("Test", "Url connection bonne");
        }

        else {
            Log.i("Test", "URL connection : " + urlConnection.getResponseMessage() + " " + urlConnection.getResponseCode());
        }

        writer.close();
        out.close();
        urlConnection.disconnect();
    }
}
