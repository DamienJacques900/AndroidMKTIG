package com.damienjacques.cafesuspendu.dao;

import android.util.*;

import com.damienjacques.cafesuspendu.model.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class TerminalDAO
{
    public ArrayList<Terminal> getAllTerminals() throws Exception
    {
        URL url = new URL("http://cafesuspenduappweb.azurewebsites.net/api/Terminals");
        URLConnection connection = url.openConnection();
        System.out.println(connection.toString());
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String stringJSON = "",line;
        while((line=br.readLine())!=null)
        {
            sb.append(line);
        }
        br.close();
        stringJSON = sb.toString();
        return jsonToTerminals(stringJSON);
    }

    private ArrayList<Terminal> jsonToTerminals(String stringJSON) throws Exception
    {
        ArrayList<Terminal> terminals = new ArrayList<>();
        Terminal terminal;
        JSONArray jsonArray = new JSONArray(stringJSON);
        for(int i = 0; i < jsonArray.length();i++)
        {
            JSONObject jsonTerminal = jsonArray.getJSONObject(i);
            terminal = new Terminal(jsonTerminal.getInt("TerminalId"),jsonTerminal.getInt("NbBookedCoffees"),jsonTerminal.getDouble("Latitude"),jsonTerminal.getDouble("Longitude"));
            terminals.add(terminal);
        }
        return terminals;
    }
}
